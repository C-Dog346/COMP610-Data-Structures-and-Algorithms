/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa_assignment_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Callum
 */
public class FileSorter implements Runnable {

    private int limit;
    private File input;
    private File output;
    private String temp;
    private boolean stopRequested;
    private int fileCounter;
    private BufferedReader br;
    private Queue<String> q;
    //private double splitProgress;

    public FileSorter(int limit, File input, File output) {
        this.limit = limit;
        this.input = input;
        this.output = output;
        this.temp = "./resources/temp";
        this.stopRequested = false;
        this.fileCounter = 0;
        try {
            this.br = new BufferedReader(new FileReader(this.input));
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.splitProgress = 100 / input.length();
    }

    @Override
    public void run() {
        while (!stopRequested) {
            try {
                String[] currentStrings = splitStage();
                writeToTemp(currentStrings);

            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        merge();

        for (int i = 0; i < this.fileCounter; i++) {
            try {
                Files.deleteIfExists(Paths.get(this.temp + (this.fileCounter - i) + ".txt"));
            }
            catch (IOException ex) {
                System.out.println("Failed to delete a file!");
            }
        }

    }

    private String[] splitStage() throws FileNotFoundException {
        String[] currentStrings = readFromInput();
        try {
            if (currentStrings[0] != null) {
                Arrays.sort(currentStrings);
            }
        }
        catch (Exception NullPointerException) {

        }
        return currentStrings;
    }

    private String[] readFromInput() throws FileNotFoundException {
        String[] stringsInMemory = new String[this.limit];

        try {
            // Create buffered reader

            // Create a variable to store a line
            String line = "";
            if ((line = this.br.readLine()) != null) {
                for (int i = 0; i < this.limit; i++) {
                    //process the text for each line
                    if (line == null) {
                        i = this.limit;
                    }
                    else {
                        //this.splitProgress += line.length()*this.getSplitProgress();
                        System.out.println(line);
                        stringsInMemory[i] = line;
                        line = br.readLine();
                    }
                }
            }
            else {
                this.stopRequested = true;
            }
        }
        catch (IOException ex) {
            System.err.println("IOException Error: " + ex.getMessage());
            System.err.println("Please make sure both files are valid!");
            this.stopRequested = true;
        }

        return stringsInMemory;
    }

    private void writeToTemp(String[] currentStrings) {
        try {
            this.fileCounter++;
            String fileString = this.temp + this.fileCounter + ".txt";
            q.add(fileString);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileString));
            for (String str : currentStrings) {
                bw.append(str);
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void merge() {
        try {
            for (int i = 1; i < this.fileCounter - 1; i++) {
                BufferedReader br1 = new BufferedReader(new FileReader(q.poll()));
                String file2 = q.poll();
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                BufferedWriter bw = new BufferedWriter(new FileWriter(file2));

                String[] br1Array = new String[this.limit];
                String[] br2Array = new String[this.limit];
                int index = 0;
                String line = "";

                while ((line = br1.readLine()) != null) {
                    //process the text for each line
                    br1Array[index++] = line;
                }

                index = 0;

                while ((line = br2.readLine()) != null) {
                    //process the text for each line
                    br2Array[index++] = line;
                }

                for (int j = 0; j < this.limit * 2; j++) {
//                    if (br1Array[j] <= br2Array[j]) {
//                        
//                    }
                }
            }

        }
        catch (IOException ex) {
            Logger.getLogger(FileSorter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
