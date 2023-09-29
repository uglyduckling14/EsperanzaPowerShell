package org.example;

import java.io.File;

public class List {
    String userInput;

    public List(String userInput){
        this.userInput = userInput;
    }
    public boolean errorCheck(){
        if(this.userInput.length() > 4) {
            System.out.println("list does not take arguments!");
            return false;
        }
        displayFiles();
        return true;
    }
    private void displayFiles(){
        String currentDirectory = System.getProperty("user.dir");
        File directory = new File(currentDirectory);
        File[] files = directory.listFiles();
        if(files != null){
            for (File file : files) {
                if (file.isDirectory() || file.isFile()) {
                    printFile(file);
                }
            }
        }
    }
    //TODO: create printDirectory
    //TODO: make time readable
    private void printFile(File file){
        String name = file.getName();
        String time = String.valueOf(file.lastModified());
        String permissions = getPermissions(file);
        String bytes = String.valueOf(file.length());
        System.out.println(permissions+"\t"+bytes+"  "+time+"  "+name);
    }

    private String getPermissions(File file){
        String permissions="-";
        if(file.isDirectory()){
            permissions+="d";
        }
        if(file.canRead()){
            permissions+="r";
        }
        if(file.canWrite()){
            permissions+="w";
        }
        if(file.canExecute()){
            permissions+="x";
        }
        permissions+="-";
        return permissions;
    }
}
