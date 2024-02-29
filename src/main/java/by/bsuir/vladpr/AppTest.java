package by.bsuir.vladpr;

import by.bsuir.vladpr.graphics.Model;
import by.bsuir.vladpr.graphics.ObjParser;

import java.net.URISyntaxException;
import java.util.Objects;

public class AppTest {

    public static void main(String[] args) throws URISyntaxException {
        ObjParser parser = new ObjParser();
        Model m = parser.parse(Objects.requireNonNull(AppTest.class.getClassLoader()
                .getResource("cube.obj")).toURI().getPath());
        System.out.println(m);
    }
}
