package pij.ryan.durling.views.pages;

import javafx.scene.control.Button;

public interface SignIn {

    /**
     * Get the users name
     *
     * @return String
     */
    String getName();

    /**
     * get the sign in button
     *
     * @return Button
     */
    Button getSignInButton();
}
