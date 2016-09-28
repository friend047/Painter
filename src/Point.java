public class Point {
	int x, y;//todo: change to private type

	Point() {
		x = y = 0;
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void set(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public void show() {
		System.out.println("X:Y=" + x + ":" + y);
	}
}
