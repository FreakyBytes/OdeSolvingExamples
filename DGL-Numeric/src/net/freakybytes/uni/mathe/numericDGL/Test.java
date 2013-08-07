package net.freakybytes.uni.mathe.numericDGL;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EulerPoly euler = new EulerPoly( new MathFunction() {
			
			@Override
			public double calc(double x, double y) {
				return ((3*x) - (2*y))/x;
				//return y - ( (x-1)*(x-1) );
			}
		});
		
		euler.approx(1, 2, 0, 0.1);
		//euler.approx(0, 1, 1, 0.1);
		
		EulerHeun heun = new EulerHeun( new MathFunction() {
			
			@Override
			public double calc(double x, double y) {
				return ((3*x) - (2*y))/x;
				//return y - ( (x-1)*(x-1) );
			}
		});
		
		//heun.approx(0, 1, 1, 0.1);
		heun.approx(1, 2, 0, 0.1);
		
		RungeKutta runge = new RungeKutta( new MathFunction() {
			
			@Override
			public double calc(double x, double y) {
				return ((3*x) - (2*y))/x;
			}
		});
		
		runge.approx(1, 2, 0, 0.1);
		
	}

}
