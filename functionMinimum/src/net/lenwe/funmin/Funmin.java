package net.lenwe.funmin;

public class Funmin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindMinimum f = new FindMinimum();
		for(int i = 1; i < 10; i++){
			f.find(i%2 == 1, 1./(i*i));
		}

	}

}
