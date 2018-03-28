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
    private static StringBuilder errorString;
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
        errorString = new StringBuilder();

        while(fileInput.hasNextLine() && fileInput.hasNext()) {
            String curToken = fileInput.next();
            
            if (curToken.equals("<song>")) {
                if (errorString.length() != 0) {
                    error.println("*********Error********");
                    error.println(errorString.toString().trim() + " is outside a song tag");
                    error.println();
                }
                errorString.setLength(0);
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
        boolean criticalError = false;
        String artist = "";
        String title = "";
        String album = "";

        try {
            while (!criticalError && fileInput.hasNext() && !(curToken = fileInput.next()).equals("</song>")) {

                try {
                    errorString.append(curToken);
                    switch (curToken) { 
                        case "<artist>":
                            errorString.append("\n");
                            artist = parseArtist();
                            if (artist.equals("")) {
                                songError = true;
                            }
                            break;
                        case "<title>":
                            errorString.append("\n");
                            title = parseTitle();
                            if (title.equals("")) {
                                songError = true;

                            }
                            break;
                        case "<album>":
                            errorString.append("\n");
                            album = parseAlbum();
                            if (album.equals("")) {
                                songError = true;
                            }
                            break;
                        default: 
                            songError = true;
                            errorString.append(" ");
                            break;
                    }
                } catch (NoSuchElementException e) {
                    criticalError = true;
                }
            }

            if (songError) {
                error.println("*********Error********");
                error.println(errorString.toString().trim());
                error.println();
            }

            if (!criticalError){
                songList.add(new Song(album.trim(), title.trim(), artist.trim()));
            }
        } catch (NoSuchElementException e) {
            if (!songError){
                error.println("*********Error********");
                error.println(errorString.toString().trim());
                error.println();
            }
        }

        errorString.setLength(0);
    }

    /*********************************************
     * Parse the artist tag
     *********************************************/
    public static String parseArtist() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = fileInput.next()).equals("</artist>")) {
            errorString.append(curToken);
            if (curToken.equals("<artist>")) {
                parseArtist();
            } else if (curToken.equals("<title>")) {
                parseTitle();
            } else if (curToken.equals("<album>")) {
                parseAlbum();
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending artist tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken);
        errorString.append("\n");
        return retval.toString();
    }
    
    /*******************************************************
     * Parse the title tag
     ********************************************************/
    public static String parseTitle() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = fileInput.next()).equals("</title>")) {
            errorString.append(curToken);
            if (curToken.equals("<artist>")) {
                parseArtist();
            } else if (curToken.equals("<title>")) {
                parseTitle();
            } else if (curToken.equals("<album>")) {
                parseAlbum();
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending title tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken);
        errorString.append("\n");
        return retval.toString();
    }

    /*********************************************************
     * Parse album tag
     *******************************************************/
    public static String parseAlbum() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = fileInput.next()).equals("</album>")) {
            errorString.append(curToken + " ");
            if (curToken.equals("<artist>")) {
                parseArtist();
            } else if (curToken.equals("<title>")) {
                parseTitle();
            } else if (curToken.equals("<album>")) {
                parseAlbum();
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending album tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken);
        errorString.append("\n");
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

class Stack<T> {

    private Node<T> head;
    private int length;

    public Stack() {
        length = 0;
        head = null;
    }

    public void push(Node<T> n) {
        n.next = head;
        head = n;
        length++;
    }

    public T pop() {
        Node<T> n = head;
        head = head.next;
        length--;
        return n.data;
    }

    public void clear() {
        head = null;
        length = 0;
    }

}

class Node<T> {

    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}