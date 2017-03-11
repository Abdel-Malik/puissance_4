package tp4;

import gui.FenetreJeu;

public class test {
	
	public static void main(String[] s){
		Plateau p = new Plateau();
		
		for(int i = 0; i < 9; i++){
			p.joue(5);
			p.imprime();
			p.joue(4);
			p.imprime();
		}
		FenetreJeu fj = new FenetreJeu("p4");
	}
}
