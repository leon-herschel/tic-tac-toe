package tictactoe;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class ShadowButton extends JButton {
    public ShadowButton(String text) {
        super(text);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(Font.BOLD, 16));
        setPreferredSize(new Dimension(200, 40));
        LayerUI<JButton> layerUI = new ShadowLayerUI();
        JLayer<JButton> layer = new JLayer<>(this, layerUI);
        add(layer);
    }

    private static class ShadowLayerUI extends LayerUI<JButton> {
        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = c.getWidth();
            int height = c.getHeight();
            Color shadowColor = new Color(0, 0, 0, 80);
            int shadowSize = 5;
            int cornerSize = 10;
            int arrowSize = 20;
            int x = 0;
            int y = 0;
            Shape shape = null;

            if (c instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) c;
                if (button.getModel().isPressed()) {
                    x += 1;
                    y += 1;
                }
            }
            shape = new RoundRectangle2D.Float(x, y, width - shadowSize, height - shadowSize, cornerSize, cornerSize);
            g2.setColor(shadowColor);
            g2.translate(shadowSize, shadowSize);
            g2.fill(shape);
            g2.translate(-shadowSize, -shadowSize);
            g2.dispose();
        }
    }
}

