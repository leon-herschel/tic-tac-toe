package tictactoe;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedCorner extends BasicButtonUI {
    private int radius;

    public RoundedCorner(int radius) {
        this.radius = radius;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ButtonModel model = b.getModel();
        Color color = b.getBackground();
        int x = 0;
        int y = 0;
        int w = b.getWidth();
        int h = b.getHeight();

        if (model.isRollover()) {
            color = color.brighter();
        }

        if (model.isPressed()) {
            color = color.darker();
            x++;
            y++;
            w--;
            h--;
        }

        g2.setColor(color);
        g2.fillRoundRect(x, y, w, h, radius, radius);
        g2.setColor(b.getForeground());
        FontMetrics fm = g.getFontMetrics();
        Rectangle textRect = fm.getStringBounds(b.getText(), g).getBounds();
        int textX = b.getWidth() / 2 - textRect.width / 2;
        int textY = b.getHeight() / 2 - textRect.height / 2 + fm.getAscent();
        g2.drawString(b.getText(), textX, textY);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        d.setSize(d.width + radius, d.height + radius);
        return d;
    }
}
