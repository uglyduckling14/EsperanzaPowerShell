package org.example;

import java.util.ArrayList;

public class CommandHistory {
    ArrayList<String> commandHistory = new ArrayList<>();
    int index;

    public ArrayList<String> add(String command){
        commandHistory.add(command);
        return commandHistory;
    }
    private void displayAll(){
        System.out.println("-- Command History --");
        for(String command:commandHistory){
            int index = commandHistory.indexOf(command)+1;
            System.out.println(commandHistory);
            System.out.println(index+" : "+command);
        }
    }
    public boolean errorCheck(String userInput){
        if(commandHistory==null){
            System.out.println("history is empty!");
            return false;
        }else if(userInput.charAt(0) == '^' && isNum(userInput)){
            return true;
        }else if(userInput.length() > 7){
            System.out.println("history does not take arguments");
            return false;
        }else{
            displayAll();
        }
        return true;
    }

    private boolean isNum(String userInput){
        try{
            index = Integer.parseInt(userInput.substring(2));
            return index>0 && index<= userInput.length()+1;
        }
        catch(Exception e){
            System.out.println("Invalid arguments!");
            return false;
        }
    }

    public String command(int index){
        return commandHistory.get(index);
    }
}
