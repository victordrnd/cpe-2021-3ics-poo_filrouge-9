package filRouge.checkersGameModel;

import java.util.ArrayList;
import java.util.List;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

public class PawnModel implements PieceModel {

	private Coord coord;
	private PieceSquareColor pieceColor;

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super();
		this.coord = coord;
		this.pieceColor = pieceColor;
	}

	@Override
	public Coord getCoord() {
		return coord;
	}

	@Override
	public void move(Coord coord) {
		Coord c = new Coord(coord.getColonne(), coord.getLigne());
		this.coord = c;
	}

	@Override
	public PieceSquareColor getPieceColor() {
		return pieceColor;
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake) {
		int isPieceToTakeInt = isPieceToTake ? 2 : 1;
		int factor = pieceColor.equals(PieceSquareColor.BLACK) ? -1 : 1;
		if ((targetCoord.getLigne() - this.getCoord().getLigne()) * factor == isPieceToTakeInt && Math.abs(targetCoord.getColonne() - this.getCoord().getColonne()) == isPieceToTakeInt)
			return true;
		return false;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		List<Coord> coordsOnItinerary = new ArrayList<Coord>();
		int sens = (this.getCoord().getColonne() - targetCoord.getColonne()) * -1; //Droite +1 - Gauche -1 
		char colonne = this.coord.getColonne(); //colonne la plus petite 
		for (int i = this.coord.getLigne() + 1; i < targetCoord.getLigne(); i++) {
			if(this.getPieceColor() == PieceSquareColor.WHITE){
				colonne += sens; 
			}
			else {
				colonne -= sens;
			}
			Coord c = new Coord(colonne, i);
			coordsOnItinerary.add(c); 
			}
		return coordsOnItinerary;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [" + pieceColor.toString().charAt(0) + coord + "]";
	}

}
