package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import tp4.Joueur;

public class PageJoueur extends JFrame{
	private JTextField nom;
	private JComboBox<String> ia;
	private JButton valider;
	private Joueur j1;
	private Joueur j2;
	
	public PageJoueur(){
		super();
		this.setLayout(new FlowLayout());
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.nom = new JTextField(15);
		this.ia = new JComboBox<String>();
		this.valider = new JButton("valider");
		this.ia.addItem("joueur");
		this.ia.addItem("ia");
		this.valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				boolean ia = true;
				if(PageJoueur.this.ia.getSelectedItem() == "joueur")
					ia = false;
				if(j1 == null){
					PageJoueur.this.initialiserJoueur(1,ia,nom.getText());
					nom.setText("");
				}else{
					PageJoueur.this.initialiserJoueur(2,ia,nom.getText());
					nom.setText("");
					setVisible(false);
				}
			}
		});
		setLocation(150, 150);
		add(nom);
		add(ia);
		add(valider);
		pack();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setVisible(true);
	}
	
	public void reinitialiserJoueurs() {
		this.j1 = null;
		this.j2 = null;
		setVisible(true);
	}

	private void initialiserJoueur(int numJoueur, boolean ia, String name){
		if(numJoueur == 1)
			this.j1 = new Joueur(ia,name,numJoueur);
		else
			this.j2 = new Joueur(ia,name,numJoueur);
	}

	public Joueur getJoueur1() {
		// TODO Auto-generated method stub
		return j1;
	}
	
	public Joueur getJoueur2() {
		// TODO Auto-generated method stub
		return j2;
	}
}