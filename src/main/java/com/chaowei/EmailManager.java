package com.chaowei;

import com.chaowei.controller.services.FetchFolderService;
import com.chaowei.controller.services.FolderUpdaterService;
import com.chaowei.model.EmailAccount;
import com.chaowei.model.EmailMessage;
import com.chaowei.model.EmailTreeItem;
import com.chaowei.view.IconResolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Folder;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    private EmailMessage selectedMessage;

    private EmailTreeItem<String> selectedFolder;

    private ObservableList<EmailAccount> emailAccounts = FXCollections.observableArrayList();
    private IconResolver iconResolver = new IconResolver();


    public ObservableList<EmailAccount> getEmailAccounts(){
        return emailAccounts;
    }

    public EmailMessage getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessage selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public EmailTreeItem<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailTreeItem<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    private FolderUpdaterService folderUpdaterService;
    // Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");

    public EmailTreeItem <String> getFoldersRoot(){
        return foldersRoot;
    }

    private List<Folder> folderList = new ArrayList<>();

    public List<Folder> getFolderList(){
        return this.folderList;
    }

    public EmailManager(){
        this.folderUpdaterService = new FolderUpdaterService(folderList);
        folderUpdaterService.start();
    }

    public void addEmailAccount(EmailAccount emailAccount){
        emailAccounts.add(emailAccount);
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        treeItem.setGraphic(iconResolver.getIconForFolder(emailAccount.getAddress()));

        FetchFolderService fetchFolderService = new FetchFolderService(emailAccount.getStore(), treeItem, folderList);
        fetchFolderService.start();
        foldersRoot.getChildren().add(treeItem);
    }

    public void setRead() {
        try{
            selectedMessage.setRead(true);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, true);
            selectedFolder.decrementMessageCount();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setUnRead() {
        try{
            selectedMessage.setRead(false);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, false);
            selectedFolder.incrementMessageCount();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteSelectedMessage() {
        try{
            selectedMessage.getMessage().setFlag(Flags.Flag.DELETED, true);
            selectedFolder.getEmailMessages().remove(selectedMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
