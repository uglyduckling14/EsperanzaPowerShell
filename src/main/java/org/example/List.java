package org.example;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
                if (file.isFile()) {
                    printFile(file);
                }else if(file.isDirectory()){
                    printDirectory(file, file.getPath());
                }
            }
        }
    }
    private void printFile(File file){
        String name = file.getName();
        String time =getTime(file.lastModified());
        String permissions = getPermissions(file);
        String bytes = String.valueOf(file.length());
        System.out.println(permissions+"\t"+bytes+"  "+time+"  "+name);
    }

    private void printDirectory(File file, String filePath){
        String name = file.getName();
        String time =getTime(file.lastModified());
        String permissions = getPermissions(file);
        int bytes = 0;
        for(File f: Objects.requireNonNull(file.listFiles())) {
            permissions += getPermissions(f);
            bytes += (f.length());
        }
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

    private String getTime(long time){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyy HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}
