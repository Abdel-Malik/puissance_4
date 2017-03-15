package heuristique;

import tp4.Plateau;

public class StupideIA {
	
	public int choixColonne(){
		return (int)(Plateau.COLONNES*Math.random());
	}
}
