package filRouge.checkersGameModel;

import java.util.List;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

public interface PieceModel {
	
	
	/**
	 * @return the coord
	 */
	public Coord getCoord() ;
	
	/**
	 * @param coord the coord to set
	 * le d�placement d'une pi�ce change ses coordonn�es
	 */
	public void move(Coord coord);


	/**
	 * @return the pieceColor
	 */
	public PieceSquareColor getPieceColor() ;
	
	
	/**
	 * @param targetCoord
	 * @param isPieceToTake
	 * @return true si le d�placement est l�gal
	 */
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake);
	
	/**
	 * @param targetCoord
	 * @return liste des coordonn�es des cases travers�es par itin�raire de d�placement
	 */
	public List<Coord> getCoordsOnItinerary(Coord targetCoord);
	
}

