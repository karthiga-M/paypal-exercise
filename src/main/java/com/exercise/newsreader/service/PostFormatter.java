package com.exercise.newsreader.service;

import com.exercise.newsreader.domain.Concept;
import com.exercise.newsreader.service.impl.SocialMediaPostFormatter;

import java.util.List;

public interface PostFormatter {
    String format(String post, List<Concept> conceptList);
}
