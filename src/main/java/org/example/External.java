package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class External {
    public static long runExternal(String userInput) {
        if (userInput.contains("|")) {
            String[] pipeParts = userInput.split("\\|");
            String command1 = pipeParts[0].trim();
            String command2 = pipeParts[1].trim();
            try {
                ProcessBuilder process1 = new ProcessBuilder(command1.split("\\s+"));
                ProcessBuilder process2 = new ProcessBuilder(command2.split("\\s+"));
                process1.redirectOutput(ProcessBuilder.Redirect.PIPE);
                process2.redirectInput(ProcessBuilder.Redirect.PIPE);
                long time = System.currentTimeMillis();
                Process p1 = process1.start();
                Process p2 = process2.start();

                InputStream p1Output = p1.getInputStream();
                OutputStream p1Input = p2.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = p1Output.read(buffer)) != -1) {
                    p1Input.write(buffer, 0, bytesRead);
                    p1Input.flush();
                }

                p1Input.close();

                // Redirect process2's output to the terminal
                BufferedReader p2Reader = new BufferedReader(new InputStreamReader(p2.getInputStream()));
                String line;

                while ((line = p2Reader.readLine()) != null) {
                    System.out.println(line);
                }

                p2Reader.close();

                if(command1.split("\\s+")[0].endsWith("&") || command2.split("\\s+")[0].endsWith("&")){
                    return 0;
                }
                int exitCode1 = p1.waitFor();
                int exitCode2 = p2.waitFor();
                return System.currentTimeMillis() - time;
            } catch (IOException | InterruptedException e) {
                System.out.println("Piping failed!");
                return 0;
            }
        } else if(!userInput.contains("&")){
            String[] commandParts = userInput.split("\\s+");
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
                processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
                long time = System.currentTimeMillis();
                Process process = processBuilder.start();
                int exitCode = process.waitFor();
                return System.currentTimeMillis() - time;
            } catch (IOException | InterruptedException e) {
                System.out.println("External command does not exist!");
                return 0;
            }
        }else{
            userInput = userInput.replace("&", "");
            String[] commandParts = userInput.split("\\s+");
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
                processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
                Process process = processBuilder.start();
                int exitCode = process.waitFor();
                return 0;
            } catch (IOException | InterruptedException e) {
                System.out.println("External command does not exist!");
                return 0;
            }
        }
    }
}
