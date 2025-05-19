package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
//import com.google.gson.internal.bind.util.ISO8601Utils;

//import java.io.IOException;
import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wordNet;

    public HyponymsHandler(String synsetFile, String hyponymFile)  {
        wordNet = new WordNet(synsetFile, hyponymFile);
    }
    @Override
    public String handle(NgordnetQuery q) {
        if (q.words().isEmpty()) {
            return new HashSet<String>().toString();
        }

        Set<String> commonHyponyms = new HashSet<>(wordNet.findCommonHyponyms(q.words().get(0)));
        for (int i = 1; i < q.words().size(); i++) {
            commonHyponyms.retainAll(wordNet.findCommonHyponyms(q.words().get(i)));
            if (commonHyponyms.isEmpty()) {
                break;
            }
        }

        List<String> sortedHyponyms = new ArrayList<>(commonHyponyms);
        Collections.sort(sortedHyponyms);
        return sortedHyponyms.toString();
    }
}
