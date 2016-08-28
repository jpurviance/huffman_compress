/**
 * Data encapsulation for char realized in file.
 *
 * Created by John on 7/31/16.
 */
public class Frequency {
    private int appearances; // times in file
    private char am; // char this is.

    /**
     * Use first time found
     * @param letter char
     */
    public Frequency(char letter){
        this.am = letter;
        this.appearances = 0;
    }

    /**
     * return time in file
     * @return times in file
     */
    public int get_appearances() {
        return this.appearances;
    }

    /**
     * return type of char
     * @return type of char
     */
    public char get_am() {
        return this.am;
    }

    /**
     * increment times in file.
     */
    public void appeared(){
        this.appearances++;
    }
}
