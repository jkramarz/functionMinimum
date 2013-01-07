package net.lenwe.funmin;

import java.util.Arrays;

public class FindMinimum {

	double p[] = { 0, 0, 0 };
	double val[] = { 0, 0, 0 };
	double step = 0.001;

	static double x = Math.random();
	static double y = Math.random();

	void find(boolean var, double st) {
		System.out.println("Var: " + (var == true ? 1 : 0) + " step: " + st);
		step = st;
		p[0] = Math.random() * st;
		p[1] = Math.random() * st;
		p[2] = Math.random() * st;

		Arrays.sort(p);

		int steps = 0;

		for (int i = 0; i < 3; i++) {
			if (var) {
				val[i] = Blackbox.getResult(x, p[i]);
			} else {
				val[i] = Blackbox.getResult(p[i], y);
			}
		}

		while (!(val[0] > val[1] && val[1] < val[2]) && steps++ < 100) {
			for (int i = 0; i < 2; i++) {
				p[i] = p[i + 1];
				val[i] = val[i + 1];
			}
			p[2] = p[1] + step;
			step *= 2;
			if (var) {
				val[2] = Blackbox.getResult(x, p[2]);
			} else {
				val[2] = Blackbox.getResult(p[2], y);
			}
		}

		while (steps++ < 100) {
			double d;
			if (steps > 10) {
				double f = val[2] - val[1];
				double s = val[0] - val[2];
				double t = val[1] - val[0];
				d = p[0] * p[0] * f + p[1] * p[1] * s + p[2] * p[2] * t / 2
						* (p[0] * f + p[1] * s + p[2] * t);
			} else {
				double f = Math.abs(p[1]-p[0]);
				double s = Math.abs(p[2]-p[1]);
				double gamma = 0.381966;
				if(f > s){
					d = p[0] + gamma*f;
				}else{
					d = p[1] + gamma*s;
				}
			}
			double vald;
			if (var) {
				vald = Blackbox.getResult(x, d);
			} else {
				vald = Blackbox.getResult(d, y);
			}
			if (vald < val[1]) {
				if (d < p[1]) {
					p[2] = p[1];
					val[2] = val[1];
					p[1] = d;
					val[1] = vald;
				} else {
					p[0] = p[1];
					val[0] = val[1];
					p[1] = d;
					val[1] = vald;
				}
			} else {
				if (d < p[1]) {
					p[0] = d;
					val[0] = vald;
				} else {
					p[2] = d;
					val[2] = vald;
				}
			}
		}

		if (var) {
			for (int i = 0; i < 3; i++) {
				System.out.println("Final result: " + i + ": f(" + x + ","
						+ p[i] + ")=" + val[i]);
				y = p[1];
			}
		} else {
			for (int i = 0; i < 3; i++) {
				System.out.println("Final result: " + i + ": f(" + p[i] + ","
						+ y + ")=" + val[i]);
				x = p[1];
			}
		}
	}
}
