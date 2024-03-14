package by.bsuir.vladpr.graphics;

import static java.lang.Math.*;

public class Matrix4 {

    private double[][] matrix;

    public Matrix4() {
        this.matrix = new double[4][4];
    }

    public Matrix4(double[][] matrix) {
        this.matrix = matrix;
    }

//    public Vector4 multiply(Vector4 vector) {
//        double[] result = new double[4];
//        double[] v = {vector.getX(), vector.getY(), vector.getZ(), vector.getW()};
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                result[i] += matrix[i][j] * v[j];
//            }
//        }
//        return new Vector4(result[0], result[1], result[2], result[3]);
//    }

    public Matrix4 multiply(Matrix4 m) {
        Matrix4 result = new Matrix4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result.matrix[i][j] += matrix[i][k] * m.matrix[k][j];
                }
            }
        }
        return result;
    }

    public static Matrix4 translate(Vector3 vec) {
        return new Matrix4(new double[][]{
                {1,0,0,vec.getU()},
                {0,1,0,vec.getV()},
                {0,0,1,vec.getW()},
                {0,0,0,1}
        });
    }

    public static Matrix4 scale(Vector3 vec) {
        return new Matrix4(new double[][]{
                {vec.getU(),0,0,0},
                {0,vec.getV(),0,0},
                {0,0,vec.getW(),0},
                {0,0,0,1}
        });
    }

    public static Matrix4 rotateX(double angle) {
        double radians = Math.PI / 180 * angle;
        return new Matrix4(new double[][]{
                {1,0,0,0},
                {0,cos(radians),-sin(radians),0},
                {0,sin(radians),cos(radians),0},
                {0,0,0,1}
        });
    }

    public static Matrix4 rotateY(double angle) {
        double radians = Math.PI / 180 * angle;
        return new Matrix4(new double[][]{
                {cos(radians),0,sin(radians),0},
                {0,1,0,0},
                {-sin(radians),0,cos(radians),0},
                {0,0,0,1}
        });
    }

    public static Matrix4 rotateZ(double angle) {
        double radians = Math.PI / 180 * angle;
        return new Matrix4(new double[][]{
                {cos(radians),-sin(radians),0,0},
                {sin(radians),cos(radians),0,0},
                {0,0,1,0},
                {0,0,0,1}
        });
    }

    public static Matrix4 viewMatrix(Vector3 eye, Vector3 target, Vector3 up) {
        Vector3 zAxis = eye.subtract(target).normalize();
        Vector3 xAxis = up.cross(zAxis).normalize();
        Vector3 yAxis = zAxis.cross(xAxis).normalize();

        return new Matrix4(new double[][]{
                {xAxis.getU(), xAxis.getV(), xAxis.getW(), -xAxis.dot(eye)},
                {yAxis.getU(), yAxis.getV(), yAxis.getW(), -yAxis.dot(eye)},
                {zAxis.getU(), zAxis.getV(), zAxis.getW(), -zAxis.dot(eye)},
                {0, 0, 0, 1}
        });
    }

//    public static Matrix4 orthographicProjection(double width, double height, double near, double far) {
//        return new Matrix4(new double[][]{
//                {2 / width, 0, 0, 0},
//                {0, 2 / height, 0, 0},
//                {0, 0, 1 / (near - far), near / (near - far)},
//                {0, 0, 0, 1}
//        });
//    }

//    public static Matrix4 perspectiveProjection(double width, double height, double near, double far) {
//        return new Matrix4(new double[][]{
//                {2 * near / width, 0, 0, 0},
//                {0, 2 * near / height, 0, 0},
//                {0, 0, far / (near - far), near * far / (near - far)},
//                {0, 0, -1, 0}
//        });
//    }

    public static Matrix4 perspectiveProjectionFOV(double fov, double aspect, double near, double far) {
        return new Matrix4(new double[][]{
                {1 / (aspect * Math.tan(fov / 2)), 0, 0, 0},
                {0, 1 / Math.tan(fov / 2), 0, 0},
                {0, 0, far / (near - far), near * far / (near - far)},
                {0, 0, -1, 0}
        });
    }

//    public static Matrix4 getProjM(double near, double far, double fov, double aspect) {
//        double fovRadians = Math.PI / 180 * fov;
//        return new Matrix4(new double[][]{
//                {aspect / fovRadians, 0, 0, 0},
//                {0, fovRadians, 0, 0, 0},
//                {0, 0, far / (far - near), 1.0, 0},
//                {0, 0, (-far * near) / (far - near), 0, 0},
//                {0, 0, 0, 0}
//        });
//    }

    public static Matrix4 viewportTransformation(double width, double height, double xmin, double ymin) {
        return new Matrix4(new double[][]{
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
