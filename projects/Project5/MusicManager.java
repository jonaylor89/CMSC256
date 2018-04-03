/**********************************************
 * John Naylor
 * CMSC 256, Section 2
 * Project 5 - Music Manager
 * Lex a xml-like file into MySong objects
 ********************************************/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class MusicManager {
    public static void main(String[] argv) {
        printHeading();

        if (argv.length != 3) {
            System.out.println("[!!!] Not enough arguments. Exiting");
            System.exit(1);
        }

        String fileName = argv[0];
        String option1 = argv[1];
        String option2 = argv[2];
        Scanner fileInput = null;
        PrintWriter errorFile = null;
        SongReader reader = null;

        try {
            fileInput = new Scanner(new File(fileName));
            errorFile = new PrintWriter("ErrorLog.txt");
        } catch (FileNotFoundException e) {
            System.out.println("[!!!] Input file does not exist");
            System.exit(1);
        }

        try {
            reader = new SongReader(fileInput, errorFile);
        } catch (FileNotFoundException e)  {
            System.out.println("[!!!] Error making song reader");
            System.exit(1);
        }

        ArrayList<MySong> songList = new ArrayList<MySong>();

        while (fileInput.hasNextLine() && fileInput.hasNext()) {
            String curToken = fileInput.next();
            
            if (curToken.equals("<song>")) {
                // if (errorString.length() != 0) {
                //     errorFile.println("*********Error********");
                //     errorFile.println(errorString.toString().trim() + " is outside a song tag");
                //     errorFile.println();
                // }
                // errorString.setLength(0);
                MySong newSong = reader.parseSong();

                if (newSong != null)
                    songList.add(newSong);
            } else {
               // errorString.append(curToken + " ");
            }
        }

        Collections.sort(songList, Collections.reverseOrder());

        switch (option2) {
            case "1":
                if (songList.size() > 10) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(songList.get(i));
                        System.out.println();
                    }
                } else {
                    for (MySong s : songList) {
                        System.out.println(s);
                        System.out.println();
                    }
                }
                break;
            case "2":
                for (MySong s : songList) {
                    if (s.getArtist().equals(option2)) {
                        System.out.println("Yes");
                        System.exit(0);
                    }
                }

                System.out.println("No");
                break;
            case "3":                                            // TODO: Third case needs some more work
                for (MySong s : songList) {
                    if (s.getArtist().equals(option2)) {
                        System.out.println(s);
                    }
                }
                break;
        }

        errorFile.close();
        fileInput.close();
    }

    /**************************************************
     * Mandatory header function
     ****************************************************/
    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("CMSC 256 Section 2");
        System.out.println("Project 5 - Music Manager");
        System.out.println("Lex a xml-like file into MySong objects");
        System.out.println();
    }
}