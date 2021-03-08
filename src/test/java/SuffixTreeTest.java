import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import suffix_tree.SuffixTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SuffixTreeTest {

    private SuffixTree suffixTree;
    private String text;

    @BeforeEach
    void setUp() {
        text = "atabdsf alert sdfasdasd alert sadsadsd";
        suffixTree = new SuffixTree(text);
    }

    @Test
    void searchTest(){
        List<Integer> result = suffixTree.search("alert");
        List<Integer> expected = new ArrayList<>(Arrays.asList(8, 24));

        List<Integer> nullresult = suffixTree.search("NOT FOUND");

        assertEquals(expected, result);
        assertNull(nullresult);
    }


}
