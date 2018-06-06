public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2){
        int length = Math.abs(this.x - p2.x);
        int height = Math.abs(this.y - p2.y);

        return Math.sqrt(length*length + height*height);
    }
}
