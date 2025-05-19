package main;

//import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.Set;

public class WordNet {
    private Graph graph;

    public WordNet(String synset, String hyponyms)  {
        graph = new Graph(synset, hyponyms);
    }

    public Set<String> findCommonHyponyms(String word) {
        try {
            Set<String> hyponyms = graph.findHyponyms(word);
            if (hyponyms == null) {
                throw new IllegalStateException("Error finding hyponyms for word: " + word);
            }
            return hyponyms;
        } catch (Exception e) {
            throw new IllegalStateException("Error finding hyponyms for word: " + word, e);
        }
    }

    public Set<String> findCommonHyponyms(List<String> words) {
        if (words == null || words.isEmpty()) {
            return Collections.emptySet();
        }
        try {
            Set<String> commonHyponyms = graph.findHyponyms(words);
            if (commonHyponyms == null) {
                throw new IllegalStateException("Error finding common hyponyms for words: " + words);
            }
            return commonHyponyms;
        } catch (Exception e) {
            throw new IllegalStateException("Error finding common hyponyms for words: " + words, e);
        }
    }
}
