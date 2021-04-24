abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {

    protected double s1;
    protected double s2;
    protected double s3;

    public Triangle(double s1, double s2, double s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    double getPerimeter() {
        return s1 + s2 + s3;
    }

    @Override
    double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
    }
}

class Rectangle extends Shape {

    protected double height;
    protected double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    double getPerimeter() {
        return height * 2 + width * 2;
    }

    @Override
    double getArea() {
        return height * width;
    }
}

class Circle extends Shape {

    protected double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}