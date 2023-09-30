package org.example;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static CommandHistory commandHistory = new CommandHistory();
    static String currentDirectory;
    public static void main(String[] args) {


        while(true){
            currentDirectory = System.getProperty("user.dir");
            System.out.print("["+currentDirectory+"]:");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            commandHistory.add(userInput);
            if(!continueProgram(userInput)){
                break;
            }
            if (!isValidCommand(userInput)) {
                System.out.println("Invalid command!");
            }
        }
    }

    public static boolean continueProgram(String userInput){
        if(userInput.equals("exit")){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isValidCommand(String userInput) {
        if (userInput.startsWith("^")) {
            if (commandHistory.errorCheck(userInput)) {
                if (continueProgram(commandHistory.command(commandHistory.index))) {
                    return isValidCommand(commandHistory.command(commandHistory.index));
                }
                return false;
            }
            return false;
        }
        else if(userInput.startsWith("cd")){
            if(userInput.length() > 2){
                return DirectoryChange.errorCheck(System.getProperty("user.dir"), userInput.substring(3));
            }
            if(userInput.equals("cd")){
                System.setProperty("user.dir", System.getProperty("user.home"));
                return true;
            }
            return true;
        }
        else if(userInput.startsWith("list")){
            List list = new List(userInput);
            return list.errorCheck();
        }
        else if(userInput.startsWith("history")){
            return commandHistory.errorCheck(userInput);
        }
        else if(userInput.startsWith("mdir")){
            return DirectoryChange.makeDir(userInput.substring(5));
        }
        else if(userInput.startsWith("rdir")){
            return DirectoryChange.removeDir(userInput.substring(5));
        }
        return false;
    }
}
