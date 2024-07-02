package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    private Point midPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
        midPoint = new Point((beginPoint.getX() + endPoint.getX()) / 2,
                (beginPoint.getY() + endPoint.getY()) / 2);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        return midPoint;
    }
}
// END
