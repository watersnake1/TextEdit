/**
 * Created by christian on 12/25/16.
 */
public class LangTypeComboItem {
    private boolean compiled;
    private String text;

    public LangTypeComboItem(boolean compiled, String text) {
        this.compiled = compiled;
        this.text = text;
    }

    /**
     * return the name of the language
     * @return
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Return if the language selected is a compiled langauge (like python) or not
     * @return
     */
    public boolean isCompiled() {
        return compiled;
    }
}
