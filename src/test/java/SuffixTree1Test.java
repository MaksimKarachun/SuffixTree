import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import suffix_tree.SuffixTree;
import suffix_tree.SuffixTree1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SuffixTree1Test {

    private SuffixTree1 suffixTree1;
    private String text;

    @BeforeEach
    void setUp() {
        text = "atabdsf alert sdfasdasd alert sadsadsd";
        suffixTree1 = new SuffixTree1(text);
    }

    @Test
    void searchTest(){
        List<Integer> result = suffixTree1.search("alert");
        List<Integer> expected = new ArrayList<>(Arrays.asList(8, 24));

        List<Integer> nullresult = suffixTree1.search("NOT FOUND");

        assertEquals(expected, result);
        assertNull(nullresult);
    }


}
