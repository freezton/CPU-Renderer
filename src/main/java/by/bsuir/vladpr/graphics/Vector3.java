package by.bsuir.vladpr.graphics;

public class Vector3 {

    private double u;
    private double v;
    private double w;

    public Vector3(double u, double v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public Vector3(double u, double v) {
        this.u = u;
        this.v = v;
        this.w = 1.0;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }

    public double getW() {
        return w;
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(this.u - other.u, this.v - other.v, this.w - other.w);
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
                this.v * other.w - this.w * other.v,
                this.w * other.u - this.u * other.w,
                this.u * other.v - this.v * other.u
        );
    }

    public Vector4 vec4() {
        return new Vector4(this.u, this.v, this.w);
    }

    public Vector3 normalize() {
        double length = Math.sqrt(u*u + v*v + w*w);
        return new Vector3(u/length, v/length, w/length);
    }

    public double dot(Vector3 vec) {
        return this.u * vec.u + this.v * vec.v + this.w * vec.w;
    }
}
