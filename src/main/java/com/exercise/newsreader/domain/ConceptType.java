package com.exercise.newsreader.domain;

import com.exercise.newsreader.service.impl.concept.formatter.EntityConceptFormatter;
import com.exercise.newsreader.service.impl.concept.formatter.HyperLinkConceptFormatter;
import com.exercise.newsreader.service.impl.concept.formatter.TwitterUserConceptFormatter;

public enum ConceptType {
    ENTITY(EntityConceptFormatter.class),
    HYPERLINK(HyperLinkConceptFormatter.class),
    TWITTER_USERNAME(TwitterUserConceptFormatter.class);

    private Class formatterClass;

    ConceptType(Class formatterClass ) { this.formatterClass = formatterClass; }

    public Class getFormatterClass() {
        return this.formatterClass;
    }
}
