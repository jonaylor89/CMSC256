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
    public Song(String album, String title, String artist) {
        setAlbum(album);
        setTitle(title);
        setArtist(artist);
    }

    /**************************************
     * @param album the album to set
     ***********************************/
    public void setAlbum(String album) {
        this.album = album;
    }

    /*************************************
     * @param artist the artist to set
     ************************************/
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /***********************************
     * @param title the title to set
     **********************************/
    public void setTitle(String title) {
        this.title = title;
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
     * @return If two songs are easier
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
        return String.format("%s-%s-%s", artist, album, title);
    }

    /**************************************
     * @return Comparison of two songs
     *************************************/
    @Override
    public int compareTo(Object obj) {
        return 0;                                                // TODO
    }

}