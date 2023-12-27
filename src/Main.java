//Emil Davlityarov
//e.davlityarov@innopolis.university
import java.util.*;
//I used flyweight for create new type of Shapes using ready before , I mean that if I create new Shape with equivalent fill color, border
//color and thickness, than I create one instance and others shapes would refer on this instance.
//I used visitor for 2 methods export and draw, because visitor pattern is to define a new operation without
// introducing the modifications to an existing object structure, so if we want change these methods then we would not
// change class such as Triangle, Circle, line and Rectangle. Need to change only class vector.
public class Main {
    public static void main(String[] args) {
        ShapeVisitor visitor = new allVisitor();
        List<Shape> shapes = new ArrayList<>();
        ShapeType shapeType = ShapeFactory.getShapeType(Color.RED, Color.BLUE, 1.5);
        Shape line = new line(10, shapeType);
        line.draw(visitor, 1, 1);
        shapeType = ShapeFactory.getShapeType(Color.RED, Color.BLUE, 1.5);
        Shape circle = new Circle(5, shapeType);
        circle.draw(visitor, 2,2);
    }
}

enum Color{
    RED,
    BLUE,
    BLACK,
}
abstract class Shape{
    Color fillColor;
    Color borderColor;
    double borderThickness;
    double coordinateX;
    double coordinateY;
    public Shape(){}
    public Shape(Color fillColor, Color borderColor, double borderThickness, double coordinateX, double coordinateY) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    abstract void draw(ShapeVisitor shapeVisitor, double x, double y);


    abstract void export(ShapeVisitor shapeVisitor);
}
class ShapeType extends Shape {
    private Color bordercolor;
    private Color fillcolor;
    private double borderThickness;

    public ShapeType(Color color, Color fillcolor, double thickness) {
        this.bordercolor = color;
        this.fillcolor = fillcolor;
        this.borderThickness = thickness;
    }

    @Override
    public void draw(ShapeVisitor shapeVisitor, double x, double y) {
    }

    @Override
    public void export(ShapeVisitor shapeVisitor) {
    }

    public Color getColor() {
        return this.bordercolor;
    }

    public Color getFillcolor() {
        return this.fillcolor;
    }

    public double getThickness() {
        return this.borderThickness;
    }
}

class ShapeFactory{
    private static HashMap<String, ShapeType> shapeHashMap = new HashMap<>();
    public static ShapeType getShapeType(Color color, Color fillColor, double thickness){
        String key = color.toString() + fillColor.toString() + thickness;
        ShapeType shapeType = shapeHashMap.get(key);
        if (shapeType == null){
            shapeType = new ShapeType(color, fillColor, thickness);
            shapeHashMap.put(key, shapeType);
            System.out.println("Creating shapeType...");
        } else {
            System.out.println("Using an already existing shapeType...");
        }
        return shapeType;
    }

}
class Rectangle extends Shape{
    private double length;
    private double width;
    private ShapeType shapeType;
    public Rectangle(){
        super();
    }

    public Rectangle(double length, double width, ShapeType shapeType){
        this.length = length;
        this.width = width;
        this.shapeType = shapeType;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
    @Override
    public void export(ShapeVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public void draw(ShapeVisitor shapeVisitor, double x, double y){
        shapeVisitor.visit2(this);
    }
}
class Circle extends Shape{
    private double radius;
    private ShapeType shapeType;
    public Circle(){
        super();
    }
    public Circle(double radius, ShapeType shapeType){
        this.radius = radius;
        this.shapeType = shapeType;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void draw(ShapeVisitor shapeVisitor, double x, double y){
        shapeVisitor.visit2(this);
    }
    @Override
    public void export(ShapeVisitor visitor){
        visitor.visit(this);
    }
}
class Triangle extends Shape{
    private double side1;
    private double side2;
    private double side3;
    private ShapeType shapeType;
    public Triangle(){
        super();
    }

    public Triangle(double side1, double side2, double side3, ShapeType shapeType){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.shapeType = shapeType;
    }

    public double getSide1() {
        return side1;
    }
    public double getSide2() {
        return side2;
    }
    public double getSide3(){
        return side3;
    }
    @Override
    public void draw(ShapeVisitor shapeVisitor, double x, double y){
        shapeVisitor.visit2(this);
    }
    @Override
    public void export(ShapeVisitor visitor){
        visitor.visit(this);
    }
}
class line extends Shape{
    private double lineLength;
    private ShapeType shapeType;
    public line(){
        super();
    }
    public line(double lineLength, ShapeType shapeType){
        this.lineLength = lineLength;
        this.shapeType = shapeType;
    }

    public double getLineLength() {
        return lineLength;
    }

    @Override
    public void draw(ShapeVisitor shapeVisitor, double x, double y){
        shapeVisitor.visit2(this);
    }
    @Override
    public void export(ShapeVisitor visitor){
        visitor.visit(this);
    }
}
interface ShapeVisitor{
    void visit(Circle circle);
    void visit(Rectangle rectangle);
    void visit(line line);
    void visit(Triangle triangle);
    void visit2(Circle circle);
    void visit2(Rectangle rectangle);
    void visit2(line line);
    void visit2(Triangle triangle);

}
class allVisitor implements ShapeVisitor{
    public void visit(Circle circle){
        System.out.println("Exporting circle...");
    }
    public void visit(Rectangle rectangle){
        System.out.println("Exporting rectangle...");
    }
    public void visit(line line){
        System.out.println("Exporting line...");
    }
    public void visit(Triangle triangle){
        System.out.println("Exporting triangle...");
    }
    public void visit2(Circle circle){
        System.out.println("Drawing circle...");
    }
    public void visit2(Rectangle rectangle){
        System.out.println("Drawing rectangle...");
    }
    public void visit2(line line){
        System.out.println("Drawing line...");
    }
    public void visit2(Triangle triangle){
        System.out.println("Drawing triangle...");
    }

}