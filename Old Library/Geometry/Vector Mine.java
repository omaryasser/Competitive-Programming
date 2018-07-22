static class Vector {
        double x , y;
        public Vector(double xx , double yy)
        {
            x = xx;
            y = yy;
        }
        
        public Vector()
        {
            
        }
        
        public static Vector points_to_vector (Point p1 , Point p2)
        {
            return new Vector(p2.x - p1.x , p2.y - p1.y);
        }
        
        public static Vector scale (Vector v , double s)
        {
            return new Vector (v.x * s , v.y * s);
        }
        
        public static Point translate (Point p , Vector v)
        {
            return new Point (p.x + v.x , p.y + v.y);
        }
        
    }

 static double EPS = 1e-9;

    static class Point {
        double x , y;
        public Point(double xx , double yy)
        {
            x = xx;
            y = yy;
        }
        public Point()
        {

        }
    }