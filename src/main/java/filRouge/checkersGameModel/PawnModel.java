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
		int factor = pieceColor.equals(PieceSquareColor.WHITE) ? 1 : -1;
		int diagonal_move_count = Math.abs(this.coord.getLigne() - targetCoord.getLigne());
		if(diagonal_move_count > 1){
			for(int i = 1; i < diagonal_move_count; i++){
				Coord coord;
				boolean droite = ((this.coord.getColonne() < targetCoord.getColonne()) ^ (factor== -1));
				if (droite) {
					coord = new Coord((char) (this.getCoord().getColonne() + (factor*i)), this.getCoord().getLigne() + (factor*i));
				} else {
					coord = new Coord((char) (this.getCoord().getColonne() - (factor*i)), this.getCoord().getLigne() + (factor*i) );
				}
				coordsOnItinerary.add(coord);
			}
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
