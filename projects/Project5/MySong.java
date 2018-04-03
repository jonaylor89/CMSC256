/*******************************************
 * John Naylor
 * CMSC 256, Section 2
 * Project 5 - Music Manager
 * Lex a xml-like file into MySong objects
 *******************************************/
public class MySong extends Song {

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
}