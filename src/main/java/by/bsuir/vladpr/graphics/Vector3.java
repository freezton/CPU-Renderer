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
}
