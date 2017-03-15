package tp4;

import gui.Coup;
import gui.FenetreJeu;
import gui.Jeton;
import gui.PageJoueur;
import heuristique.StupideIA;

public class CentreJeu extends Thread{
	private FenetreJeu fj;
	private Plateau p;
	private StupideIA ia;
	private Coup c;
	
	public CentreJeu(){
		fj = new FenetreJeu("Puissance 4");
		//fj.setVisible(false);
		p = new Plateau();
		ia = new StupideIA();
		c = fj.getCoup();
		initialiserJoueurs();
		
		this.start();
	}
	
	private void initialiserJoueurs(){
		PageJoueur page = new PageJoueur();
		p.setJoueur1(new Joueur(false, "Amalik",1));
		p.setJoueur2(new Joueur(true, "RobotBoyDumb",2));
	}

	public void run(){
		while(true){
			while(!c.coupJouee()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!tourDeJeu())
				break;
			
			if(p.isIA()&&!p.isJoueur1()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fj.choixIA(ia.choixColonne());
			}
		}
	}

	private boolean tourDeJeu(){
		boolean b = true;
		if(!p.jeuClos()){
			Jeton j = Jeton.Jaune;
			if(p.isJoueur1())
				j= Jeton.Rouge;
			try {
				p.joue(c.getColonne());
				fj.repeindre(p.getLigne(c.getColonne()), c.getColonne(), j);
				p.imprime();
			} catch (ColonnePleineException e) {
				System.out.println("BOUYAHHHHHH");
			}
			c.setCoupJouee(false);
		}else{
			b = false;
			String win = "Joueur2";
			if(p.isJoueur1())
				win = "Joueur1";
			System.out.printf("jeu clos, gagnant %s",win);
		}
		return b;
	}
}