public class MM1 {
	public static double lambda;
	public static double mu;
	public static double dureemax;
	public static int debug;
	private static Evt e;
	public static void main(String[] args){
		
		double time=System.currentTimeMillis();
		lambda=Double.parseDouble(args[0]);
		mu=Double.parseDouble(args[1]);
		dureemax=Double.parseDouble(args[2]);
		debug=Integer.parseInt(args[3]);
				
		System.out.println("Exécution en cours...");
		new Stats();
		e= new Evt(0,true);
		new Ech();
		
		Ech.gestionEch(); 												//Programme principal 
		
		double finaltime=(System.currentTimeMillis()-time)/1000;       //permet d'avoir le temps d'éxécution 
		System.out.println("\nSucces !\nTemps d'execution :"+ finaltime +" s");
		Stats.statsTheo(e);
		Stats.ResultSimu();
	}
}