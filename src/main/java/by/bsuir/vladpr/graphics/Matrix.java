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

    public static Matrix getTranslationMatrix(double x, double y, double z) {
        return new Matrix(new double[][]{
                {1,0,0,x},
                {0,1,0,y},
                {0,0,1,z},
                {0,0,0,1}
        });
    }

    public static Matrix getScaleMatrix(double x, double y, double z) {
        return new Matrix(new double[][]{
                {x,0,0,0},
                {0,y,0,0},
                {0,0,z,0},
                {0,0,0,1}
        });
    }

    public static Matrix getRotateXMatrix(double angle) {
        double radians = Math.PI / 180 * angle;
        return new Matrix(new double[][]{
                {1,0,0,0},
                {0,cos(radians),-sin(radians),0},
                {0,sin(radians),cos(radians),0},
                {0,0,0,1}
        });
    }

    public static Matrix getRotateYMatrix(double angle) {
        double radians = Math.PI / 180 * angle;
        return new Matrix(new double[][]{
                {cos(radians),0,sin(radians),0},
                {0,1,0,0},
                {-sin(radians),0,cos(radians),0},
                {0,0,0,1}
        });
    }

    public static Matrix getRotateZMatrix(double angle) {
        double radians = Math.PI / 180 * angle;
        return new Matrix(new double[][]{
                {cos(radians),-sin(radians),0,0},
                {sin(radians),cos(radians),0,0},
                {0,0,1,0},
                {0,0,0,1}
        });
    }
}
