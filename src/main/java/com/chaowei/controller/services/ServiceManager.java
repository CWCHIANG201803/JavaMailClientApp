package com.chaowei.controller.services;

import com.chaowei.model.EmailTreeItem;

import javax.mail.Store;
import java.util.ArrayList;

public class ServiceManager {
    public void submitFetchFoldersJob(Store store, EmailTreeItem<String> emailTreeItem){
        FetchFolderService fetchFoldersService = new FetchFolderService(store, emailTreeItem, new ArrayList<>());
        fetchFoldersService.start();
    }
}
