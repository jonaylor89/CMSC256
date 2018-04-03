/*******************************************
 * John Naylor
 * CMSC 256, Section 2
 * Project 5 - Music Manager
 * Lex a xml-like file into MySong objects
 *******************************************/
public class MySong extends Song implements Comparable{

    private int playcount;

    public MySong(String title, String album, String artist, int playcount) {
        super(title, album, artist);

        this.playcount = playcount;
    }

    /********************************
     * @return the playcount
     *******************************/
    public int getPlaycount() {
        return playcount;
    }

    /**********************************************
     * @param playcount the playcount to set
     ********************************************/
    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    /***********************************************
     * @return String representation of mysong
     ***********************************************/
    @Override
    public String toString() {
        return String.format("Title: %s \nArtist: %s \nAlbum: %s \nPlaycount: %d", getArtist(), getAlbum(), getTitle(), playcount);
    }

    @Override
    public int compareTo(Object obj) {

        if(this == obj) {
            return 0; 
        }

        if(obj == null) {
            return -1; 
        }

        MySong other = (MySong) obj;

        if (this.playcount < other.getPlaycount())
            return -1;
        else if (this.playcount > other.getPlaycount())
            return 1;
        else
            return 0;

    }
}