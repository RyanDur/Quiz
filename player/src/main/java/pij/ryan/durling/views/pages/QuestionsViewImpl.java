package pij.ryan.durling.views.pages;

import pij.ryan.durling.models.Question;

import java.util.Set;

public class QuestionsViewImpl implements QuestionsView {

    private Set<Question> questions;

    public QuestionsViewImpl(Set<Question> questions) {
        this.questions = questions;
    }
}
