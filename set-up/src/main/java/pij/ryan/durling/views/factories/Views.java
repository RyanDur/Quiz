package pij.ryan.durling.views.factories;

import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.ViewBox;
import pij.ryan.durling.views.pages.Answers;
import pij.ryan.durling.views.pages.Questions;

public interface Views {

    ViewBox getErrorBox();

    Questions getQuestionView();

    Answers getAnswerView();

    Header getHeader();

    Footer getFooter();
}
