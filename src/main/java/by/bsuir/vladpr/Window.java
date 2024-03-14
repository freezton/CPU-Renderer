package by.bsuir.vladpr;

import by.bsuir.vladpr.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.Objects;

public class Window extends JFrame {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) {
        Window window = new Window();
    }

    public Window() {
        this.setResizable(false);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(new JLabel(new ImageIcon(image)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Renderer");
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Model m;
        ObjParser parser = new ObjParser();
        try {
            m = parser.parse(Objects.requireNonNull(this.getClass().getClassLoader()
                    .getResource("cube.obj")).toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
//        m.rotateY(50);
//        m.rotateY(-50);
//        m.rotateX(35);
        m.translate(new Vector3(-1, 0, 2));

        double fov = Math.PI / 2;
        double aspect = (double) SCREEN_HEIGHT / (double) SCREEN_WIDTH;
        double near = 0.1;
        double far = 1000.0;

        Camera camera = new Camera(SCREEN_HEIGHT, SCREEN_WIDTH, fov, near, far, image);
        camera.setPosition(new Vector4(0, 0, -1));
//        for (int i = 0; i < 1000000; i++) {
//            m.setIdentity(new Matrix(new double[][] {
//                    {1, 0, 0, 0},
//                    {0, 1, 0, 0},
//                    {0, 0, 1, 0},
//                    {0, 0, 0, 1}
//            }));
//            m.rotateX(1);
//            m.translate(new Vector3(-0.4, 0, 2));
            camera.drawModel(m);
            g.drawImage(image, 8, 31, null);
//        }
    }

}
