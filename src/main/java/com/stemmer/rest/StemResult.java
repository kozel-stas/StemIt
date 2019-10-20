package com.stemmer.rest;

import org.apache.commons.lang3.tuple.ImmutablePair;

class StemResult {

    private final ImmutablePair<String, String> pair;
    private final int wordCount;

    StemResult(ImmutablePair<String, String> pair, int wordCount) {
        this.pair = pair;
        this.wordCount = wordCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public ImmutablePair<String, String> getStem() {
        return pair;
    }
}
