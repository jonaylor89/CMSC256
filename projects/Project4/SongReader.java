/***********************************************
 * John Naylor
 * CMSC 256 Section 2 Spring
 * Project 4 Song Reader
 * Parse XML-like files for songs
 *********************************************/

import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class SongReader {

    private static PrintWriter error;
    private static Scanner fileInput;
    private static List<Song> songList;

    /***************************************************************************
     * Intakes a file from either the command like or user prompt and parses
     * the songs in the file
     **************************************************************************/
    public static void main(String[] argv) {
        printHeading();

        String fileName;
        fileInput = null;

        if (argv.length == 1) {
            fileName = argv[0];
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter filename: ");
            fileName = userInput.next();
            userInput.close();
        }

        try {
            fileInput = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
            System.exit(1);
        }

        try {
            error = new PrintWriter("ErrorLog.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No error log file");
            System.exit(1);
        }

        songList = new ArrayList<Song>();

        while(fileInput.hasNextLine() && fileInput.hasNext()) {
            String curToken = fileInput.next();
            StringBuilder errorString = new StringBuilder();
            
            if (curToken.equals("<song>")) {
                if (errorString.length() != 0) {
                    error.println("Syntax Error <<" + errorString.toString().trim() + ">> is outside a song tag");
                    errorString.setLength(0);
                }
                parseSong();
            } else {
                errorString.append(curToken + " ");
            }
        }

        fileInput.close();
        error.close();

        for (Song s : songList) {
            System.out.println(s);
            System.out.println();
        }
    }

    /**********************************************************************************
     * Scans every token in the scanner until reaching an end tag where it finally 
     * creates the Song object
     ********************************************************************************/
    public static void parseSong() {
        String curToken;
        boolean songError = false;
        String artist = "";
        String title = "";
        String album = "";

        StringBuilder errorString = new StringBuilder();

        try {
            while (fileInput.hasNext() && !(curToken = fileInput.next()).equals("</song>")) {

                try {
                    switch (curToken) { 
                        case "<artist>":

                            if (errorString.length() != 0) {
                                error.println("Syntax Error << " + errorString.toString().trim() + " >> is outside a proper tag");
                                errorString.setLength(0);
                            }

                            artist = parseArtist();
                            if (artist.equals("")) {
                                error.println("Error: Artist not provided");
                            }
                            break;
                        case "<title>":

                            if (errorString.length() != 0) {
                                error.println("Syntax Error << " + errorString.toString().trim() + " >> is outside a proper tag");
                                errorString.setLength(0);
                            }

                            title = parseTitle();
                            if (title.equals("")) {
                                error.println("Error: Title not provided");
                            }
                            break;
                        case "<album>":

                            if (errorString.length() != 0) {
                                error.println("Syntax Error << " + errorString.toString().trim() + " >> is outside a proper tag");
                                errorString.setLength(0);
                            }

                            album = parseAlbum();
                            if (album.equals("")) {
                                error.println("Error: Album not provided");
                            }
                            break;
                        default: 
                            errorString.append(curToken + " ");
                            break;
                    }
                } catch (NoSuchElementException e) {
                    error.println(e.getMessage());
                    songError = true;
                }
            }

            if (errorString.length() != 0) {
                error.println("Syntax Error << " + errorString.toString().trim() + " >> is outside a proper tag");
                errorString.setLength(0);
            }

            if (!songError){
                songList.add(new Song(album.trim(), title.trim(), artist.trim()));
            }
        } catch (NoSuchElementException e) {
            error.println("Missing ending </song> tag");
        }
    }

    /*********************************************
     * Parse the artist tag
     *********************************************/
    public static String parseArtist() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        while (!(curToken = fileInput.next()).equals("</artist>")) {
            if (curToken.equals("<artist>")) {
                parseArtist();
                error.println("Syntax Error: " + curToken + " in artist tag");
            } else if (curToken.equals("<title>")) {
                parseTitle();
                error.println("Syntax Error: " + curToken + " in artist tag");
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                error.println("Syntax Error: " + curToken + " in artist tag");
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending artist tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        return retval.toString();
    }
    
    /*******************************************************
     * Parse the title tag
     ********************************************************/
    public static String parseTitle() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        while (!(curToken = fileInput.next()).equals("</title>")) {
            if (curToken.equals("<artist>")) {
                parseArtist();
                error.println("Syntax Error: " + curToken + " in title tag");
            } else if (curToken.equals("<title>")) {
                parseTitle();
                error.println("Syntax Error: " + curToken + " in title tag");
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                error.println("Syntax Error: " + curToken + " in title tag");
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending title tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        return retval.toString();
    }

    /*********************************************************
     * Parse album tag
     *******************************************************/
    public static String parseAlbum() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        while (!(curToken = fileInput.next()).equals("</album>")) {
            if (curToken.equals("<artist>")) {
                parseArtist();
                error.println("Syntax Error: " + curToken + " in album tag");
            } else if (curToken.equals("<title>")) {
                parseTitle();
                error.println("Syntax Error: " + curToken + " in album tag");
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                error.println("Syntax Error: " + curToken + " in album tag");
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending album tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        return retval.toString();
    }

    /**************************************************
     * Mandatory header function
     ****************************************************/
    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("CMSC 256 Section 2");
        System.out.println("Project 4 Song Parser Class");
        System.out.println("Parse XML-like files for songs");
        System.out.println();
    }
}