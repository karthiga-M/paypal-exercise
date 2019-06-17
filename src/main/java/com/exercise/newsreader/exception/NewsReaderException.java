package com.exercise.newsreader.exception;

public abstract class NewsReaderException extends RuntimeException {
    public NewsReaderException(String message) {
        super(message);
    }
}
