package tp4;

public class Joueur {
	public static final String DEFAULT_IA_NAME = "IA";
	private boolean ia;
	private String nomJoueur;
	private int numero;
	
	public Joueur(boolean ia, String nomJoueur, int numeroJoueur){
		this.ia = ia;
		this.nomJoueur = nomJoueur;
		this.numero = numeroJoueur;
	}

	public boolean isIA() {
		return ia;
	}
	
	public String getName(){
		return nomJoueur;
	}
	
	public Joueur copie(){
		return new Joueur(ia,nomJoueur,numero);
	}
}
