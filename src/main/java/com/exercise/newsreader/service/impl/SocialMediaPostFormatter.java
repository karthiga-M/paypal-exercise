package com.exercise.newsreader.service.impl;

import com.exercise.newsreader.domain.Concept;
import com.exercise.newsreader.exception.InvalidConceptIndexException;
import com.exercise.newsreader.service.PostFormatter;
import com.exercise.newsreader.service.impl.concept.formatter.ConceptFormatter;

import java.util.List;

import static com.exercise.newsreader.service.impl.concept.formatter.ConceptFormatterFactory.*;

public class SocialMediaPostFormatter implements PostFormatter {

    @Override
    public String format(String postToFormat, List<Concept> conceptList) {
        if (postToFormat == null || postToFormat.isEmpty()) {
            return postToFormat;
        }
        String formattedPost = postToFormat;
        for (Concept concept : conceptList) {
            String conceptValue = getConceptValue(postToFormat, concept);
            String formattedConceptValue = getFormattedConceptValue(conceptValue, concept);
            formattedPost = formattedPost.replace(conceptValue, formattedConceptValue);
        }
        return formattedPost;
    }

    private String getFormattedConceptValue(String conceptValue, Concept concept) {
        ConceptFormatter formatter =  getFormatter(concept.getType());
        String formattedConceptValue = conceptValue;
        if(formatter == null) {
            System.out.println("Formatter is null");
            return formattedConceptValue;
        }
         formattedConceptValue = formatter.format(conceptValue);
        return formattedConceptValue;
    }

    private String getConceptValue(String postToFormat, Concept concept){
        try {
            return postToFormat.substring(concept.getStartIndex(), concept.getEndIndex());
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Exception while retrieving substring :"+ e.getMessage());
            throw new InvalidConceptIndexException("Invalid index range, startindex :"+concept.getStartIndex()+" , endindex :"+concept.getEndIndex());
        }
    }
}