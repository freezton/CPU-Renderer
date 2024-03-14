package by.bsuir.vladpr.graphics;

public class Object3d {

    protected Vector4 position = new Vector4(0, 0, 0);

    protected Matrix identity = Matrix.identity();

    public void translate(Vector3 translation) {
        identity = Matrix.translate(translation).multiply(identity);
    }

    public void rotateX(double angle) {
        identity = Matrix.rotateX(angle).multiply(identity);
    }

    public void rotateY(double angle) {
        identity = Matrix.rotateY(angle).multiply(identity);
    }

    public void rotateZ(double angle) {
        identity = Matrix.rotateZ(angle).multiply(identity);
    }

    public Matrix getIdentity() {
        return identity;
    }

    public Vector4 getPosition() {
        return position;
    }

    public void setPosition(Vector4 position) {
        this.position = position;
    }

    public void setIdentity(Matrix identity) {
        this.identity = identity;
    }
}
