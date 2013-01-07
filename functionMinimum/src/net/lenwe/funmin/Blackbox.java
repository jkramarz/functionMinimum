package net.lenwe.funmin;

public class Blackbox {
	private Blackbox(){}
	static public double getResult(double x, double y){
		x = x + FindMinimum.x;
		y = y + FindMinimum.y;
		return Math.pow(1-x, 2) + 100 * Math.pow(y-Math.pow(x, 2), 2);
	}
}
