/***********************************************
 * John Naylor
 * CMSC 256 Section 2 Spring
 * Project 5 Song Reader
 * Lex a xml-like file into MySong objects
 *********************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class SongReader {

    private PrintWriter error;
    private StringBuilder errorString;
    private boolean songError;
    private boolean criticalError;
    private Iterator<String> input;

    /*********************************************************************
     * Contructor initializes input interator and error output file
     ******************************************************************/
    public SongReader(Iterator<String> input, PrintWriter error) throws FileNotFoundException {
        this.input = input;
        this.error = error;
        errorString = new StringBuilder();
    }

    /**********************************************************************************
     * Scans every token in the scanner until reaching an end tag where it finally 
     * creates the Song object. This is the key method in the SongReader class.
     ********************************************************************************/
    public MySong parseSong() {
        String curToken;
        songError = false;
        criticalError = false;
        String artist = "";
        String title = "";
        String album = "";
        int playcount = 0;

        try {
            while (!criticalError && input.hasNext() && !(curToken = input.next()).equals("</song>")) {

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
                        case "<playcount>":
                            errorString.append("\n");
                            try {
                                playcount = Integer.valueOf(parsePlaycount());
                            } catch (NumberFormatException e) {
                                songError = true;
                                playcount = 0;
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

        } catch (NoSuchElementException e) {
            if (!songError){
                error.println("*********Error********");
                error.println(errorString.toString().trim());
                error.println();
            }
        }

        errorString.setLength(0);

        if (!criticalError){
            if (title == null) { System.out.println("WTF?!?!?"); }
            return new MySong(title, album, artist, playcount);
        }

        return null;
    }

    /*********************************************
     * Parse the artist tag
     *********************************************/
    private String parseArtist() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = input.next()).equals("</artist>")) {
            errorString.append(curToken + " ");
            if (curToken.equals("<artist>")) {
                parseArtist();
                songError = true;
            } else if (curToken.equals("<title>")) {
                parseTitle();
                songError = true;
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                songError = true;
            } else if (curToken.equals("<playcount>")) {
                parsePlaycount();
                songError = true;
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending artist tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken + " ");
        errorString.append("\n");
        return retval.toString().trim();
    }
    
    /*******************************************************
     * Parse the title tag
     ********************************************************/
    private String parseTitle() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = input.next()).equals("</title>")) {
            errorString.append(curToken + " ");
            if (curToken.equals("<artist>")) {
                parseArtist();
                songError = true;
            } else if (curToken.equals("<title>")) {
                parseTitle();
                songError = true;
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                songError = true;
            } else if (curToken.equals("<playcount>")) {
                parsePlaycount();
                songError = true;
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending artist tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken + " ");
        errorString.append("\n");
        return retval.toString().trim();
    }

    /*********************************************************
     * Parse album tag
     *******************************************************/
    private String parseAlbum() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = input.next()).equals("</album>")) {
            errorString.append(curToken + " ");
            if (curToken.equals("<artist>")) {
                parseArtist();
                songError = true;
            } else if (curToken.equals("<title>")) {
                parseTitle();
                songError = true;
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                songError = true;
            } else if (curToken.equals("<playcount>")) {
                parsePlaycount();
                songError = true;
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending artist tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken + " ");
        errorString.append("\n");

        return retval.toString().trim();
    }

    public String parsePlaycount() {
        String curToken;
        StringBuilder retval = new StringBuilder(" ");

        errorString.append("\t");

        while (!(curToken = input.next()).equals("</playcount>")) {
            errorString.append(curToken + " ");
            if (curToken.equals("<artist>")) {
                parseArtist();
                songError = true;
            } else if (curToken.equals("<title>")) {
                parseTitle();
                songError = true;
            } else if (curToken.equals("<album>")) {
                parseAlbum();
                songError = true;
            } else if (curToken.equals("<playcount>")) {
                parsePlaycount();
                songError = true;
            } else if (curToken.equals("</song>")) {
                throw new NoSuchElementException("No ending artist tag");
            } else {
                retval.append(curToken + " ");
            }
        }

        errorString.append("\n");
        errorString.append(curToken + " ");
        errorString.append("\n");
        return retval.toString().trim();
    }
}
