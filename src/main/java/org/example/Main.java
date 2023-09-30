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
                System.out.println(userInput);
                break;
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
        if (userInput.substring(0, 1).equals("^")) {
            if (commandHistory.errorCheck(userInput)) {
                if (continueProgram(commandHistory.command(commandHistory.index))) {
                    return isValidCommand(commandHistory.command(commandHistory.index));
                }
                return false;
            }
            return false;
        }else if(userInput.substring(0,2).equals("cd")){
            if(userInput.length() > 2){
                return DirectoryChange.errorCheck(System.getProperty("user.dir"), userInput.substring(3));
            }
            return true;
        }else if(userInput.substring(0,4).equals("list")){
            List list = new List(userInput);
            return list.errorCheck();
        }
        else if(userInput.substring(0,7).equals("history")){
            return commandHistory.errorCheck(userInput);
        }
        return false;
    }
}
