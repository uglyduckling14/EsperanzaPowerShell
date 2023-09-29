package org.example;

import java.util.ArrayList;

public class CommandHistory {
    ArrayList<String> commandHistory = new ArrayList<>();

    public ArrayList<String> add(String command){
        commandHistory.add(command);
        return commandHistory;
    }
}
