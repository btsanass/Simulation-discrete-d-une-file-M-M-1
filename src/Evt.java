
public class Evt {
	double date;             //date d'arrivée ou de départ selon le typé d'évènement 
	boolean type;            //true arrivée, false départ
	int num;                 //numéro du client correspondant à l'évènement
	
	public Evt(){
		type=true;
		date=0;
	}
	
	public Evt(double date, boolean type){
		this.type=type;
		this.date=date;
	}
}