package Tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class CircularLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public CircularLabel() {
        super();
        init();
    }

    public CircularLabel(String text) {
        super(text);
        init();
    }

    public CircularLabel(Icon icon) {
        super(icon);
        init();
    }

    public CircularLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    public CircularLabel(Icon icon, int horizontalAlignment) {
        super(icon, horizontalAlignment);
        init();
    }

    private void init() {
        setOpaque(false);  // Make the label transparent
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adjust padding as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast to Graphics2D for more advanced drawing operations
        Graphics2D g2d = (Graphics2D) g.create();

        // Set rendering hints for smoother graphics (optional)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the color and thickness of the circular border
        g2d.setColor(Color.RED);
        g2d.setStroke(new java.awt.BasicStroke(2));

        // Calculate the center and radius for the circle
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 2 - 1;

        // Draw the circular border
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Dispose of the Graphics2D object to free up resources
        g2d.dispose();
    }
}
