package com.exercise.newsreader;

import com.exercise.newsreader.domain.Concept;
import com.exercise.newsreader.domain.ConceptType;
import com.exercise.newsreader.exception.InvalidConceptIndexException;
import com.exercise.newsreader.exception.InvalidConceptTypeException;
import com.exercise.newsreader.service.PostFormatter;
import com.exercise.newsreader.service.impl.SocialMediaPostFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostFormatterTest {

    PostFormatter postFormatter = new SocialMediaPostFormatter();

    @Test
    public void shouldReturnFormattedPost() {
        String post = "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile";
        String expectedPost = "<strong>Obama</strong> visited <strong>Facebook</strong> headquarters: <a href=http://bit.ly/xyz>http://bit.ly/xyz</a> @<a href= http://twitter.com/elversatile>elversatile</a>";
        List<Concept> concepts = Arrays.asList(
                new Concept(ConceptType.ENTITY,14,22),
                new Concept(ConceptType.ENTITY,0,5),
                new Concept(ConceptType.TWITTER_USERNAME,56,67),
                new Concept(ConceptType.HYPERLINK,37,54)
        );
        String formattedPost = postFormatter.format(post,concepts);
        Assert.assertEquals(expectedPost,formattedPost);
    }

    @Test
    public void shouldReturnNullIfInputPostIsNull() {
        List<Concept> concepts = Arrays.asList(
            new Concept(ConceptType.ENTITY,14,22),
            new Concept(ConceptType.ENTITY,0,5),
            new Concept(ConceptType.TWITTER_USERNAME,56,67),
            new Concept(ConceptType.HYPERLINK,37,54)
        );

        Assert.assertEquals(null, postFormatter.format(null, concepts));
    }

    @Test
    public void shouldReturnSamePostIfConceptListIsEmpty() {
        String post = "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile";

        Assert.assertEquals(post, postFormatter.format(post, new ArrayList<Concept>()));

    }

    @Test(expected = InvalidConceptIndexException.class)
    public void shouldThrowExceptionIfConceptWithWrongIndex() {
        String post = "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile";
        List<Concept> concepts = Arrays.asList(
                new Concept(ConceptType.ENTITY,14,78),
                new Concept(ConceptType.ENTITY,0,5),
                new Concept(ConceptType.TWITTER_USERNAME,56,67),
                new Concept(ConceptType.HYPERLINK,37,54)
        );
        postFormatter.format(post,concepts);
    }

    @Test(expected = InvalidConceptTypeException.class)
    public void shouldReturnSameConceptIfConceptTypeIsInvalid()  {

        String post = "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile";
        List<Concept> concepts = new ArrayList<>();
        concepts.add(new Concept(null,14,22));
        concepts.add(new Concept(ConceptType.ENTITY,0,5));
        concepts.add(new Concept(ConceptType.TWITTER_USERNAME,56,67));
        concepts.add(new Concept(ConceptType.HYPERLINK,37,54));
        postFormatter.format(post,concepts);
    }
}

