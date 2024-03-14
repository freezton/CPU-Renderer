package by.bsuir.vladpr.graphics;

public class Vector4 {

    private double x;
    private double y;
    private double z;
    private double w;

    public Vector4(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0;
    }

    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4 normalize() {
        double length = Math.sqrt(x*x + y*y + z*z);
        return new Vector4(x/length, y/length, z/length);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector4 multiply(Matrix4 matrix) {
        double[][] m = matrix.getMatrix();
//        double x = this.x * m[0][0] + this.y * m[1][0] + this.z * m[2][0] + this.w * m[3][0];
//        double y = this.x * m[0][1] + this.y * m[1][1] + this.z * m[2][1] + this.w * m[3][1];
//        double z = this.x * m[0][2] + this.y * m[1][2] + this.z * m[2][2] + this.w * m[3][2];
//        double w = this.x * m[0][3] + this.y * m[1][3] + this.z * m[2][3] + this.w * m[3][3];
        double x = m[0][0] * this.x + m[0][1] * this.y + m[0][2] * this.z + m[0][3] * this.w;
        double y = m[1][0] * this.x + m[1][1] * this.y + m[1][2] * this.z + m[1][3] * this.w;
        double z = m[2][0] * this.x + m[2][1] * this.y + m[2][2] * this.z + m[2][3] * this.w;
        double w = m[3][0] * this.x + m[3][1] * this.y + m[3][2] * this.z + m[3][3] * this.w;

        if (w != 0) {
            x /= w;
            y /= w;
            z /= w;
        }
        return new Vector4(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
