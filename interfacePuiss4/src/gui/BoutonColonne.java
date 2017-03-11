package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoutonColonne extends JButton{
	private int colonne;
	
	public BoutonColonne(int c){
		super();
		colonne = c;
		this.setText("colonne"+(colonne+1));
	}
	
	public int getColonne(){
		return colonne;
	}
}
