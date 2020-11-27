package filRouge.checkersGameModel;

/**
 * @author francoiseperrin
 *
 *         Coordonn es des PieceModel
 */
public class Coord implements Comparable<Coord>{

	private char colonne;
	private int ligne;
	static final int MAX = ModelConfig.LENGTH;

	public Coord(char colonne, int ligne) {
		super();
		this.colonne = colonne;
		this.ligne = ligne;
	}

	public char getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public static boolean coordonnees_valides(Coord c) {

		if ((c.getColonne() >= 'a' && c.getColonne() <= 'j') && (c.getLigne() <= MAX && c.getLigne() >= 0)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Coord c) {
		// 0 ==
		// 1 <
		// 2 >

		//2 -> case 20 : 2*10
		// [2, 'b'] 21 : 2*10 + 'b' - 'a' : 21 

		Integer somme1 = this.getLigne() * 10 + this.getColonne() - 'a';
		Integer somme2 = c.getLigne() * 10 + c.getColonne() - 'a';
		
		return somme1.compareTo(somme2);		
	}


	@Override
	public String toString() {
		return "[" + ligne + "," + colonne + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Coord c = (Coord) o;
		return c.getLigne() == this.getLigne() && c.getColonne() == this.getColonne();
	}


}