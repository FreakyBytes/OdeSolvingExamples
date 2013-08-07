package net.freakybytes.uni.mathe.numericDGL;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class EulerHeun {
	
	private MathFunction function;
	private double h;
	private double result[];
	private double begin;
	private double yBegin;
	
	private DecimalFormat format;
	
	public EulerHeun( MathFunction function ) {
		this.function = function;
		format = new DecimalFormat(",##0.000000");
	}
	
	public double[] approx( double begin, double end, double yBegin, double stepWidth ) {
		h = stepWidth;
		this.begin = begin;
		this.yBegin = yBegin;
		
		int i = 0;
		int stepcount = (int) Math.ceil( ( end - begin ) / h);
		double yTilde, xi;
		result = new double[stepcount + 2];
		
		result[0] = yBegin;
		
		for( i = 0; i <= stepcount; i++ ) {
			xi = updateXi(i);
			yTilde = calcYtilde(i, xi);
			result[i+1] = makeStep( i, xi, yTilde );
			System.out.println( MessageFormat.format("x{0}: {1}    ~y{0}: {3}    y{0}: {2}", i, format.format(xi), format.format(result[i]), format.format(yTilde) ) );
		}
		
		return result;
	}
	
	private double calcYtilde( int i, double xi ) {
		return result[i] + (h * function.calc( xi, result[i] ));
	}
	
	private double updateXi( int i ) {
		return begin + (h * i);
	}
	
	private double makeStep( int i, double xi, double yTilde ) {
		double xi2 = updateXi( i+1 );
		return 0.5 * (result[i] + (yTilde + ( h * function.calc(xi2, yTilde) )) ); 
	}
}
