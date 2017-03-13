package tp4;

public class Plateau {
	public static int LIGNES = 6;
	public static int COLONNES = 7;
	private int[][] grille;
	//private Couple joueurs;
	private boolean J1;
	private boolean jeuClos;
	
	public Plateau(){
		grille = new int[LIGNES][COLONNES];
		J1 = true;
		jeuClos = false;
		initialiserGrille();
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

	
	public void joue(int i){
		if(!jeuClos){
			if(i<0 || i >=COLONNES)
				System.out.printf("clonne %d inexistante, jouez dans [0;%d]\n",i,COLONNES);
			int jeton = 2;
			if(J1)
				jeton = 1;
			try {
				int j = insererJeton(i,jeton);
				if(gagne(i,j) != -1){
					System.out.printf("Gagné\n");
					jeuClos = true;
				}else{
					J1 = !J1;
				}
			} catch (ColonnePleineException e) {
				System.out.printf("colonne %d pleine, jouez ailleurs\n",e.getColonnePleine()+1);
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
		return (val==4);
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
		return (val==4);
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
		return (val==4);
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
		return (val==4);
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
}
