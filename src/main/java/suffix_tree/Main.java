package suffix_tree;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String text = "They are Australian animals and they are the" +
                "symbols of the Sydney Games. The kookaburra is an Australian " +
                "bird. She got her name, Olly, from the word ‘Olympics’. " +
                "She’s a symbol of friendship and honesty.";

        SuffixTree suffixTree = new SuffixTree(text);

        List<Integer> result = suffixTree.search("Olympics");
        if (result != null)
        for (Integer pos : result){
            System.out.println(pos);
        }
        System.out.println(text.substring(150));

    }
}
