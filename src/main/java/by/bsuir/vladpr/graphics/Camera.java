package by.bsuir.vladpr.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera extends Object3d {

    private int screenWidth;
    private int screenHeight;
    private double aspect;
    private double fov;
    private double near;
    private double far;
    private BufferedImage image;

    public Camera(int screenHeight, int screenWidth, double fov, double near, double far, BufferedImage image) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.far = far;
        this.fov = fov;
        this.near = near;
        this.aspect = (double) screenHeight / (double) screenWidth;
        this.image = image;
    }

    public void drawModel(Model model) {
//        Model model = new Model(new ArrayList<>(model1.getTriangles()));
//        model.setIdentity(model1.getIdentity());
//        model.setPosition(model1.getPosition());
//        model.translate(model.getPosition().vec3());

        model.translate(model.getPosition().vec3());
        Matrix id = getResultTransformation(model.getIdentity());
        Model newModel = new Model();
        for (Triangle triangle : model.getTriangles()) {
            Triangle t = new Triangle(triangle);
            newModel.addTriangle(t);
            Vector4[] vertices = t.getVertices();
            for (int i = 0; i < vertices.length; i++) {
                Vector4 out = vertices[i].multiply(id);
                vertices[i] = out;
            }
        }


        for (Triangle triangle : newModel.getTriangles()) {
            Vector4[] vertices = triangle.getVertices();
            for (int i = 0; i < vertices.length; i++) {
                Vector4 currentVertex = vertices[i];
                Vector4 nextVertex = vertices[(i + 1) % vertices.length];
                int x1 = (int) Math.round(currentVertex.getX());
                int y1 = (int) Math.round(currentVertex.getY());
                int x2 = (int) Math.round(nextVertex.getX());
                int y2 = (int) Math.round(nextVertex.getY());
                drawLine(image, x1, y1, x2, y2);
            }
        }
    }

    private Matrix getResultTransformation(Matrix identity) {
        Matrix transform = Matrix.viewportTransformation(screenWidth, screenHeight, 0, 0);
        Matrix perspectiveProjection = Matrix.perspectiveProjectionFOV(fov, aspect, near, far);
        Matrix viewMatrix = Matrix.viewMatrix(
                position(),
                target(),
                up()
        );
        return transform.multiply(perspectiveProjection)
                .multiply(viewMatrix)
                .multiply(identity);
    }

    public void drawLine(BufferedImage image, int x1, int y1, int x2, int y2) {
//        if (Window.is)
//            image.setRGB(20, 20, Color.WHITE.getRGB());
        int width = image.getWidth();
        int height = image.getHeight();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {

            if (!(x1 < 0 || x1 >= width || y1 < 0 || y1 >= height)) {
                image.setRGB(x1, y1, Color.WHITE.getRGB());
            }
            if (x1 == x2 && y1 == y2) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                y1 = y1 + sy;
            }
        }
    }

    public Vector4 target() {
        double[][] identity = this.getIdentity().getMatrix();
        return new Vector4(identity[2][0], identity[2][1], identity[2][2]);
    }

    public void setTarget(Vector4 target) {
        double[][] identity = this.getIdentity().getMatrix();
        identity[2][0] = target.getX();
        identity[2][1] = target.getY();
        identity[2][2] = target.getZ();
    }

    public Vector4 up() {
        double[][] identity = this.getIdentity().getMatrix();
        return new Vector4(identity[1][0], identity[1][1], identity[1][2]);
    }

    public void setUp(Vector4 up) {
        double[][] identity = this.getIdentity().getMatrix();
        identity[1][0] = up.getX();
        identity[1][1] = up.getY();
        identity[1][2] = up.getZ();
    }

    public Vector4 position() {
//        double[][] identity = this.getIdentity().getMatrix();
//        return new Vector4(identity[0][0], identity[0][1], identity[0][2]);
        return getPosition();
    }


}
