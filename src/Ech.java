import java.util.LinkedList;

public class Ech {
	static LinkedList<Evt> ech;
	static double datedep;
	static double TmpsMoy;
	
	
	
	public static void inser(LinkedList<Evt> ech,Evt e){  //insertion chronologique de l'événement e dans l'éch
		double ntime=e.date;
		int i=0;
		while(i<ech.size()&&ech.get(i).date<ntime){       //tant qu'on n'atteint pas la taille de l'ech et que la date limite n'est pas dépassée
			i++;
		}
			ech.add(i,e);                                 // on ajoute à l'échéancier l'événement, à l'emplacement i
		
	}
	
	public static LinkedList<Evt> process(LinkedList<Evt> ech,Evt e,Stats stats){
		Evt arr;
		Evt dep;
		double curtmps=e.date;
		double arrtmps;
		double deptmps;
		double tmoy;
	
		int id=e.num;
		if(e.type==true){            // si c'est un événement de type arrivée
		Stats.statsarr(e);       // si c'est une arrivée, on traite les statistiques relatives à ce type
			if(curtmps<=MM1.dureemax){
				if((arrtmps=curtmps+Utile.expo(MM1.lambda))<=MM1.dureemax){
					arr=new Evt(arrtmps,true);                 //on crée l'événement arrivée
					arr.num=id+1;                              //on incrémente son numéro
					inser(ech,arr);                            // on l'insert dans l'ech
				}
			}
			deptmps=datedep+Utile.expo(MM1.mu); 
			while(deptmps<curtmps){                   //on fait cela afin d'éviter que le départ arrive dans le bon ordre
				deptmps=datedep+Utile.expo(MM1.mu); 
				
			}
			dep=new Evt(deptmps,false);              // on crée l'événement départ
			datedep=dep.date;                        // on lui attribue sa date de départ
			dep.num=id;                              // il garde le numéro qu'il avait en arrivant
			inser(ech,dep);                          //on l'insère dans l'échéancier
			tmoy=datedep-curtmps;                    // calcul du temps moyen dans le systeme (reultats simulation)
			TmpsMoy=TmpsMoy+tmoy;
		}
		else{
			
			Stats.statsdep(e);               //on traite les stats relatives aux départs si le type de l'événement == false
		}
		return ech;                          //on retourne l'échéancier obtenu
	}
	
	public static void gestionEch(){ 
		
		
		double arrtime;          //date d'arrivée de l'évenement 2
		Stats stats=new Stats();
		Evt e=new Evt();
		ech=new LinkedList<Evt>();
		inser(ech,e);             //on insère le premier événement 
		
		Stats.statsarr(e);
		
		if(MM1.debug==1){        //on affiche que si debug est à 1
			System.out.println("Date : " + e.date +"       Arrivee client #" +e.num);
		}
		e=ech.poll();   
		if((arrtime=e.date+Utile.expo(MM1.lambda))<=MM1.dureemax){ // si l'arrivée à une date ne dépassant pas la date maximale
			Evt arr=new Evt(arrtime,true);
			arr.num=e.num+1;
			inser(ech,arr);
		}
		Evt dep=new Evt(e.date+Utile.expo(MM1.mu),false);
		dep.num=e.num;
		inser(ech,dep);
		datedep=dep.date;
		
		while(!ech.isEmpty()){                  //tant que l'echantillon n'est pas vide
			e=ech.poll(); 
			ech=process(ech,e,stats); 
			if(MM1.debug==1){
				if(e.type==true){               // si ==true, on affiche les arrivées
					System.out.println("Date : " + e.date +"       Arrivee client #" +e.num);
				}
				else{                           // sinon on affiche les départs
					System.out.println("Date : " + e.date +"       Depart client #" +e.num +"    Arrivé à t= " + Stats.getArrTps(e.num));
				}
			}
		}
	}
}