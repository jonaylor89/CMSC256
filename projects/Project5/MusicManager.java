
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**********************************************
 * John Naylor
 * CMSC 256, Section 2
 * Project 5 - Music Manager
 * Lex a xml-like file into MySong objects
 ********************************************/
public class MusicManager {
    public static void main(String[] argv) {
        printHeading();

        if (argv.length != 3) {
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
            System.out.println("No such file");
            System.exit(1);
        }

        try {
            reader = new SongReader(fileInput, errorFile);
        } catch (FileNotFoundException e)  {
            System.out.println("No such file");
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
                    songList.add(reader.parseSong() );
            } else {
               // errorString.append(curToken + " ");
            }
        }

        for (MySong s : songList) {
            System.out.println(s);
        }


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