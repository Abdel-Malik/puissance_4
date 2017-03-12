package gui;

public class Coup {
	private int colonne;
	private boolean J1;
	private boolean jouee;
	private boolean aLire;
	
	public Coup(){
		jouee = false;
		aLire = false;
	}

	public boolean coupJouee() {
		return jouee;
	}

	public int getColonne() {
		return colonne;
	}

	public void setALire(boolean b) {
		aLire = b;
	}

	public void setColonne(int cb) {
		colonne = cb;
	}

	public void setCoupJouee(boolean b) {
		jouee = b;
	}
}
