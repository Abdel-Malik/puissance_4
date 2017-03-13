package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import tp4.Plateau;

public class PanneauJeu extends JPanel{
	private Jeton[][] grilleJeton;
	
	public PanneauJeu(){
		super();
		this.grilleJeton = new Jeton[Plateau.LIGNES][Plateau.COLONNES];
		initialiserGrilleJeton();
	}
	
	public void paintComponent(Graphics g){
		Dimension d = this.getSize();
		d.setSize(d.getWidth()/grilleJeton[0].length,d.getHeight()/grilleJeton.length);
		for(int i = 0; i < this.grilleJeton.length; i++){
			for(int j = 0; j < this.grilleJeton[0].length; j++){
				g.setColor(Color.BLUE);
				g.fillRect((int)(j*d.getWidth()), (int)(i*d.getHeight()), (int)d.getWidth(), (int)d.getHeight());
				g.setColor(this.grilleJeton[i][j].getColor());
				g.fillOval((int)(j*d.getWidth()+5), (int)(i*d.getHeight()+5), (int)d.getWidth()-10, (int)d.getHeight()-10);
			}
		}
	}
	private void initialiserGrilleJeton(){
		for(int i = 0; i < this.grilleJeton.length; i++){
			for(int j = 0; j < this.grilleJeton[0].length; j++){
				this.grilleJeton[i][j] = Jeton.Vide;
			}
		}
	}

	public void ajoutJeton(int i, int j, Jeton jeton) {
		this.grilleJeton[i][j] = jeton;
		
	}
}
