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
		if ((targetCoord.getLigne() - this.getCoord().getLigne()) * factor == isPieceToTakeInt
				&& Math.abs(targetCoord.getColonne() - this.getCoord().getColonne()) == isPieceToTakeInt)
			return true;
		return false;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		List<Coord> coordsOnItinerary = new ArrayList<Coord>();
		// int hCount = (targetCoord.getColonne() - this.coord.getColonne()); // Droite
		// +1 - Gauche -1
		// // hCount *= pieceColor.equals(PieceSquareColor.WHITE) ? -1 : 1;
		// int vCount = (targetCoord.getLigne() - this.coord.getLigne());
		// // vCount *= pieceColor.equals(PieceSquareColor.WHITE) ? -1 : 1;
		// boolean droite = hCount > 0;
		// for (int i = this.coord.getLigne() + 1; i < Math.abs(vCount); i++) {
		// Coord coord;
		// int col_to_add = (i * hCount);
		// char col = (char) (this.coord.getColonne() + (char) col_to_add);
		// coord = new Coord(col, i);
		// coordsOnItinerary.add(coord);
		// }
		// int factor = pieceColor.equals(PieceSquareColor.WHITE) ? 1 : -1;
		if (Math.abs(this.coord.getLigne() - targetCoord.getLigne()) > 1) {
			Coord coord;
			if (pieceColor.equals(PieceSquareColor.WHITE)) {
				boolean droite = this.coord.getColonne() < targetCoord.getColonne();
				if (droite) {
					coord = new Coord((char) (this.getCoord().getColonne() + 1), this.getCoord().getLigne() + 1);
				} else {
					coord = new Coord((char) (this.getCoord().getColonne() - 1), this.getCoord().getLigne() + 1);
				}
			} else {
				boolean droite = this.coord.getColonne() > targetCoord.getColonne();
				if (droite) {
					coord = new Coord((char) (this.getCoord().getColonne() - 1), this.getCoord().getLigne() - 1 );
				} else {
					coord = new Coord((char) (this.getCoord().getColonne() + 1), this.getCoord().getLigne() - 1);
				}
			}
			coordsOnItinerary.add(coord);
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
