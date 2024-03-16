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

    public Vector4 multiply(Matrix matrix) {
        double[][] m = matrix.getMatrix();

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

    public Vector4 subtract(Vector4 vec) {
        return new Vector4(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public Vector4 normalize() {
        double length = Math.sqrt(x*x + y*y + z*z);
        return new Vector4(x/length, y/length, z/length);
    }

    public Vector4 cross(Vector4 vec) {
        return new Vector4(
                this.y * vec.z - this.z * vec.y,
                this.z * vec.x - this.x * vec.z,
                this.x * vec.y - this.y * vec.x
        );
    }

    public double dot(Vector4 vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector4 add(Vector4 vec) {
        return new Vector4(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector3 vec3() {
        return new Vector3(this.x, this.y, this.z);
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
