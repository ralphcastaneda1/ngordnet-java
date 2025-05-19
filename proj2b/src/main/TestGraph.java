package main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;

public class TestGraph {
    @Test
    public void testFindChildren() throws IOException {
        Graph graph = new Graph("/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/synsets.txt"
                , "/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/hyponyms.txt");
        Set<Integer> children = graph.getHyponyms(-8);
        assertNotNull(children);
    }

    @Test
    public void testFindHyponymsOfWord() throws IOException {
        Graph graph = new Graph("/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/synsets.txt"
                , "/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/hyponyms.txt");
        Set<String> hyponyms = graph.findHyponyms("antihistamine");
        assertNotNull(hyponyms);
        assertFalse(hyponyms.isEmpty());
    }

    @Test
    public void testInvalidWord() throws IOException {
        Graph graph = new Graph("/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/synsets.txt"
                , "/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/hyponyms.txt");
        Set<String> hyponyms = graph.findHyponyms("invalidword");
        assertTrue(hyponyms.isEmpty());
    }


}

