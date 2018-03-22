/***********************************************
 * John Naylor
 * CMSC 256 Section 2 
 * Project 4 Song Reader
 * Parse XML-like files for songs
 *********************************************/

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class SongReader {

    public static void main(String[] argv) {
        printHeading();

        String fileName;

        if (argv.length == 1) {
            fileName = argv[0];
        } else {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter filename");
            fileName = userInput.next();
            userInput.close();
        }

        Scanner fileInput = new Scanner(new File(fileName));
        ArrayList<Song> songList = new ArrayList<Song>();

        while(fileInput.hasNextLine() && fileInput.hasNext()) {
            String curToken = fileInput.next();
            
            if (curToken.equals("<song>")) {
                parseSong(fileInput, songList);
            } else {
                System.out.println("Syntax Error:" + curToken);
            }
        }

        fileInput.close();
    }

    public static void parseSong(Scanner input, List songList) {
        String curToken;
        String artist = "";
        String title = "";
        String album = "";

        while (!(curToken = input.next()).equals("</song>")) {
            switch (curToken) {
                case "<artist>":
                    artist = parseArtist(input);
                    break;
                case "<title>":
                    title = parseTitle(input);
                    break;
                case "<album>":
                    album = parseAlbum(input);
                    break;
            }
        }

        songList.add(new Song(album, title, artist));
    }

    public static String parseArtist(Scanner input) {
        String curToken;
        StringBuilder retval = new StringBuilder();

        while (!(curToken = input.next()).equals("</artist>")) {
            retval.append(curToken);
        }

        return retval.toString();
    }
    
    public static String parseTitle(Scanner input) {
        String curToken;
        StringBuilder retval = new StringBuilder();

        while (!(curToken = input.next()).equals("</title>")) {
            retval.append(curToken);
        }
        return retval.toString();
    }

    public static String parseAlbum(Scanner input) {
        String curToken;
        StringBuilder retval = new StringBuilder();
        
        while (!(curToken = input.next()).equals("</album>")) {
            retval.append(curToken);
        }

        return retval.toString();
    }

    private static void printHeading() {
        System.out.println("John Naylor");
        System.out.println("CMSC 256 Section 2");
        System.out.println("Project 4 Song Parser Class");
        System.out.println("Parse XML-like files for songs");
    }
}