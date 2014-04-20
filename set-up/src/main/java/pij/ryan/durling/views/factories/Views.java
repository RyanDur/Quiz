package pij.ryan.durling.views.factories;

import pij.ryan.durling.views.elements.Footer;
import pij.ryan.durling.views.elements.Header;
import pij.ryan.durling.views.elements.ViewBox;
import pij.ryan.durling.views.pages.Answers;
import pij.ryan.durling.views.pages.Questions;
import pij.ryan.durling.views.pages.SplitMenu;

public interface Views {

    /**
     * get the view box
     * @return Viewbox
     */
    ViewBox getViewBox();

    /**
     * get the question view
     * @return QuestionView
     */
    Questions getQuestionView();

    /**
     * get the answer view
     * @return AnswerView
     */
    Answers getAnswerView();

    /**
     * Get the header
     *
     * @return Header
     */
    Header getHeader();

    /**
     * get the footer
     * @return Footer
     */
    Footer getFooter();

    /**
     * get the split menu
     * @return SplitMenu
     */
    SplitMenu getSplitMenu();
}
