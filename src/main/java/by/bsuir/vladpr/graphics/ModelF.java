package by.bsuir.vladpr.graphics;

import java.util.List;

public class ModelF {
    private List<Vector4> vertices;
    private List<Vector3> textures;
    private List<Vector4> normals;
    private List<Face> faces;

    public ModelF(List<Vector4> vertices, List<Vector3> textures, List<Vector4> normals, List<Face> faces) {
        this.vertices = vertices;
        this.textures = textures;
        this.normals = normals;
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "Model{" +
                "\nvertices=" + vertices +
                ", \ntextures=" + textures +
                ", \nnormals=" + normals +
//                ", \nfaces=" + faces +
                "}\n";
    }
}
