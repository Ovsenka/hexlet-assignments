package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %s метров", floorCount, area);
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home anotherHome) {
        if (area == anotherHome.getArea()) {
            return 0;
        }
        return area > anotherHome.getArea() ? 1 : -1;
    }
}
// END
