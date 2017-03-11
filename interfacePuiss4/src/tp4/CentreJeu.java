package tp4;

import gui.Coup;
import gui.FenetreJeu;

public class CentreJeu extends Thread{
	private FenetreJeu fj;
	private Plateau p;
	
	public CentreJeu(){
		fj = new FenetreJeu("Puissance 4");
		p = new Plateau();
		
		this.start();
	}
	
	public void run(){
		Coup c = fj.getCoup();
		while(true){
			while(!c.coupJouee());
			p.joue(c.getColonne());
			c.setALire(true);
		}
	}
}
