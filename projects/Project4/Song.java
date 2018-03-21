/**
 * 
 */

public class Song implements Comparable{

    private String album;
    private String title;
    private String artist;

    public Song(String album, String title, String artist) {
        
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int compareTo(Object obj) {
        return 0;
    }
}