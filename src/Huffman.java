import java.util.ArrayList;

/**
 * Created by John on 7/31/16.
 *
 * Computation engine for determining Huffman tree codes for file compression.
 *
 * New instance should be made for every new file or file update.
 */
public class Huffman {

    /**
     * Dual purpose tree node class. Serves as a leaf or an internal node. This is important because sorting is
     * for code generation encounters trees and un-inserted nodes.
     */
    private class node{
        boolean is_leaf;
        int times;
        char am;
        node left;
        node right;

        /**
         * Internal tree node, has chars or other internal nodes below it.
         *
         * @param times sum of left and right children.
         */
        private node(int times){
            this.is_leaf = false; // has children, other nodes that eternally lead to char's
            this.times = times;
        }

        /**
         * Leaf node for end of sequence chars.
         *
         * @param am letter of alphabet or special char
         * @param times occurrences in file
         */
        private node(char am, int times){
            this.is_leaf = true;
            this.times = times;
            this.am = am;
        }
    }

    private ArrayList<node> trees;

    /**
     * Computation engine for huffman codes.
     * Expects that enigne is built before code dumping.
     */
    public Huffman(){
        this.trees = new ArrayList<node>();
    }

    /**
     * Quick sort pivot function for sorting
     * @param low lower bound
     * @param high upper bound
     * @return partition midpoint.
     */
    private int partition(int low, int high){
        node pivot = this.trees.get(high); // TODO this need a better name
        int i = (low-1);
        for (int j = low; j<=high-1; j++){ // TODO J needs a better name
            if (this.trees.get(j).times < pivot.times){
                i++;
                node tmp = this.trees.get(j);
                this.trees.set(j,this.trees.get(i));
                this.trees.set(i, tmp);

            }
        }
        node tmp = this.trees.get(i+1);
        this.trees.set(i+1,this.trees.get(high));
        this.trees.set(high, tmp);
        return i +1;
    }

    /**
     * Quick sort implementation for sorting huffman codes.
     * @param low bottom of section to sort
     * @param high top of section to sort
     */
    private void sort_by_freq(int low, int high){
        if (low < high){
            int pi = this.partition(low, high);
            this.sort_by_freq(low, pi-1);
            this.sort_by_freq(pi+1, high);
        }

    }

    /**
     * wrapper function to kick off the sorting of the nodes.
     */
    private void order(){
        this.sort_by_freq(0, this.trees.size()-1);
    }


    /**
     * Expects at least one Frequency will be give.
     * @param values values to compress.
     */
    public void build(ArrayList<Frequency> values){
        for (Frequency letter: values){
            trees.add(new node(letter.get_am(), letter.get_appearances())); // init tress ot all leaf nodes.
        }
        this.order(); // first ordering
        while (this.trees.size() > 1){
            node new_root = new node(this.trees.get(0).times + this.trees.get(1).times); // join left two
            new_root.left = this.trees.get(0);
            new_root.right = this.trees.get(1);
            this.trees.set(0, new_root); // new tree
            this.trees.remove(1); // remove old tree.
            this.order(); // order again
        }
    }

    /**
     * print codes to standard out.
     * @param root current tree
     * @param at codes for this tree
     */
    private void walk(node root, StringBuilder at){
        if (root.is_leaf){
            System.out.println(root.am +" code is: "+at.toString()); // dump code for char
        } else {
            // build left
            at.append('0');
            walk(root.left, at);
            at.deleteCharAt(at.length()-1);
            // build right
            at.append('1');
            walk(root.right,at);
            at.deleteCharAt(at.length()-1);
        }
    }

    /**
     * walk the tree, printing out the tree codes.
     * expects that the tree has been built.
     */
    public void dump_codes(){
        if (this.trees.size() != 1){
            System.out.println("The codes have not been generated yet");
        } else {
            StringBuilder code = new StringBuilder();
            this.walk(this.trees.get(0), code);
        }
    }
}
