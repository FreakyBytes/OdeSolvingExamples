package net.freakybytes.uni.mathe.numericDGL;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Arrays;

public class EulerPoly {
	
	private MathFunction function;
	private double h;
	private double result[];
	private double begin;
	
	private DecimalFormat format;
	
	public EulerPoly( MathFunction function ) {
		this.function = function;
		format = new DecimalFormat(",##0.000000");
	}
	
	public double[] approx( double begin, double end, double yBegin, double stepWidth ) {
		h = stepWidth;
		this.begin = begin;
		
		int i = 0;
		int stepcount = (int) Math.ceil( ( end - begin ) / h);
		double xi = 0;
		result = new double[stepcount + 2];
		
		result[0] = yBegin;
		for( i = 0; i <= stepcount; i++ ) {
			xi = updateXi(i);
			result[i+1] = makeStep( i, xi );
			System.out.println( MessageFormat.format("x{0}: {1}    y{0}: {2}", i, format.format(xi), format.format(result[i]) ) );
		}
		
		System.out.println( Arrays.toString(result) );
		return result;
	}
	
	private double updateXi( int i ) {
		return begin + (h * i);
	}
	
	private double makeStep( int i, double xi ) {
		return result[i] + (h * function.calc(xi, result[i])); 
	}
	
}
