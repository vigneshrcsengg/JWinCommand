package com.vlr.tech.sys;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author vignesh.rajendran
 */
public class AsyncStreamReader extends Thread {

    private StringBuffer fBuffer = null;
    private InputStream fInputStream = null;
    private String fThreadId = null;
    private boolean fStop = false;
    private ILogDevice fLogDevice = null;

    private String fNewLine = null;

    public AsyncStreamReader(InputStream inputStream, StringBuffer buffer, ILogDevice logDevice, String threadId) {
        fInputStream = inputStream;
        fBuffer = buffer;
        fThreadId = threadId;
        fLogDevice = logDevice;
        fNewLine = System.getProperty("line.separator");
    }

    public String getBuffer() {
        return fBuffer.toString();
    }

    @Override
    public void run() {
        try {
            readCommandOutput();
        } catch (IOException ex) {
            ex.printStackTrace(); //DEBUG
        }
    }

    private void readCommandOutput() throws IOException {
        try (BufferedReader bufOut = new BufferedReader(new InputStreamReader(fInputStream))) {
            String line = null;
            while ((fStop == false) && ((line = bufOut.readLine()) != null)) {
                fBuffer.append(line).append(fNewLine);
                printToDisplayDevice(line);
            }
            //printToConsole("END OF: " + fThreadId); //DEBUG
        }
    }

    public void stopReading() {
        fStop = true;
    }

    private void printToDisplayDevice(String line) {
        if (fLogDevice != null) {
            fLogDevice.log(line);
        } else {
            //printToConsole(line);//DEBUG
        }
    }

    private synchronized void printToConsole(String line) {
        System.out.println(line);
    }
}
