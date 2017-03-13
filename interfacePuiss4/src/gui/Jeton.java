package gui;

import java.awt.Color;

public enum Jeton {

	Vide ("B"),
	
	Rouge ("R"),

	Jaune ("J");
	
	private String couleur;
	
	private Jeton(String couleur) 
	{
		this.couleur=couleur;
	}
	
	public Color getColor() 
	{
		Color c = new Color(0);
		if(this.couleur == Jeton.Vide.toString()){
			c = Color.WHITE;
		}else if(this.couleur == Jeton.Rouge.toString()){
			c = Color.RED;
		}if(this.couleur == Jeton.Jaune.toString()){
			c = Color.YELLOW;
		}
		return c;
	}
	
	@Override
	public String toString() 
	{
		return this.couleur;
	}
}
