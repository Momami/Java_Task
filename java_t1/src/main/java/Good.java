public class Good {
    private double length;
    private double width;
    private double height;

    public Good() {    }

    public Good(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double squareWalls(){
        return 2 * length * height + 2 * width * height;
    }

    public double squareWithoutDoor(){
        return squareWalls() - 2 * 15 - 2 * 8;
    }

    @Override
    public String toString() {
        return "Good{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
