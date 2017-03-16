package gui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tp4.Plateau;

public class FenetreJeu extends JFrame{

				//**	Attributs	**//
	//Valeurs statiques (tailles et positions des éléments)
	public static Dimension DEFAULT_DIMENSION = new Dimension(955,800);
	public static Point POSITION = new Point(200,40);
	public static final int DEFAULT_BUTTON_SIZE_X = 100;
	public static final int DEFAULT_BUTTON_SIZE_Y = 50;
	public static final int DEFAULT_SPEED = 6;
	
	//Etat du jeu
	private boolean fonctionne;
	private int largeur;
	private int hauteur;
	private int fps;
	private Coup coupJouee;
	
		/* Déclaration des éléments graphiques */
		
	//panneau d'affichage de l'état de la grille du jeu
	private PanneauJeu panneau;
	private	JPanel g;
	private	JPanel d;
	private JSplitPane paneAffichage;
	//boutons de contrôle
	private BoutonColonne[] colonnes;
		
		
		
				//**	Constructeur	**//
		public FenetreJeu(String name){
			super(name);
			this.fonctionne = true;
			this.largeur = DEFAULT_DIMENSION.width;
			this.hauteur = DEFAULT_DIMENSION.height;
			this.fps = DEFAULT_SPEED;
			this.coupJouee = new Coup();
			initialisation();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setVisible(true);
		}
		
		
				//**	Méthodes	**//
		
		private void initialisation() {
			this.initialisationFenetre();
			this.structurationFenetre();
		}
		
		private void initialisationFenetre(){
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setLocation(POSITION);
			this.setSize(DEFAULT_DIMENSION);
			
			g = new JPanel();
			d = new JPanel();
			this.panneau = new PanneauJeu();
			
			d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));
			paneAffichage = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,g,d);
			paneAffichage.setEnabled(false);
			paneAffichage.setDividerLocation(150);
			this.add(paneAffichage);
		}
		
		private void structurationFenetre(){
			structurationSplitPaneGauche();
			structurationSplitPaneDroit();
		}
		
		private void structurationSplitPaneGauche() {
			BoxLayout layoutGauche = new BoxLayout(g, BoxLayout.Y_AXIS);
			g.setLayout(layoutGauche);
			g.add(new JLabel("J1"));
			g.add(new JLabel("J2"));
//			g.add(new JComboBox<String>());
			g.add(new JButton("demarrer"));
			g.add(new JButton("abandonner"));
		}
		private void structurationSplitPaneDroit() {
			int h = DEFAULT_BUTTON_SIZE_Y+10;
			JPanel panelBoutons = new JPanel();
			panelBoutons.setMaximumSize(new Dimension(this.largeur, h));
			GridLayout layoutBoutons = new GridLayout(1, Plateau.COLONNES);
			layoutBoutons.minimumLayoutSize(panelBoutons);
			panelBoutons.setLayout(layoutBoutons);
			panelBoutons.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.darkGray));
			initialisationBoutons(panelBoutons);

			this.panneau.setSize(new Dimension(largeur,hauteur-h));
			d.add(panelBoutons);
			d.add(panneau);
		}


		private void initialisationBoutons(JPanel panel){
			this.colonnes = new BoutonColonne[Plateau.COLONNES];
			for(int i =0; i<Plateau.COLONNES;i++){
				this.colonnes[i] = new BoutonColonne(i);
				this.colonnes[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(fonctionne){
							coupJouee.setColonne(((BoutonColonne)e.getSource()).getColonne());
							coupJouee.setCoupJouee(true);
						}
					}
				});
				panel.add(this.colonnes[i]);
			}
		}

		
		//Affichage de la grille
		
		public void afficherGrille(){
			//this.panneau.affichageJeu();
		}
		
		/*public void afficherGrilleInitial(Grille grille) {
			this.panneau.affichageinitial(grille);
		}*/
		
		//Getter
		public PanneauJeu getPanneau(){
			return this.panneau;
		}
		public boolean getFonctionnement() {
			return this.fonctionne;
		}
		
		/*public int getHauteurPanneau() {
			return this.panneau.getHeight();
		}
		public int getLargeurPanneau() {
			return this.panneau.getWidth();
		}*/
		public int getfps() {
			return this.fps;
		}

		public void setFonctionnement(boolean b) {
			this.fonctionne = false;
		}


		public Coup getCoup() {
			return this.coupJouee;
		}
		
		public void repeindre(int i, int j, Jeton jeton){
			this.panneau.ajoutJeton(i,j,jeton);
			this.panneau.repaint();
		}


		public void choixIA(int choixColonne) {
			if(fonctionne){
				coupJouee.setColonne(choixColonne);
				coupJouee.setCoupJouee(true);
			}
		}
}
