package by.bsuir.vladpr.graphics;

public class Face {

    private Vector4[] vertices = new Vector4[3];
    private Vector4[] normals = new Vector4[3];
    private Vector3[] textures = new Vector3[3];
    private Vector4 normal;

    public Face(Vector4 p1, Vector4 p2, Vector4 p3) {
        vertices[0] = p1;
        vertices[1] = p2;
        vertices[2] = p3;
        for (int i = 0; i < 3; i++) {
            normals[i] = new Vector4(0,0,0);
            textures[i] = new Vector3(0,0);
        }
        findNormal();
    }

    public void setNormals(Vector4 n1, Vector4 n2, Vector4 n3) {
        normals[0] = n1;
        normals[1] = n2;
        normals[2] = n3;
    }

    public void setTextures(Vector3 t1, Vector3 t2, Vector3 t3) {
        textures[0] = t1;
        textures[1] = t2;
        textures[2] = t3;
    }

    private void findNormal() {

    }

    public Vector4[] getVertices() {
        return vertices;
    }

    public void setVertices(Vector4[] vertices) {
        this.vertices = vertices;
    }

    public Vector4[] getNormals() {
        return normals;
    }

    public Vector3[] getTextures() {
        return textures;
    }

    public Vector4 getNormal() {
        return normal;
    }

    public void setNormal(Vector4 normal) {
        this.normal = normal;
    }
}
