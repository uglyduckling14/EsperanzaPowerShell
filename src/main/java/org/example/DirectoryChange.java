package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectoryChange {
    static String directoryUser;
    private static String forwardDir(){
        System.setProperty("user.dir", directoryUser);
        return System.getProperty("user.dir");
    }
    public static boolean errorCheck(String currentDirectory, String directory){
        Path path;
        directory = splitCommand(directory)[0];

        if(directory.equals("..")){
            Path currentPath = Paths.get(currentDirectory);
            path = currentPath.getParent();
        }else {
            path = Paths.get(currentDirectory,directory);
        }
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

    private static String[] splitCommand(String command) {
        java.util.List<String> matchList = new java.util.ArrayList<>();

        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(command);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }

        return matchList.toArray(new String[matchList.size()]);
    }

    public static boolean makeDir(String directoryName) {
        String directory = splitCommand(directoryName)[0];
        Path path = Paths.get(System.getProperty("user.dir"), directory);

        if(!Files.exists(path)){
            File file = new File(path.toUri());
            try{
                file.createNewFile();
                return true;
            }
            catch(IOException e){
                return false;
            }
        }else{
            System.out.println("File already exists!");
            return false;
        }
    }

    public static boolean removeDir(String directoryName){
        String directory = splitCommand(directoryName)[0];
        Path path = Paths.get(System.getProperty("user.dir"), directory);

        if(Files.exists(path)){
            File file = new File(path.toUri());
            file.delete();
            return true;
        }else{
            System.out.println("File or directory does not exist!");
            return false;
        }
    }
}
