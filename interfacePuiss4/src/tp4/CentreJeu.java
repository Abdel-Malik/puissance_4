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
		p = new Plateau();
		ia = new StupideIA();
		fj = new FenetreJeu("Puissance 4");

		initialiserJoueurs();
		c = fj.getCoup();
		
		this.start();
	}
	
	private void initialiserJoueurs(){
		PageJoueur page = new PageJoueur();
		while(page.isVisible()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		p.setJoueur1(page.getJoueur1());
		p.setJoueur2(page.getJoueur2());
		fj.setNomJoueurs(page.getJoueur1().getName(), page.getJoueur2().getName());
		fj.setVisible(true);
	}

	public void run(){
		while(true){
			if(!p.isCurrentIA()){
				while(!c.coupJouee()&&(!p.jeuClos())){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
			if(p.isCurrentIA()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fj.choixIA(ia.choixColonne());
			}
			if(!tourDeJeu())
				break;
		}
	}

	private boolean tourDeJeu(){
		boolean b = true;
		if(!p.jeuClos()&&p.ColonneRestante()>0){
			Jeton j = Jeton.Jaune;
			if(p.isJoueur1())
				j= Jeton.Rouge;
			try {
				p.joue(c.getColonne());
				fj.repeindre(p.getLigne(c.getColonne()), c.getColonne(), j);
			} catch (ColonnePleineException e) {
				System.out.println("Colonne_Pleine");
				p.setColonnePleine(e.getColonnePleine());
			}
			c.setCoupJouee(false);
		}else if(p.ColonneRestante()==0&&!p.jeuClos()){
			fj.EgaliteFinale();
			b = false;
		}else{
			b = false;
			fj.ClorePartie(p.getCurrentJoueur());
		}
		return b;
	}
}