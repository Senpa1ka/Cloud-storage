import javax.swing.*;
import java.awt.*;

class MainWindowController extends JButton {
    public MainWindowController(String label) {
        super(label);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }



    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    public boolean contains(int x, int y) {
        int radius = getWidth() / 2;
        return (x - radius) * (x - radius) + (y - radius) * (y - radius) <= radius * radius;
    }


}


