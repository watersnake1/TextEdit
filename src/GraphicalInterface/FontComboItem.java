package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by christian on 12/18/16.
 * This object represenets a font within a jcombobox dropdown
 */
public class FontComboItem extends JButton {
    private String text;
    private Font font;
    private int size;

    public FontComboItem(String text, int size) {
        this.text = text;
        this.size = size;
        font = new Font(text, Font.PLAIN, size);

    }

    public FontComboItem(Font font) {
        this.font = font;
        this.text = font.getName();
    }

    /**
     * return the text of the combo item being displayed
     * @return
     */
    @Override
    public String toString() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
