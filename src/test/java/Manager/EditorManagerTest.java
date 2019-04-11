package Manager;

import Bean.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EditorManagerTest {

    private EditorManager editorManager = new EditorManager();
    private List<Coordinates> coordinates = new ArrayList<>();
    private List<Coordinates> coordinatesTriangle = new ArrayList<>();
    private List<Coordinates> coordinatesStar = new ArrayList<>();
    private List<Coordinates> coordinatesLine = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();
    private double rx = 10.0;
    private double ry = 10.0;
    private String text = " ";
    private String defaultColor = "red";


    @Test
    public void getShapes() throws Exception {
        generateShape();
        assertEquals(editorManager.getShapes().size(), shapes.size());
    }


    @Test
    public void changeColor() throws Exception {
        generateShape();
        for(int i = 0; i < editorManager.getShapes().size(); i++){
            int id = editorManager.getShapes().get(i).getId();
            editorManager.changeColor(id, "red");
            assertEquals(editorManager.getShapes().get(i).getColor(), "red");
        }
    }

    @Test
    public void createShape() throws Exception {
        generateShape();
        for(int i = 0; i < editorManager.getShapes().size(); i++){
            assertEquals(editorManager.getShapes().get(i).getColor(), shapes.get(i).getColor());
            assertEquals(editorManager.getShapes().get(i).getCoordinates(), shapes.get(i).getCoordinates());
        }
    }

    @Test
    public void getShapeIfExists() throws Exception {
        generateShape();
        for(int i = 0; i < editorManager.getShapes().size(); i++){
            int id = editorManager.getShapes().get(i).getId();
            assertEquals(editorManager.getShapeIfExists(id), editorManager.getShapes().get(i));
            assertEquals(editorManager.getShapeIfExists(editorManager.getShapes().size() + 10), null);
        }
    }

    @Test
    public void deleteShape() throws Exception {
        generateShape();
        int id = 0;
        if(editorManager.getShapeIfExists(5) != null){
            id = 5;
        }
        editorManager.deleteShape(id);
        boolean ifExists = false;
        for(Shape shape: editorManager.getShapes()){
            if(shape.getId() == id)
                ifExists = true;
        }
        assertEquals(ifExists, false);
    }

    public void generateShape(){
        addCoordinates();
        for(int j = 0; j < 100; j++){
            double i = (double)j;
            editorManager.createShape("Circle", coordinates, rx, defaultColor);
            editorManager.createShape("Ellipse", coordinates, rx, ry, defaultColor);
            editorManager.createShape("Quadrangle", coordinates, 100.0, 100.0, defaultColor);
            editorManager.createShape("Text", coordinates, text, defaultColor);
            editorManager.createShape("Star", coordinatesStar, defaultColor);
            editorManager.createShape("Line", coordinatesLine, defaultColor);
            editorManager.createShape("Triangle", coordinatesTriangle, defaultColor);
            editorManager.createShape("Ngon", coordinatesStar, defaultColor);
            shapes.add(new Circle(1, coordinates, rx, defaultColor));
            shapes.add(new Ellipse(1, coordinates, rx,ry, defaultColor));
            shapes.add(new Quadrangle(1, coordinates, 100.0,100.0, defaultColor));
            shapes.add(new Text(1, coordinates, text, defaultColor));
            shapes.add(new Star(1, coordinatesStar, defaultColor));
            shapes.add(new Line(1, coordinatesLine, defaultColor));
            shapes.add(new Triangle(1, coordinatesTriangle, defaultColor));
            shapes.add(new Ngon(1, coordinatesStar, defaultColor));
        }
    }

    public void addCoordinates(){
        coordinates.add(new Coordinates(100,100));
        for(int i = 0; i < 5; i++){
            if(i < 2){
                coordinatesLine.add(new Coordinates(100,100));
            }
            if(i < 3){
                coordinatesTriangle.add(new Coordinates(100,100));
            }
            if(i < 5){
                coordinatesStar.add(new Coordinates(100,100));
            }
        }
    }



    @Test
    public void moveShape() throws Exception {
        generateShape();
            editorManager.moveShape(editorManager.getShapes().get(0).getId(), "up");
            assertEquals(50.0, editorManager.getShapes().get(0).getCoordinates().get(0).getY(), 0);
            editorManager.moveShape(editorManager.getShapes().get(0).getId(), "down");
            assertEquals(100.0, editorManager.getShapes().get(0).getCoordinates().get(0).getY(), 0);
            editorManager.moveShape(editorManager.getShapes().get(0).getId(), "left");
            assertEquals(50, editorManager.getShapes().get(0).getCoordinates().get(0).getX(), 0);
            editorManager.moveShape(editorManager.getShapes().get(0).getId(), "right");
            assertEquals(100.0, editorManager.getShapes().get(0).getCoordinates().get(0).getX(), 0);
    }


}