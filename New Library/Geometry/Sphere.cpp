// Distance between two Points on Sphere.
double gcDistance(double pLat, double pLong,
double qLat, double qLong, double radius) {
	pLat *= PI / 180; pLong *= PI / 180;
	// convert degree to radian
	qLat *= PI / 180; qLong *= PI / 180;
	return radius * acos(cos(pLat)*cos(pLong)*cos(qLat)*cos(qLong) +
	cos(pLat)*sin(pLong)*cos(qLat)*sin(qLong) +
	sin(pLat)*sin(qLat)); 
}
