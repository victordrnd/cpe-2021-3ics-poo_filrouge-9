package filRouge.checkersGameModel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Cete classe fabrique et stocke toutes les PieceModel du Model dans une collection 
 * elle est donc responsable de rechercher et mettre � jour les PieceModel (leur position)
 * 
 * En revanche, elle n'est pas responsable des algorithme m�tiers li�s au d�placement des pi�ces
 * (responsabilit� de la classe Model)
 */
public class ModelImplementor {

	// la collection de pi�ces en jeu - m�lange noires et blanches
	private Collection<PieceModel> pieces = null;	

	public ModelImplementor() {
		super();
		pieces = new LinkedList<PieceModel>();
	
		// Cr�ation des pi�ces blanches et ajout dans la collection de pi�ces
		for ( Coord coord : ModelConfig.WHITE_PIECE_COORDS){
			pieces.add(new PawnModel(coord, PieceSquareColor.WHITE));
		}
		
		// Cr�ation des pi�ces noires et ajout dans la collection de pi�ces
		for ( Coord coord : ModelConfig.BLACK_PIECE_COORDS){
			pieces.add(new PawnModel(coord, PieceSquareColor.BLACK));
		}

	}

	public PieceSquareColor getPieceColor(Coord coord) {
		PieceSquareColor color = null;
		
		// ToDo
		
		return color;
	}

	public boolean isPiecehere(Coord coord) {
		boolean isPiecehere = false;
		
		// ToDo
		
		return isPiecehere;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {

		boolean isMovePieceOk = false;
		
		// ToDo
		
		return isMovePieceOk;
	}


	public boolean movePiece(Coord initCoord, Coord targetCoord) {
		
		boolean isMovePieceDone = false;
		
		// ToDo
		
		return isMovePieceDone;
	}

	/**
	 * @param coord
	 * @return la pi�ce qui se trouve aux coordonn�es indiqu�es
	 */
	PieceModel findPiece(Coord coord) {		// visibilit� Package pour �tre test� dans TestCherchersGameModel
		//	private PieceModel findPiece(Coord coord) {		// A d�commenter apr�s les tests
		
		PieceModel findPiece = null;

		
		// ToDo
		
		return findPiece;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * La m�thode toStrong() retourne une repr�sentation 
	 * de la liste de pi�ces sous forme d'un tableau 2D
	 * 
	 */
	public String toString() {

		String st = "";
		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];

//		// cr�ation d'un tableau 2D avec les noms des pi�ces � partir de la liste de pi�ces
//		for(PieceModel piece : this.pieces) {
//
//			PieceSquareColor color = piece.getPieceColor();
//			String stColor = (PieceSquareColor.WHITE.equals(color) ? "--B--" : "--N--" );
//
//			int col = piece.getCoord().getColonne()-'a';
//			int lig = piece.getCoord().getLigne() -1;
//			damier[lig][col ] = stColor ;
//		}

		// Affichage du tableau formatt�
		st = "     a      b      c      d      e      f      g      h      i      j\n";
		for ( int lig = 9; lig >=0 ; lig--) {
			st += (lig+1) + "  ";
			for ( int col = 0; col <= 9; col++) {					 
				String stColor = damier[lig][col];				
				if (stColor != null) {						
					st += stColor + "  ";	
				} 
				else {
					st += "-----  ";
				}
			}
			st +="\n";
		}
		return "Damier du model \n" + st;	
	}


}
