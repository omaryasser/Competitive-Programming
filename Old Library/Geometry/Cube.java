
	static class Cube
	{
		Point l, u;
		
		Cube(Point a, Point b) { l = a; u = b; }
		
		Cube intersect(Cube c)
		{
			Point l = new Point(Math.max(this.l.x, c.l.x), Math.max(this.l.y, c.l.y), Math.max(this.l.z, c.l.z));
			Point u = new Point(Math.min(this.u.x, c.u.x), Math.min(this.u.y, c.u.y), Math.min(this.u.z, c.u.z));
			
			if(l.x == u.x || l.y == u.y || l.z == u.z)
				return null;
			if(this.contains(l) && this.contains(u) && c.contains(l) && c.contains(u))
				return new Cube(l, u);
			return null;
		}
		
		boolean contains(Point p)
		{
			return p.x <= u.x && p.y <= u.y && p.z <= u.z && p.x >= l.x && p.y >= l.y && p.z >= l.z;
		}
		
		int volume()
		{
			return (u.x - l.x) * (u.y - l.y) * (u.z - l.z);
		}
		public static void main(String[] args) {
			Cube intersect = cubes[0];
			for(int i = 1; i < n && intersect != null; i++)
				intersect = intersect.intersect(cubes[i]);
			
			out.println(intersect != null ? intersect.volume() : 0);
		}
	}
	

	static class Point
	{
		int x, y, z;
		
		Point(int a, int b, int c) { x = a; y = b; z = c; }
	}
	