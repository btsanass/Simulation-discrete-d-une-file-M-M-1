import java.lang.Math;
import java.util.Random;

public class Utile {	
	private static Random random;
	public static double expo(double var){
		random = new Random();
		double x= random.nextDouble();
		
		while (x==0 || x==1) {              // tant que x n'est pas une proba on refait un random 
			x= random.nextDouble();
		}	
		return (- Math.log(1-x)/var);
	}
}