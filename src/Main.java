import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by John on 7/31/16.
 *
 * A project to generate huffman codes for characters given their frequency in a document.
 *
 */
public class Main {
    /**
     * Prints huffman codes to standard out.
     *
     * @param args index '0' is to have the file path of the decrement to be evaluated.
     */
    public static void main(String[] args){
        try {
            Counter in_file = new Counter(args[0]);
            ArrayList<Frequency> file_properties = in_file.count(); // Computer frequency of chars.
            Huffman comp_engine = new Huffman();
            comp_engine.build(file_properties); // Construct tree for codes
            comp_engine.dump_codes(); // Print all the codes to standard out.
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

}
