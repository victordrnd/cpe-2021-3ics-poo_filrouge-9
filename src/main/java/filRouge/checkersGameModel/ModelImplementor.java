package filRouge.checkersGameModel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 *         Cete classe fabrique et stocke toutes les PieceModel du Model dans
 *         une collection elle est donc responsable de rechercher et mettre � j
 *          ur les PieceModel (leur position)
 * 
 *         En revanche, elle n'est pas responsable des algorithme m�tiers l
 *         �s au d�placement des pi�ces (responsabilit� de la classe M
 *         del)
 */
public class ModelImplementor {

	// la collection de pi�ces en jeu - m�lange noires et blanches
	private Collection<PieceModel> pieces = null;

	public ModelImplementor() {
		super();
		pieces = new LinkedList<PieceModel>();

		// Cr�ation des pi�ces blanches et ajout dans la collection de pi�ces
		for (Coord coord : ModelConfig.WHITE_PIECE_COORDS) {
			pieces.add(new PawnModel(coord, PieceSquareColor.WHITE));
		}

		// Cr�ation des pi�ces noires et ajout dans la collection de pi�ces
		for (Coord coord : ModelConfig.BLACK_PIECE_COORDS) {
			pieces.add(new PawnModel(coord, PieceSquareColor.BLACK));
		}

	}

	public PieceSquareColor getPieceColor(Coord coord) {
		PieceSquareColor color = null;
		for (PieceModel piece : pieces) {
			if (piece.getCoord().equals(coord)) {
				color = piece.getPieceColor();
			}
		}
		return color;
	}

	public boolean isPiecehere(Coord coord) {
		for (PieceModel piece : pieces) {
			if (piece.getCoord().equals(coord))
				return true;
		}
		return false;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {

		boolean isMovePieceOk = false;

		if (!initCoord.equals(targetCoord) && Coord.coordonnees_valides(initCoord) && Coord.coordonnees_valides(targetCoord)) {
			PieceModel piece = this.findPiece(initCoord);
			return piece.isMoveOk(targetCoord, isPieceToTake);
		}

		return isMovePieceOk;
	}

	public boolean movePiece(Coord initCoord, Coord targetCoord) {

		boolean isMovePieceDone = false;
		if(!this.isPiecehere(targetCoord)){
			PieceModel piece = this.findPiece(initCoord);
			piece.move(targetCoord);
			return true;
		}

		return isMovePieceDone;
	}

	/**
	 * @param coord
	 * @return la pi�ce qui se trouve aux coordonn�es indiqu�es
	 */
	PieceModel findPiece(Coord coord) { // visibilit� Package pour �tre test� dans TestCherchersGameModel

		PieceModel findPiece = null;
		for (PieceModel piece : pieces) {
			if (piece.getCoord().equals(coord))
				findPiece = piece;
		}

		return findPiece;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * La m�thode toStrong() retourne une repr�sentation  de  liste de pi�ces s
	 *  us forme d'un tableau 2D
	 * 
	 */
	public String toString() {

		String st = "";
		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];

		// // cr�ation d'un tableau 2D avec les noms des pi�ces � partir de la liste
		// 
		//
		// de pi�ces
		// for(PieceModel piece : this.pieces) {
		//
		// PieceSquareColor color = piece.getPieceColor();
		// String stColor = (PieceSquareColor.WHITE.equals(color) ? "--B--" : "--N--" );
		//
		// int col = piece.getCoord().getColonne()-'a';
		// int lig = piece.getCoord().getLigne() -1;
		// damier[lig][col ] = stColor ;
		// }

		// Affichage du tableau formatt�
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
		return "Damier du model \n" + st;
	}

}
