package by.bsuir.vladpr.graphics;

import java.util.List;

public class Model {

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
