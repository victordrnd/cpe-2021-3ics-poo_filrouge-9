package filRouge.checkersGameModel;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 *         Cete classe fabrique et stocke toutes les PieceModel du Model dans
 *         une collection elle est donc responsable de rechercher et mettre j ur
 *         les PieceModel (leur position)
 * 
 *         En revanche, elle n'est pas responsable des algorithme m tiers l s au
 *         d placement des pi ces (responsabilit de la classe M del)
 */
public class ModelImplementor {

	// la collection de pi ces en jeu - m lange noires et blanches
	private Collection<PieceModel> pieces = null;

	public ModelImplementor() {
		super();
		pieces = new LinkedList<PieceModel>();

		// Cr ation des pi ces blanches et ajout dans la collection de pi ces
		for (Coord coord : ModelConfig.WHITE_PIECE_COORDS) {
			pieces.add(new PawnModel(coord, PieceSquareColor.WHITE));
		}

		// Cr ation des pi ces noires et ajout dans la collection de pi ces
		for (Coord coord : ModelConfig.BLACK_PIECE_COORDS) {
			pieces.add(new PawnModel(coord, PieceSquareColor.BLACK));
		}

	}

	public PieceSquareColor getPieceColor(Coord coord) {
		if (isPiecehere(coord)) {
			return findPiece(coord).getPieceColor();
		}
		return null;
	}

	public boolean isPiecehere(Coord coord) {
		return findPiece(coord) != null;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {
		PieceModel piece = this.findPiece(initCoord);
		return piece.isMoveOk(targetCoord, isPieceToTake);
	}

	public boolean movePiece(Coord initCoord, Coord targetCoord) {
		if (!this.isPiecehere(targetCoord)) {
			PieceModel piece = this.findPiece(initCoord);
			piece.move(targetCoord);
			System.out.println(this.toString());
			return true;
		}
		return false;
	}

	/**
	 * @param coord
	 * @return la piece qui se trouve aux coordonnees indiquees
	 */
	private PieceModel findPiece(Coord coord) { // visibilit Package pour tre test dans TestCherchersGameModel

		PieceModel findPiece = null;
		for (PieceModel piece : pieces) {
			if (piece.getCoord().equals(coord))
				findPiece = piece;
		}
		return findPiece;
	}

	List<Coord> getCoordsOnItinerary(Coord initCoord, Coord targetCoord) {
		PieceModel piece = findPiece(initCoord);
		return piece.getCoordsOnItinerary(targetCoord);
	}

	boolean isPromotable(Coord coord) {
		PieceModel piece = this.findPiece(coord);
		if(piece instanceof PawnModel){
			return ((Promotable)piece).isPromotable();
		}
		return false;
	}

	void promoteToQueen(Coord coord){
		if(isPiecehere(coord)){
			PieceModel piece = this.findPiece(coord);
			QueenModel queen = new QueenModel(piece.getCoord(), piece.getPieceColor());
			pieces.remove(piece);
			pieces.add(queen);
		}
	}

	boolean removePiece(Coord coord) {
		if(isPiecehere(coord)){
			PieceModel piece = this.findPiece(coord);
			return pieces.remove(piece);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * La m thode toStrong() retourne une repr sentation de liste de pi ces s us
	 * forme d'un tableau 2D
	 * 
	 */
	public String toString() {

		String st = "";
		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];

		// List<PieceModel> list = new ArrayList<PieceModel>();
		// list.addAll(this.pieces);
		// Collections.sort(list, Comparator.comparing(el->el.getCoord().getColonne()));
		// ListIterator<PieceModel> iterator = list.listIterator();
		// while(iterator.hasNext()){
		// 	if(iterator.nextIndex() % 5 == 0){
		// 		System.out.print("\n");
		// 	}
		// 	System.out.print(iterator.next());
		// } 


		
		// // cr ation d'un tableau 2D avec les noms des pi ces partir de la liste
		//
		//
		// de pi ces
		for (PieceModel piece : this.pieces) {

			PieceSquareColor color = piece.getPieceColor();
			String stColor = (PieceSquareColor.WHITE.equals(color) ? "--B--" : "--N--");

			int col = piece.getCoord().getColonne() - 'a';
			int lig = piece.getCoord().getLigne() - 1;
			damier[lig][col] = stColor;
		}

		// Affichage du tableau formatt
		st = "     a      b      c      d      e      f      g      h      i      j\n";
		for (int lig = 9; lig >= 0; lig--) {
			st += (lig + 1) + "  ";
			for (int col = 0; col <= 9; col++) {
				String stColor = damier[lig][col];
				if (stColor != null) {
					st += stColor + "  ";
				} else {
					st += "-----  ";
				}
			}
			st += "\n";
		}
		return st;
	}

}
