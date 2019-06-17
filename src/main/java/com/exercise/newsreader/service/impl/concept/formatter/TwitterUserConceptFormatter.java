package com.exercise.newsreader.service.impl.concept.formatter;


public class TwitterUserConceptFormatter implements ConceptFormatter {

    public String format(String username){

        return new String("<a href= http://twitter.com/"+username+">"+username+"</a>");
    }
}
