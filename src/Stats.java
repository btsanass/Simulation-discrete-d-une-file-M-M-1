import java.util.LinkedList;

public class Stats {
	static LinkedList<Double> listeTps;				//liste des temps d'arrivee
	static double  nbClients;							//nombre de clients dans le système
	static double nbClientsTot;						//nombre total de clients = nb d'événements
	static double sansAtt;							// nombre de clients servis sans attente
	static double nbClientsMoy;						//nombre moyen de clients dans le systeme
	static double tpsMoy;							//temps moyen de séjour
	static double tpsDeSimu;						//temps de simulation
	
	public Stats(){
		listeTps=new LinkedList<Double>();
		nbClientsTot=0;
		nbClients=0;
		nbClientsMoy=0;
		sansAtt=0;
		tpsMoy=0;
		tpsDeSimu=0;
	}
	
	public static double getArrTps(int i){                      //permet de retourner le temps d'arrivée de l'événement i
		return listeTps.get(i);
	}
	
	public static void statsTheo(Evt e) {              // Calcul des stats théoriques
		System.out.println();
		System.out.println("-------------------RESULTATS THEORIQUES--------------------");
		System.out.println();
		if (MM1.lambda < MM1.mu)  {
			System.out.println("lambda < mu : file stable");
		}
		else {
			System.out.println("lambda > mu : file instable");
		}
		double ro= MM1.lambda/MM1.mu;
		System.out.println("Nombre de clients attendus = " + MM1.lambda*MM1.dureemax + "\nProba file occupée ro (lambda/mu) = " + ro + "\nProba sans attente (1-ro) = " + (1-ro) + "\nDébit (lambda) = " + MM1.lambda );
		
		double nbClients = ro/(1-ro);
		double tpsMoyenSejour = 1/(MM1.mu*(1-ro));
		System.out.println("Espérance nombre de clients dans le système (ro/1-ro) = " + nbClients + "\nTemps moyen de séjour (1/mu(1-ro)) = " + tpsMoyenSejour);

		
	}
	
	public static void statsarr(Evt e){           //traitement statistique d'une arrivée
		nbClientsTot++;                              // c'est un événement, on incrémente le nombre de clients total 
		listeTps.add(e.date);
		if(e.type==true && nbClients==0 || nbClients==nbClientsTot/2)   // si le dernier est sorti ou que c'est le premier
			sansAtt++; 
		nbClients++;                                // c'est une arrivée : on incrémente le nombre de clients dans le système
		nbClientsMoy=(nbClientsTot/tpsDeSimu);    //on fait la moyenne
	}
	
	public static void statsdep(Evt e){          //traitement statistique d'un départ 
		tpsDeSimu=e.date;
		nbClients--;                                       // c'est un départ, on décrémente le nombre de clients dans le système
		nbClientsMoy=(nbClientsTot/tpsDeSimu);
		tpsMoy=Ech.TmpsMoy/nbClientsTot;
		
	}
	
	public static void ResultSimu(){                        //Calculs Resultats simulation
		double debit=nbClientsTot/tpsDeSimu;                //débit de la file
		double proporSansAtt=sansAtt/nbClientsTot;          //proportion de clients servis sans attente
		double proporAvecAtt=1-proporSansAtt;               //proportion de clients servis avec attente
		System.out.println();
		System.out.println("--------------------RESULTATS SIMULATION--------------------\n\nNombre total de clients = "+nbClientsTot+"\nProportion clients sans attente = "+proporSansAtt+"\nProportion clients avec attente = "+proporAvecAtt+"\nDebit = "+debit+"\nNb moyen de clients dans le système = "+nbClientsMoy+"\nTemps moyen de séjour = "+tpsMoy);
		
	}
}