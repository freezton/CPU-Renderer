package by.bsuir.vladpr;

import by.bsuir.vladpr.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.Objects;

public class Window extends JFrame {

    public static boolean is = false;
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    Model model;
    Camera camera;

    public static void main(String[] args) {
        Window window = new Window();
    }

    public Window() {
        this.setResizable(false);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(new JLabel(new ImageIcon(image)));

        ObjParser parser = new ObjParser();
        try {
            model = parser.parse(Objects.requireNonNull(this.getClass().getClassLoader()
                    .getResource("cube.obj")).toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        model.setPosition(new Vector4(-1, 0, 1));


        double fov = Math.PI / 2;
        double near = 0.1;
        double far = 1000.0;

        camera = new Camera(SCREEN_HEIGHT, SCREEN_WIDTH, fov, near, far, image);
        camera.setPosition(new Vector4(0, 0, -2));
//        camera.translate(new Vector3(0, 0, -5));

        model.rotateX(50);
        model.rotateZ(20);

        this.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                image.setRGB(10, 10, Color.WHITE.getRGB());
                is = true;
                camera.translate(new Vector3(0, 0, -5));
//                model.rotateX(10);
//                model.setPosition(new Vector4(-1, 0, 2));
//                model.translate(new Vector3(-1, 0, 2));
                repaint();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Renderer");
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        camera.drawModel(model);
//        g.drawImage();
        g.drawImage(image, 8, 31, null);
    }

}
