package tp4;

import java.util.ArrayList;

public class Plateau {
	public static int LIGNES = 6;
	public static int COLONNES = 7;
	private int[][] grille;
	private boolean J1;
	private Joueur[] joueurs;
	private boolean jeuClos;
	private boolean[] colonneDisponible;
	
	public Plateau(){
		grille = new int[LIGNES][COLONNES];
		J1 = true;
		jeuClos = false;
		joueurs = new Joueur[2];
		colonneDisponible = new boolean[COLONNES];
		initialiserTableau();
		initialiserGrille();
	}
	
	private void initialiserTableau(){
		for(int i =0; i<COLONNES; i++){
			colonneDisponible[i] = true;
		}
		
	}

	public void setColonnePleine(int i){
		this.colonneDisponible[i] = false;
	}
	public int ColonneRestante(){
		int a = 0;
		for(int i = 0; i<COLONNES; i++){
			if(colonneDisponible[i])
				a++;
		}
		return a;
	}
	
	private void initialiserGrille(){
		for(int i = 0; i < LIGNES; i++){
			for(int j =0; j < COLONNES; j++){
				grille[i][j] = 0;
			}
		}
	}
	private int insererJeton(int i, int jeton) throws ColonnePleineException{
		int rang = LIGNES-1;
		while(grille[rang][i] != 0 && rang>0)
			rang--;
		if(rang >= 0 && grille[rang][i]==0)
			grille[rang][i] = jeton;
		else
			throw new ColonnePleineException(i);
		return rang;
	}
	
	public void imprime(){
        for(int i = 0; i<LIGNES; i++){
            for(int j = 0; j<COLONNES; j++){
                System.out.print(grille[i][j]);
            }
            System.out.println();
        }System.out.println();
    }

	
	public void joue(int i) throws ColonnePleineException{
		if(!jeuClos){
			if(i<0 || i >=COLONNES)
				System.out.printf("colonne %d inexistante, jouez dans [0;%d]\n",i,COLONNES);
			int jeton = 2;
			if(J1)
				jeton = 1;
			int j = insererJeton(i,jeton);
			if(gagne(i,j) != -1){
				System.out.printf("Gagn�\n");
				jeuClos = true;
			}else{
				J1 = !J1;
			}
		}else{
			int res = 2;
			if(J1)
				res = 1;
			System.out.printf("jeu fini ! gagnant : %d\n",res);
		}
	}
	
	public int gagne(int colonneJouee,int ligneFinale){
		int res = -1;
		if(ligneDe4(colonneJouee,ligneFinale)||colonneDe4(colonneJouee,ligneFinale)||DiagoDroiteDe4(colonneJouee,ligneFinale) ||DiagoGaucheDe4(colonneJouee,ligneFinale)){
			res = 2;
			if(J1)
				res = 1;
		}
		return res;
	}
	
	private boolean ligneDe4(int i,int j){
		int val = 1;
		int jeton = valeurGrille(i,j);
		int jp = 1;
		while(valeurGrille(i,j+jp) == jeton){
			val++;
			jp++;
		}
		jp = 1;
		while(valeurGrille(i,j-jp) == jeton){
			val++;
			jp++;
		}
		return (val>=4);
	}
	private boolean colonneDe4(int i,int j){
		int val = 1;
		int jeton = valeurGrille(i,j);
		int ip = 1;
		while(valeurGrille(i+ip,j) == jeton){
			val++;
			ip++;
		}
		ip = 1;
		while(valeurGrille(i-ip,j) == jeton){
			val++;
			ip++;
		}
		return (val>=4);
	}
	private boolean DiagoDroiteDe4(int i,int j){
		int val = 1;
		int jeton = valeurGrille(i,j);
		int p = 1;
		while(valeurGrille(i+p,j-p) == jeton){
			val++;
			p++;
		}
		p = 1;
		while(valeurGrille(i-p,j+p) == jeton){
			val++;
			p++;
		}
		return (val>=4);
	}
	private boolean DiagoGaucheDe4(int i,int j){
		int val = 1;
		int jeton = valeurGrille(i,j);
		int p = 1;
		while(valeurGrille(i+p,j+p) == jeton){
			val++;
			p++;
		}
		p = 1;
		while(valeurGrille(i-p,j-p) == jeton){
			val++;
			p++;
		}
		return (val>=4);
	}
	
	private int valeurGrille(int i, int j){
		int res = -1;
		if((i>=0 && i <COLONNES) && (j>=0 && j <LIGNES))
			res = grille[j][i];
		return res;
	}

	public int getLigne(int colonne) {
		int index = 0;
		while(index < Plateau.LIGNES && this.grille[index][colonne]==0)
			index++;
		if(index == Plateau.LIGNES)
			index--;
		return index;
	}

	public boolean isJoueur1() {
		return this.J1;
	}
	
	public boolean jeuClos(){
		return this.jeuClos;
	}

	public void setJoueur1(Joueur joueur) {
		this.joueurs[0] = joueur;
		
	}
	public void setJoueur2(Joueur joueur) {
		this.joueurs[1] = joueur;		
	}

	public boolean j1IsIA() {
		return this.joueurs[0].isIA();
	}
	public boolean j2IsIA() {
		return this.joueurs[1].isIA();
	}
	
	public boolean isCurrentIA(){
		boolean b = false;
		if(this.J1)
			b = j1IsIA();
		else
			b = j2IsIA();
		return b;
	}
	
	public Plateau copiePlateau(){
		Plateau copie = new Plateau();
		copieGrille(copie.grille);
		copie.J1 = J1;
		copieJoueurs(copie.joueurs);
		copie.jeuClos = jeuClos;
		
		return copie;
	}
	private void copieGrille(int[][] g){
		for(int i = 0; i < LIGNES; i++){
			for(int j =0; j < COLONNES; j++){
				g[i][j] = this.grille[i][j];
			}
		}
	}
	private void copieJoueurs(Joueur[] j){
		j[0] = this.joueurs[0].copie();
		j[1] = this.joueurs[1].copie();
	}
	
	public Joueur getCurrentJoueur(){
		Joueur g = this.joueurs[1];
		if(this.J1)
			g = this.joueurs[0];
		return g;
	}

}
