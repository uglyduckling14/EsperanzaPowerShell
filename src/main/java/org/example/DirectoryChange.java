package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DirectoryChange {
    static String directoryUser;
    public static String forwardDir(){
        System.setProperty("user.dir", directoryUser);
        return System.getProperty("user.dir");
    }
    public static boolean errorCheck(String currentDirectory, String directory){
        String newDirectory = System.getProperty("user.dir") + "/" + directory;
        Path path = Paths.get(currentDirectory,directory);

        System.out.println(path);
        if (Files.exists(path)) {
            try{
                directoryUser = path.toRealPath().toString();
                forwardDir();
                return true;
            }
            catch(IOException e){
                System.out.println("An error occurred");
                return false;
            }

        } else {
            System.out.println("Directory not found: " + directory);
        }
        return false;
    }

}
