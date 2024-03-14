package by.bsuir.vladpr.graphics;

import java.util.List;

public class ModelF {
    private List<Vector4> vertices;
    private List<Vector3> textures;
    private List<Vector4> normals;
    private List<Triangle> triangles;

    public ModelF(List<Vector4> vertices, List<Vector3> textures, List<Vector4> normals, List<Triangle> triangles) {
        this.vertices = vertices;
        this.textures = textures;
        this.normals = normals;
        this.triangles = triangles;
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
