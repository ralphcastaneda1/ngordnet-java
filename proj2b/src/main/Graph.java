package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//import java.util.stream.Collectors;

public class Graph {
    private Map<Integer, Set<String>> synsets;
    private Map<Integer, Set<Integer>> hyponymRelations;

    public Graph(String synsets, String hyponyms) {
        this.synsets = new HashMap<>();
        this.hyponymRelations = new HashMap<>();
        try {
            parseSynsets(synsets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            parseHyponyms(hyponyms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseSynsets(String file) throws IOException {
        //@Source https://www.w3schools.com/java/java_try_catch.asp
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                int id = Integer.parseInt(part[0]);
                String[] words = part[1].split(" ");
                synsets.put(id, new HashSet<>(Arrays.asList(words)));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void parseHyponyms(String file) throws IOException {
        //@Source https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                int parentId = Integer.parseInt(part[0]);
                hyponymRelations.putIfAbsent(parentId, new HashSet<>());
                for (int i = 1; i < part.length; i++) {
                    int hyponId = Integer.parseInt(part[i]);
                    hyponymRelations.get(parentId).add(hyponId);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> getHyponyms(int synsetId) {
        Set<Integer> allHyp = new HashSet<>();
        System.out.println("getHyponyms called with synsetId: " + synsetId);
        if (hyponymRelations.containsKey(synsetId)) {
            dfs(synsetId, allHyp);
        } else {
            System.out.println("No hyponym relations found for synsetId: " + synsetId);
        }
        return allHyp;
    }

    public void dfs(int sysnetId, Set<Integer> visited) {
        if (visited.contains(sysnetId)) {
            return;
        }
        System.out.println("Visiting synset ID: " + sysnetId);
        visited.add(sysnetId);
        if (hyponymRelations.containsKey(sysnetId)) {
            for (int adjsynid : hyponymRelations.get(sysnetId)) {
                dfs(adjsynid, visited);
            }
        }
    }

    public Set<String> findHyponyms(String word) {
        Set<String> hyponyms = new HashSet<>();
        for (Map.Entry<Integer, Set<String>> entry : synsets.entrySet()) {
            if (entry.getValue().contains(word)) {
                hyponyms.addAll(exploreHyp(entry.getKey()));
            }
        }
        return hyponyms;
    }

    public Set<String> exploreHyp(Integer synsetId) {
        Set<String> hyponyms = new HashSet<>();
        if (synsets.containsKey(synsetId)) {
            hyponyms.addAll(synsets.get(synsetId));
        }
        if (hyponymRelations.containsKey(synsetId)) {
            for (Integer hypid : hyponymRelations.get(synsetId)) {
                hyponyms.addAll(exploreHyp(hypid));
            }
        }
        return hyponyms;
    }

    public Set<String> findHyponyms(List<String> words) {
        if (words == null || words.isEmpty()) {
            return new HashSet<>();
        }

        Set<String> commonHyponyms = new HashSet<>(findHyponyms(words.get(0)));
        for (int i = 1; i < words.size(); i++) {
            commonHyponyms.retainAll(findHyponyms(words.get(i)));
            if (commonHyponyms.isEmpty()) {
                return commonHyponyms;
            }
        }
        return commonHyponyms;
    }

}







