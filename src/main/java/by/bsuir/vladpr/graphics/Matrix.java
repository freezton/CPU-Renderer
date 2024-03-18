package by.bsuir.vladpr.graphics;

import static java.lang.Math.*;

public class Matrix {

    private double[][] matrix;

    public Matrix() {
        this.matrix = new double[4][4];
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix multiply(Matrix m) {
        Matrix result = new Matrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result.matrix[i][j] += matrix[i][k] * m.matrix[k][j];
                }
            }
        }
        return result;
    }

    public static Matrix identity() {
        return new Matrix(new double[][] {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    public static Matrix translate(Vector3 vec) {
        return new Matrix(new double[][]{
                {1,0,0,vec.getU()},
                {0,1,0,vec.getV()},
                {0,0,1,vec.getW()},
                {0,0,0,1}
        });
    }

    public static Matrix scale(Vector3 vec) {
        return new Matrix(new double[][]{
                {vec.getU(),0,0,0},
                {0,vec.getV(),0,0},
                {0,0,vec.getW(),0},
                {0,0,0,1}
        });
    }

    public static Matrix rotateX(double angle) {
        angle = Math.toRadians(angle);
        return new Matrix(new double[][]{
                {1,0,0,0},
                {0,cos(angle),-sin(angle),0},
                {0,sin(angle),cos(angle),0},
                {0,0,0,1}
        });
    }

    public static Matrix rotateY(double angle) {
        angle = Math.toRadians(angle);
        return new Matrix(new double[][]{
                {cos(angle),0,sin(angle),0},
                {0,1,0,0},
                {-sin(angle),0,cos(angle),0},
                {0,0,0,1}
        });
    }

    public static Matrix rotateZ(double angle) {
        angle = Math.toRadians(angle);
        return new Matrix(new double[][]{
                {cos(angle),-sin(angle),0,0},
                {sin(angle),cos(angle),0,0},
                {0,0,1,0},
                {0,0,0,1}
        });
    }

    public static Matrix viewMatrix(Vector4 eye, Vector4 target, Vector4 up) {
        Vector4 zAxis = eye.subtract(target).normalize();
        Vector4 xAxis = up.cross(zAxis).normalize();
        Vector4 yAxis = zAxis.cross(xAxis).normalize();

        return new Matrix(new double[][]{
                {xAxis.getX(), xAxis.getY(), xAxis.getZ(), -xAxis.dot(eye)},
                {yAxis.getX(), yAxis.getY(), yAxis.getZ(), -yAxis.dot(eye)},
                {zAxis.getX(), zAxis.getY(), zAxis.getZ(), -zAxis.dot(eye)},
                {0, 0, 0, 1}

        });
    }

    public static Matrix perspectiveProjectionFOV(double fov, double aspect, double near, double far) {
        return new Matrix(new double[][]{
                {1 / (aspect * Math.tan(fov / 2)), 0, 0, 0},
                {0, 1 / Math.tan(fov / 2), 0, 0},
                {0, 0, far / (near - far), near * far / (near - far)},
                {0, 0, -1, 0}
        });
    }

    public static Matrix viewportTransformation(double width, double height, double xmin, double ymin) {
        return new Matrix(new double[][]{
                {width / 2, 0, 0, xmin + width / 2},
                {0, -height / 2, 0, ymin + height / 2},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    public double[][] getMatrix() {
        return matrix;
    }
}
