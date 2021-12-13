package com.chaowei.controller.services;

import com.chaowei.model.EmailTreeItem;
import com.chaowei.view.IconResolver;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import java.util.List;

public class FetchFolderService extends Service<Void> {

    private Store store;
    private EmailTreeItem<String> foldersRoot;
    private List<Folder> folderList;
    private IconResolver iconResolver = new IconResolver();
    private static int NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE = 0;

    public FetchFolderService(Store store, EmailTreeItem<String> foldersRoot, List<Folder> folderList) {
        this.folderList = folderList;
        this.store = store;
        this.foldersRoot = foldersRoot;
        this.setOnSucceeded(e->{
            NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE--;
        });
    }

    public static boolean noServicesActive(){
        return NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE ==0;
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE++;
                fetchFolders();
                if (FetchFolderService.noServicesActive()){
                    System.out.println("checking for folders!!");
                    for (Folder folder : folderList){
                        if(folder.getType() != Folder.HOLDS_FOLDERS && folder.isOpen()){
                            folder.getMessageCount();
                        }
                    }
                }

                return null;
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders, foldersRoot);
    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> foldersRoot) throws MessagingException {
        for(Folder folder: folders){
            folderList.add(folder);
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            emailTreeItem.setGraphic(iconResolver.getIconForFolder(folder.getName()));

            foldersRoot.getChildren().add(emailTreeItem);
            foldersRoot.setExpanded(true);
            fetchMessagesOnFolder(folder, emailTreeItem);
            addMessageListenerToFolder(folder, emailTreeItem);

            if(folder.getType() == Folder.HOLDS_FOLDERS){
                Folder[] subFolders = folder.list();
                handleFolders(subFolders, emailTreeItem);
            }
        }
    }

    private void addMessageListenerToFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        folder.addMessageCountListener(new MessageCountListener() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                System.out.println("message added event!!: "+ e);
                for (int i = 0 ; i < e.getMessages().length; ++i){
                    try {
                        Message message = folder.getMessage(folder.getMessageCount()-i);
                        emailTreeItem.addEmailToTop(message);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void messagesRemoved(MessageCountEvent messageCountEvent) {

            }
        });
    }

    private void fetchMessagesOnFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        Service fetchMessageService = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if(folder.getType() != Folder.HOLDS_FOLDERS){
                            folder.open(Folder.READ_WRITE);
                            int folderSize = folder.getMessageCount();
                            for(int i = folderSize; i > 0 ; i--){
                                emailTreeItem.addEmail(folder.getMessage(i));
                            }
                        }
                        return null;
                    }
                };
            }

        };
        fetchMessageService.start();
    }
}
