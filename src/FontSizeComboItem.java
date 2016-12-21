import javax.swing.*;

/**
 * Created by christian on 12/18/16.
 * represents a font size item within a dropdown menu
 */
public class FontSizeComboItem {
    private int size;

    public FontSizeComboItem(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return Integer.toString(size);
    }

    public int getSize() {
        return size;
    }
}
