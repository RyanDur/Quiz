package pij.ryan.durling.views.navigation;

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
import pij.ryan.durling.models.QuizOption;
import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.ViewPane;
import pij.ryan.durling.views.factories.Views;
import pij.ryan.durling.views.pages.*;

import java.util.Set;

public class QuizNavImpl extends StackPane implements QuizNav {
    private QuizPlayer quizPlayer;
    private Views views;
    private Header header;
    private ViewPane viewPane;
    private Footer footer;
    private BorderPane borderPane;

    @Inject
    public QuizNavImpl(QuizPlayer quizPlayer, Views views) {
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

    private SignIn getSignInView(Header header, ViewPane viewPane) {
        SignIn signIn = views.getSignInView();

        signIn.getSignInButton().setOnAction(e -> {
            quizPlayer.setPlayerName(signIn.getName());
            header.setPlayerName(signIn.getName());
            viewPane.setView((Node) getMenuView());
        });
        return signIn;
    }

    private Menu getMenuView() {
        Menu menu = views.getMenuView();
        Set<QuizOption> options = quizPlayer.getMenu();
        if(options.isEmpty()) {
            viewPane.setMessage(ViewMessages.CLOSED_QUIZ + "\n" +
                    ViewMessages.CHOOSE_ANOTHER);
        } else {
            setMenu(menu, 0);
        }
        return menu;
    }

    private void setMenu(Menu menu, int y) {
        for (QuizOption option : quizPlayer.getMenu()) {
            Button button = getButton(option.getQuizTitle(), y);
            button.setOnAction(e -> {
                try {
                    viewPane.setMessage("");
                    getQuestionView(option);
                    header.setQuizTitle(option.getQuizTitle());
                } catch (NullPointerException e1) {
                    if (quizPlayer.getMenu().isEmpty()) {
                        viewPane.setMessage(ViewMessages.CLOSED_QUIZ + "\n" +
                                ViewMessages.NO_QUIZZES);
                    }
                    viewPane.setView((Node) getMenuView());
                }
            });
            menu.addOption(button, y++);
        }
    }

    private void getQuestionView(QuizOption option) {
        Button submitButton = getButton();
        quizPlayer.chooseQuiz(option.getQuizId());
        viewPane.setView(getQuestions());
        footer.setSubmitButton(submitButton);
    }

    private ScrollPane getQuestions() {
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        int y = 0;
        for (pij.ryan.durling.models.Question question : quizPlayer.getQuestions()) {
            Question questionView = views.getQuestionView(question, getAnswers(question.getAnswers(), question));
            gridPane.add((Node) questionView, 0, y++);
        }
        scrollPane.setContent(gridPane);
        return scrollPane;
    }

    private GridPane getAnswers(Set<pij.ryan.durling.models.Answer> answers, pij.ryan.durling.models.Question question) {
        Answer answerView = views.getAnswerView();
        int y = 0;
        for (pij.ryan.durling.models.Answer answer : answers) {
            RadioButton radioButton = new RadioButton();
            radioButton.setId(ViewMessages.ANSWER_ID + y);
            radioButton.setOnAction(e -> {
                if (answer.getValue()) {
                    quizPlayer.addToScore(question.getValue());
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
        Results results = views.getResultsView();
        results.setResults(quizPlayer.hasWon(), quizPlayer.getScore(), quizPlayer.getOldCurrentWinner(), quizPlayer.getOldHighScore());
        viewPane.setView((Node) results);
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
