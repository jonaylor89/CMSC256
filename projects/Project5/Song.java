/**************************************************
 * John Naylor
 * CMSC 256 Section 2 Spring
 * Project 4 Song project
 * Class to excapsulate details about songs
 ***********************************************/

public class Song implements Comparable {

    private String album;
    private String title;
    private String artist;

    /***************************************
     * @param album The song's album
     * @param title The song's title
     * @param artist The song's artist
     **************************************/
    public Song(String title, String album, String artist) {
        setAlbum(album);
        setTitle(title);
        setArtist(artist);
    }

    /**************************************
     * @param album the album to set
     ***********************************/
    public void setAlbum(String album) {
        if (album != null && !album.equals(""))
            this.album = album;
        else {
            this.album = "unknown";
        }
    }

    /*************************************
     * @param artist the artist to set
     ************************************/
    public void setArtist(String artist) {
        if (album != null && !artist.equals(""))
            this.artist = artist;
        else {
            this.artist = "unknown";
        }
    }

    /***********************************
     * @param title the title to set
     **********************************/
    public void setTitle(String title) {
        if (album != null && !title.equals(""))
            this.title = title;
        else {
            this.title = "unknown";
        }
    }

    /**************************
     * @return the album
     **************************/
    public String getAlbum() {
        return album;
    }

    /****************************
     * @return the artist
     ***************************/
    public String getArtist() {
        return artist;
    }

    /******************************
     * @return the title
     *****************************/
    public String getTitle() {
        return title;
    }

    /***************************************
     * @return If two songs are equal
     **************************************/
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Song s = (Song) obj;

        if (this.title == s.title && this.album == s.title && this.artist == s.artist) {
            return true;
        }

        return false;                               
    }

    /***********************************************
     * @return String representation of a song
     ***********************************************/
    @Override
    public String toString() {
        return String.format("Title: %s \nArtist: %s \nAlbum: %s", title, artist, album);
    }

    /**************************************
     * @return Comparison of two songs
     *************************************/
    @Override
    public int compareTo(Object obj) {

        if(this == obj) {
            return 0; 
        }

        if(obj == null) {
            return -1; 
        }

        Song other = (Song) obj;

        if (this.album.compareTo(other.getArtist()) != 0) {
            return this.album.compareTo(other.getArtist());
        }

        if (this.album.compareTo(other.getAlbum()) != 0) {
            return this.album.compareTo(other.getAlbum());
        }

        if (this.album.compareTo(other.getTitle()) != 0) {
            return this.album.compareTo(other.getTitle());
        }

        // Check to make sure everything is working
        assert this.equals(other) : "compareTo inconsistent with equals.";

        return 0;
    }

}