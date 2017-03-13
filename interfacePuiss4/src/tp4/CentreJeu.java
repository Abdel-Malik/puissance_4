package tp4;

import gui.Coup;
import gui.FenetreJeu;
import gui.Jeton;

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
			while(!c.coupJouee()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!p.jeuClos()){
				Jeton j = Jeton.Jaune;
				if(p.isJoueur1())
					j= Jeton.Rouge;
				p.joue(c.getColonne());
				fj.repeindre(p.getLigne(c.getColonne()), c.getColonne(), j);
				p.imprime();
				//c.setALire(true);
				c.setCoupJouee(false);
			}else{
				String win = "Joueur2";
				if(p.isJoueur1())
					win = "Joueur1";
				System.out.printf("jeu clos, gagnant %s",win);
				break;
			}
		}
	}
}
