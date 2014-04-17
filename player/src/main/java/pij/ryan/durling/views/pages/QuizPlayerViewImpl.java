package pij.ryan.durling.views.pages;

import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pij.ryan.durling.controllers.QuizPlayer;
import pij.ryan.durling.messages.ViewMessages;
import pij.ryan.durling.models.Answer;
import pij.ryan.durling.models.Question;
import pij.ryan.durling.models.QuizOption;

import java.util.Set;

public class QuizPlayerViewImpl extends StackPane implements QuizPlayerView {
    private QuizPlayer quizPlayer;
    private Views views;
    private Header header;
    private ViewPane viewPane;
    private Footer footer;
    private BorderPane borderPane;

    @Inject
    public QuizPlayerViewImpl(QuizPlayer quizPlayer, Views views) {
        this.quizPlayer = quizPlayer;
        this.views = views;
        this.setId(ViewMessages.QUIZ_PLAYER_ID);
        this.getStylesheets().add(ViewMessages.QUIZ_PLAYER_VIEW_STYLE_SHEET);


        borderPane = new BorderPane();
        header = views.getHeader();
        borderPane.setTop((Node) header);
        viewPane = views.getViewPane();
        borderPane.setCenter((Node) viewPane);
        footer = views.getFooter();
        borderPane.setBottom((Node) footer);
        viewPane.setView((Node) getSignInView(header, viewPane));
        this.getChildren().add(borderPane);
    }

    private SignInView getSignInView(Header header, ViewPane viewPane) {
        SignInView signInView = views.getSignInView();

        signInView.getSignInButton().setOnAction(e -> {
            header.setPlayerName(signInView.getName());
            viewPane.setView((Node) getMenuView());
        });
        return signInView;
    }

    private MenuView getMenuView() {
        MenuView menuView = views.getMenuView();
        int y = 0;
        for (QuizOption option : quizPlayer.getMenu()) {
            Button button = getButton(option.getQuizTitle(), y);
            button.setOnAction(e -> {
                header.setQuizTitle(option.getQuizTitle());
                getQuestionView(option);
            });
            menuView.addOption(button, y++);
        }
        return menuView;
    }

    private void getQuestionView(QuizOption option) {
        Button submitButton = getButton();
        footer.setSubmitButton(submitButton);
        quizPlayer.chooseQuiz(option.getQuizId());
        ScrollPane questions = getQuestions();
        viewPane.setView(questions);
    }

    private ScrollPane getQuestions() {
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        int y = 0;
        for(Question question : quizPlayer.getQuestions()) {
            QuestionView questionView = views.getQuestionView(question, getAnswers(question.getAnswers(), question));
            gridPane.add((Node) questionView, 0, y++);
        }
        scrollPane.setContent(gridPane);
        return scrollPane;
    }

    private GridPane getAnswers(Set<Answer> answers, Question question) {
        AnswerView answerView = views.getAnswerView();
        int y = 0;
        for (Answer answer : answers) {
            RadioButton radioButton = new RadioButton();
            radioButton.setId(ViewMessages.ANSWER_ID + y);
            radioButton.setOnAction(e -> {
                if(answer.getValue()) {
                    quizPlayer.addScore(question.getValue());
                }
            });
            answerView.addAnswer(radioButton, answer.getAnswer(), y++);
        }
        return (GridPane) answerView;
    }

    private Button getButton() {
        Button submitButton = new Button();
        submitButton.setOnAction(e -> {
            quizPlayer.submitQuiz();
            getResultsView();
        });
        submitButton.setText(ViewMessages.SUBMIT);
        return submitButton;
    }


    public void getResultsView() {
        ResultsView resultsView = views.getResultsView();
        resultsView.setResults(quizPlayer.hasWon(), quizPlayer.getScore());
        viewPane.setView((Node) resultsView);
        getNewQuizButton();
    }

    private void getNewQuizButton() {
        Button newQuizButton = new Button();
        newQuizButton.setText(ViewMessages.PLAY_AGAIN);
        newQuizButton.setOnAction(e -> {
            header.setQuizTitle("");
            viewPane.setView((Node) getMenuView());
            footer = views.getFooter();
            borderPane.setBottom((Node) footer);
        });
        footer.setSubmitButton(newQuizButton);
    }

    private Button getButton(String name, int id) {
        Button button = new Button();
        button.setText(name);
        button.setId(ViewMessages.MENU_VIEW_BUTTON_ID + id);
        return button;
    }
}
