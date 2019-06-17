package com.exercise.newsreader.service.impl.concept.formatter;


public class EntityConceptFormatter implements ConceptFormatter {

    public String format(String entity){

        return new String("<strong>"+entity+"</strong>");
    }
}
