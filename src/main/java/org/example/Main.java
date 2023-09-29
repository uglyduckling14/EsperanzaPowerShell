package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        boolean run = true;
        CommandHistory commandHistory = new CommandHistory();
        while(run){
            System.out.print("["+currentDirectory+"]:");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            run = continueProgram(userInput);
            run = isValidCommand(userInput);
            commandHistory.add(userInput);
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

    public static boolean isValidCommand(String userInput){
        if(userInput.substring(0,4).equals("list")){
            List list = new List(userInput);
            return list.errorCheck();
        }
        return false;
    }
}
