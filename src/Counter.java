import java.io.*;
import java.util.ArrayList;

/**
 * Ingest file and compute how often chars exist.
 *
 * Created by John on 7/31/16.
 */
public class Counter {

    private BufferedReader br;

    /**
     * Open file for reading.
     * @param file_path file to count
     * @throws IOException Bad file given, cannot access.
     */
    public Counter(String file_path) throws IOException{
        File file = new File(file_path);
        FileInputStream stream = new FileInputStream(file);
        this.br = new BufferedReader(new InputStreamReader(stream));
    }

    /**
     * Resolve occurrences in file.
     * @return frequencies for each char.
     */
    public ArrayList<Frequency> count(){
        Frequency[] times = new Frequency[128]; // size of ascii table
        int chars = 0;
        try {
            int ascii_int = this.br.read();
            while (ascii_int > 0){ // There is another char
                if (times[ascii_int] == null){ // first time seen
                    times[ascii_int] = new Frequency((char) ascii_int);
                    chars++;
                }
                times[ascii_int].appeared(); // saw it again.
                ascii_int = this.br.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Prep for return.
        ArrayList<Frequency> counts = new ArrayList<Frequency>(chars);
        for (int i = 0; i < times.length; i++){
            if (times[i] != null){ // only want ones that appeared.
                counts.add(times[i]);
                chars++;
            }
        }
        return counts;
    }

}
