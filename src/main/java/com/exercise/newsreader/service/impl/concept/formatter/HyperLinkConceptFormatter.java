package com.exercise.newsreader.service.impl.concept.formatter;

public class HyperLinkConceptFormatter implements ConceptFormatter {
    public String format(String hyperlink){

        return new String("<a href="+hyperlink+">"+hyperlink+"</a>");
    }
}
