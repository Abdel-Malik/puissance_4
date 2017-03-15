package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PageJoueur extends JFrame{
	public static Dimension dim = new Dimension(300, 200);
	
	public PageJoueur(){
		super();
		setLocation(150, 150);
		setSize(dim);
		setVisible(true);
		add(new JTextField());
		pack();
	}
}
