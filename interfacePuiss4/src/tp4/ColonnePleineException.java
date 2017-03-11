package tp4;

public class ColonnePleineException extends Exception {
	private int colonneSujet;
	
	public ColonnePleineException(int c){
		super();
		colonneSujet = c;
	}
	
	public int getColonnePleine(){
		return colonneSujet;
	}
}
