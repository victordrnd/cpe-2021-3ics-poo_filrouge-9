package filRouge.checkersGameModel;

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
		if (Math.abs(targetCoord.getLigne() - this.getCoord().getLigne()) == isPieceToTakeInt && Math.abs(targetCoord.getColonne() - this.getCoord().getColonne()) == isPieceToTakeInt)
			return true;
		return false;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		List<Coord> coordsOnItinerary = null;
		// // 1 tests en opti // 2 en pas opti -> 2 boucles (ligne/ colonne)
		// if(targetCoord.getLigne() < this.getCoord().getLigne()){
		// for(int i = this.getCoord().getLigne(); i < targetCoord.getLigne(); i--){
		// }
		// }

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
