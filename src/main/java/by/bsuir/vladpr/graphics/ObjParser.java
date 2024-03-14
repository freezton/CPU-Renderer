package by.bsuir.vladpr.graphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjParser {

    private List<Vector4> vertices;
    private List<Vector3> textures;
    private List<Vector4> normals;
    private List<Triangle> triangles;

    {
        vertices = new ArrayList<>();
        textures = new ArrayList<>();
        normals = new ArrayList<>();
        triangles = new ArrayList<>();
    }

    private void parseLine(String line) {
        if (line.trim().charAt(0) == '#')
            return;
        String[] lines = line.trim().split(" +");
        double x, y, z, w = 1.0;
        double[] numbers = new double[3];
        switch (lines[0]) {
            case "v":
                x = Double.parseDouble(lines[1]);
                y = Double.parseDouble(lines[2]);
                z = Double.parseDouble(lines[3]);
                if (lines.length > 4)
                    w = Double.parseDouble(lines[4]);
                vertices.add(new Vector4(x, y, z, w));
                break;
            case "vt":
                for (int i = 1; i < lines.length; i++) {
                    numbers[i-1] = Double.parseDouble(lines[i]);
                }
                textures.add(new Vector3(numbers[0], numbers[1]));
                break;  
            case "vn":
                x = Double.parseDouble(lines[1]);
                y = Double.parseDouble(lines[2]);
                z = Double.parseDouble(lines[3]);
                normals.add(new Vector4(x, y, z));
                break;
            case "f":
                String[] substrings = Arrays.copyOfRange(lines, 1, lines.length);
                int[] v = new int[substrings.length];
                for (int i = 0; i < substrings.length; i++) {
                    v[i] = Integer.parseInt(substrings[i].split("/")[0]) - 1;
                }
                int[] vt = null;
                int[] vn = null;
                if (substrings[0].split("/").length > 1 && !substrings[0].split("/")[1].isBlank()) {
                    vt = new int[substrings.length];
                    for (int i = 0; i < substrings.length; i++) {
                        vt[i] = Integer.parseInt(substrings[i].split("/")[1]) - 1;
                    }
                }
                if (substrings[0].split("/").length > 2) {
                    vn = new int[substrings.length];
                    for (int i = 0; i < substrings.length; i++) {
                        vn[i] = Integer.parseInt(substrings[i].split("/")[2]) - 1;
                    }
                }
                for (int i = 0; i < v.length-1; i++) {
                    Triangle triangle = new Triangle(vertices.get(v[0]), vertices.get(v[i]), vertices.get(v[i+1]));
                    if (vn != null) {
                        triangle.setNormals(normals.get(vn[0]), normals.get(vn[i]), normals.get(vn[i+1]));
                    }
                    if (vt != null) {
                        triangle.setTextures(textures.get(vt[0]), textures.get(vt[i]), textures.get(vt[i+1]));
                    }
                    triangles.add(triangle);
                }
                break;
        }
    }

    public Model parse(String path) {
        vertices = new ArrayList<>();
        textures = new ArrayList<>();
        normals = new ArrayList<>();
        triangles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank() || line.isEmpty())
                    continue;
                parseLine(line);
            }
            return new Model(triangles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
