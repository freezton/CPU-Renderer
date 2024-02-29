package by.bsuir.vladpr.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) {
        Window window = new Window();
    }

    public Window() {
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.add(new JLabel(new ImageIcon(image)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Renderer");
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        image.setRGB(20, 20, Color.RED.getRGB());
        image.setRGB(21, 20, Color.GREEN.getRGB());
        image.setRGB(22, 20, Color.BLUE.getRGB());
        g.drawImage(image, 8, 31, null);
    }

}
