package by.bsuir.vladpr.window;

import by.bsuir.vladpr.AppTest;
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
            m = parser.parse(Objects.requireNonNull(AppTest.class.getClassLoader()
                    .getResource("cube.obj")).toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        double fov = Math.PI / 2;
        double aspect = (double) SCREEN_HEIGHT / (double) SCREEN_WIDTH;
        double near = 0.1;
        double far = 1000.0;

        Matrix4 transform = Matrix4.viewportTransformation(SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0);
        Matrix4 perspectiveProjection = Matrix4.perspectiveProjectionFOV(fov, aspect, near, far);
        Matrix4 viewMatrix = Matrix4.viewMatrix(
                new Vector3(0,0.2,0),
                new Vector3(0,0, 2),
                new Vector3(0,1,0)
        );
        transform = transform.multiply(perspectiveProjection)
                .multiply(viewMatrix)
                .multiply(Matrix4.translate(new Vector3(0,0,2)));
        for (Triangle triangle : m.getTriangles()) {
            Vector4[] vertices = triangle.getVertices();
            for (int i = 0; i < vertices.length; i++) {
                Vector4 out = vertices[i].multiply(transform);
                vertices[i] = out;
            }
        }

        for (Triangle triangle : m.getTriangles()) {
            Vector4[] vertices = triangle.getVertices();
            for (int i = 0; i < vertices.length; i++) {
                Vector4 currentVertex = vertices[i];
                Vector4 nextVertex = vertices[(i + 1) % vertices.length];
                int x1 = (int) Math.round(currentVertex.getX());
                int y1 = (int) Math.round(currentVertex.getY());
                int x2 = (int) Math.round(nextVertex.getX());
                int y2 = (int) Math.round(nextVertex.getY());
                Camera.drawLine(image, x1, y1, x2, y2);
            }
        }
        g.drawImage(image, 8, 31, null);
    }

}
