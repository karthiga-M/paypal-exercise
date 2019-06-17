package com.exercise.newsreader.service.impl.concept.formatter;

import com.exercise.newsreader.domain.ConceptType;
import com.exercise.newsreader.exception.ConceptFormatterCreationException;
import com.exercise.newsreader.exception.InvalidConceptIndexException;
import com.exercise.newsreader.exception.InvalidConceptTypeException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConceptFormatterFactory {

    private static Map<ConceptType, ConceptFormatter> conceptFormatterMap = new ConcurrentHashMap<>();

    public static ConceptFormatter getFormatter(ConceptType conceptType) {
        if(conceptType == null) { throw new InvalidConceptTypeException("Null conceptType"); }

        if (!conceptFormatterMap.containsKey(conceptType)) {
            conceptFormatterMap.put(conceptType, createFormatter(conceptType));
        }

        return conceptFormatterMap.get(conceptType);
    }

    private static ConceptFormatter createFormatter(ConceptType conceptType) {

        ConceptFormatter formatter = null;

        Class formatterclass = conceptType.getFormatterClass();
        try {
            formatter = (ConceptFormatter) formatterclass.newInstance();
        } catch (InstantiationException e) {
            throw new ConceptFormatterCreationException("Cannot create formatter for contetType" + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new ConceptFormatterCreationException("Cannot create formatter for contetType" + e.getMessage());
        }

        return formatter;
    }
}
