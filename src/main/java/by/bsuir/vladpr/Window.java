package by.bsuir.vladpr;

import by.bsuir.vladpr.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.Objects;

public class Window extends JFrame {

    private Point lastMousePosition = new Point(300, 400);
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

        double fov = Math.PI / 2;
        double near = 0.1;
        double far = 1000.0;
        camera = new Camera(SCREEN_HEIGHT, SCREEN_WIDTH, fov, near, far, image);
        camera.setPosition(new Vector4(0, 0, -7));
        Vector4 center = model.getCenter();
        model.translate(center.minus().vec3());

        this.addMouseWheelListener(this::rotateZModel);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePosition = new Point(e.getX(), e.getY());
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                rotateObject(e);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                double speed = 0.1;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    camera.setPosition(new Vector4(
                            camera.position().getX(),
                            camera.position().getY(),
                            camera.position().getZ() + speed
                    ));
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    camera.setPosition(new Vector4(
                            camera.position().getX(),
                            camera.position().getY(),
                            camera.position().getZ() - speed
                    ));
                } else {
                    return;
                }
                repaint();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Renderer");
        this.setVisible(true);
    }

    private void rotateObject(MouseEvent e) {
        int dx = e.getX() - lastMousePosition.x;
        int dy = e.getY() - lastMousePosition.y;
        double sensitivity = 0.1;
        model.rotateX(-dy*sensitivity);
        model.rotateY(dx*sensitivity);
        lastMousePosition = e.getPoint();
        repaint();
    }

    private void rotateZModel(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0)
            model.rotateZ(2);
        else
            model.rotateZ(-2);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
        g2d.dispose();

        camera.drawModel(model);
//        g.drawImage();
        g.drawImage(image, 8, 31, null);
    }

}
