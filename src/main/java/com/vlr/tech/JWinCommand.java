package com.vlr.tech;

import com.vlr.tech.sys.SystemCommandExecutor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vignesh
 */
public class JWinCommand {

    static String root = "C:\\";
    static SystemCommandExecutor cmdExecutor;
    static int cmdExitStatus;
    static String cmdOutput;
    static String cmdError;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cmdExecutor = new SystemCommandExecutor();
        cmdExecutor.setWorkingDirectory(root);
        runCommand("ping 127.0.0.0");
    }

    static boolean runCommand(String cmd) {
        try {
            cmdExitStatus = cmdExecutor.runCommand(cmd);
            cmdOutput = cmdExecutor.getCommandOutput();
            cmdError = cmdExecutor.getCommandError();

            System.out.println("Command:        " + cmd);
            System.out.println("cmdExitStatus:  " + cmdExitStatus);
            System.out.println("cmdOutput:      " + cmdOutput);
            System.out.println("cmdError:       " + cmdError);

        } catch (Exception ex) {
            System.err.println("Error while executing command: " + ex);
        }
        return cmdExitStatus == 0;
    }

}
