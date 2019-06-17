package com.exercise.newsreader.domain;

public class Concept {


    private final ConceptType type;
    private final int startIndex;
    private final int endIndex;

    public ConceptType getType() {
        return type;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public Concept(ConceptType conceptType, int startIndex, int endIndex) {
        this.type = conceptType;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}
