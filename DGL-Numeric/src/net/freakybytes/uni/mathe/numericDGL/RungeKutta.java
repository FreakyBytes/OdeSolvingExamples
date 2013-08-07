package net.freakybytes.uni.mathe.numericDGL;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class RungeKutta {
	private MathFunction function;
	private double h;
	private double result[];
	private double begin;
	private double yBegin;
	
	private DecimalFormat format;
	
	private final double[] A_CONSTANTS = {0, 0.5, 0.5, 1};
	private final double[] C_CONSTANTS = {0.16666666666666666666666666666666,
											0.33333333333333333333333333333333,
											0.33333333333333333333333333333333,
											0.16666666666666666666666666666666};
	private final double[][] B_CONSTANTS = {{0.0, 0.0, 0.0, 0.0},
											{0.5, 0.0, 0.0, 0.0},	/* Zeile 1 */
											{0.0, 0.5, 0.0, 0.0}, 	/* Zeile 2 */
											{0.0, 0.0, 1.0, 0.0}};	/* Zeile 3 */
											
	
	public RungeKutta( MathFunction function ) {
		this.function = function;
		format = new DecimalFormat(",##0.000000");
	}
	
	public double[] approx( double begin, double end, double yBegin, double stepWidth ) {
		h = stepWidth;
		this.begin = begin;
		this.yBegin = yBegin;
		
		int i = 0;
		int stepcount = (int) Math.ceil( ( end - begin ) / h);
		result = new double[stepcount + 2];
		double xi;
		
		result[0] = yBegin;
		
		for( i = 0; i <= stepcount; i++ ) {
			xi = updateXi(i);
			result[i+1] = makeStep(i, xi);
			System.out.println( MessageFormat.format("x{0}: {1}    y{0}: {2}", i, format.format(xi), format.format(result[i]) ) );
		}
		
		
		return result;
	}
	
	private double updateXi( int i ) {
		return begin + (h * i);
	}
	
	private double makeStep( int i, double xi ) {
		double y = result[i];
		double[] kValues = generateKValues(i, xi); 
		
		for( int j = 0; j < 4; j++ ) {
			y += C_CONSTANTS[j] * kValues[j];
		}
		
		return y;
	}
	
	private double[] generateKValues( int i, double xi ) {
		double[] kValues = new double[4];
		kValues[0] = h * function.calc(xi, result[i]);
		
		for( int j = 1; j < 4; j++ ) {
			double y;
			
			y = result[i];
			for( int l = 0; l < j; l++ ) {
				y += B_CONSTANTS[j][l] * kValues[l];
			}
			
			kValues[j] = h * function.calc(
					xi + (A_CONSTANTS[j]*h),
					y);
		}
		
		
		return kValues;
	}
	
	
}
