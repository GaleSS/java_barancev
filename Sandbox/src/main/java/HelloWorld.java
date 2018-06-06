public class HelloWorld {
	public static void main(String[] args){
		System.out.println("Hello world!");
		Point p1 = new Point(3,5);
		Point p2 = new Point(6,6);
		System.out.println(distance(p1,p2));
		System.out.println(p1.distance(p2));
	}


	public static double distance(Point p1, Point p2){
		int length = Math.abs(p1.x - p2.x);
		int height = Math.abs(p1.y - p2.y);

		return Math.sqrt(length*length + height*height);
	}
}
