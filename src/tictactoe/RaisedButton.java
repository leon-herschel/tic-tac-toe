package tictactoe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class RaisedButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static final Color BUTTON_COLOR = new Color(200, 200, 200);
    private static final Color BUTTON_SHADOW_COLOR = new Color(150, 150, 150);

    public RaisedButton(String text) {
        super(text);
        setOpaque(false);
        setForeground(Color.WHITE);
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setBorder(BorderFactory.createCompoundBorder(
                new CustomBorder(),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        setContentAreaFilled(false);
    }

    private class CustomBorder extends AbstractBorder {
        private static final long serialVersionUID = 1L;

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(BUTTON_COLOR);
            g2.fillRoundRect(x, y, width - 1, height - 1, 20, 20);
            g2.setColor(BUTTON_SHADOW_COLOR);
            g2.drawRoundRect(x, y, width - 1, height - 1, 20, 20);
        }
    }
}

