package by.bsuir.vladpr.graphics;

import java.util.List;

public class Model {

    private List<Face> faces;

    public Model(List<Face> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "Model{" +
                ", faces=" + faces +
                "}\n";
    }
}
