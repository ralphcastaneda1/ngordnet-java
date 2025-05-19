package main;

import org.eclipse.jetty.util.IO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

public class TestWordNet {
    @Test
    public void testHyponymsSimple() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        Set<String> hyponyms = wn.findCommonHyponyms("antihistamine");
        assertThat(hyponyms).contains("antihistamine");
        assertThat(hyponyms).contains("actifed");
    }


    @Test
    public void testImaginaryWord() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        assertTrue(wn.findCommonHyponyms("61b").isEmpty());
    }

    @Test
    public void testEmpty() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt","./data/wordnet/hyponyms11.txt");
        assertTrue(wn.findCommonHyponyms(" ").isEmpty());
    }

    @Test
    public void testHypDesc() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        Set<String> descentHyponyms = wn.findCommonHyponyms("descent");
        System.out.println("Descent Hyponyms: " + descentHyponyms);
        Assertions.assertNotNull(descentHyponyms);
        Assertions.assertTrue(descentHyponyms.contains("parachuting"));

    }


    @Test
    public void testHypChange() throws IOException {
        WordNet wn = new WordNet("/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/synsets.txt"
                , "/Users/ralphcastaneda/Desktop/cs61b/sp24-s579/proj2b/data/wordnet/hyponyms.txt");
        Set<String> changeHyponyms = wn.findCommonHyponyms("change");

        System.out.println("Change Hyponyms: " + changeHyponyms);


        Assertions.assertNotNull(changeHyponyms);
        Assertions.assertTrue(changeHyponyms.contains("change"));

        for (String hyp : changeHyponyms) {
            if (!hyp.equals("change")) {
                if (hyp.contains("change")) {
                    System.out.println("Hyponym containing 'change': " + hyp);
                }
                Assertions.assertFalse(hyp.equals("change"));
            }
        }
    }

    @Test
    public void testChange16Text() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        Set<String> changeHyp = wn.findCommonHyponyms("change");

        List<String> expectedList = List.of("alteration", "change", "demotion", "increase", "jump", "leap", "modification", "saltation", "transition", "variation");
        List<String> actualList = changeHyp.stream().sorted().collect(Collectors.toList());

        System.out.println("Change Hyponyms: " + changeHyp);

        Assertions.assertNotNull(changeHyp);
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void testHyponymsVideoRecording() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets.txt", "./data/wordnet/hyponyms.txt");
        List<String> words = Arrays.asList("video", "recording");
        Set<String> hyponyms = wn.findCommonHyponyms(words);
        Set<String> expectedHyponyms = new HashSet<>(Arrays.asList("video", "video_recording", "videocassette", "videotape"));
        System.out.println("Expected hyponyms for 'video' and 'recording': " + expectedHyponyms);
        System.out.println("Actual hyponyms found: " + hyponyms);
        Assertions.assertEquals(expectedHyponyms, hyponyms);
    }

    @Test
    public void testHyponymsPastryTart() throws IOException {
        WordNet wn = new WordNet("./data/wordnet/synsets.txt", "./data/wordnet/hyponyms.txt");
        List<String> words = Arrays.asList("pastry", "tart");
        Set<String> hyponyms = wn.findCommonHyponyms(words);
        Set<String> expectedHyponyms = new HashSet<>(Arrays.asList("apple_tart", "lobster_tart", "quiche", "quiche_Lorraine", "tart", "tartlet"));
        System.out.println("Expected hyponyms for 'pastry' and 'tart': " + expectedHyponyms);
        System.out.println("Actual hyponyms found: " + hyponyms);
        Assertions.assertEquals(expectedHyponyms, hyponyms);
    }
}

