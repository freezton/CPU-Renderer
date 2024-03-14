package by.bsuir.vladpr.graphics;

import java.util.List;
import java.util.Observable;

public class Model extends Object3d {

    private List<Triangle> triangles;

    public Model(List<Triangle> triangles) {
        this.triangles = triangles;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    @Override
    public String toString() {
        return "Model{" +
                ", faces=" + triangles +
                "}\n";
    }
}
