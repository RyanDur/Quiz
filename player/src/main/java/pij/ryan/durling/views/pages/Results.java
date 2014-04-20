package pij.ryan.durling.views.pages;

public interface Results {

    /**
     * Set the results page
     *
     * @param winner boolean
     * @param score int
     * @param oldWinner String
     * @param oldScore int
     */
    void setResults(boolean winner, int score, String oldWinner, int oldScore);
}
