# ngordnet-java
Java backend for historical word-usage and WordNet hyponym queries 


Ngordnet-Java is the Java server and library implementation for a browser-based historical word-usage viewer. It

Parses and stores 1-gram N-gram datasets into an efficient TimeSeries and NGramMap structure.

Implements time-series operations (e.g. plus, dividedBy, yearRange) and frequency-ranking queries (top N words).

Provides a clean HistoryHandler interface (with both text and binary implementations) for serving JSON responses.

Exposes an HTTP JSON API (NgordnetServer) for queries like COUNT, TOP_N, HYPONYMS, and HISTORY, powering the supplied front-end in static/.

Key components:

TimeSeries.java — a year→value map with arithmetic and slicing utilities

NGramMap.java — loader & query engine for word counts and weights

HistoryHandler.java / HistoryTextHandler.java — JSON adapters for front-end consumption

NgordnetServer.java — lightweight HTTP server dispatching queries
