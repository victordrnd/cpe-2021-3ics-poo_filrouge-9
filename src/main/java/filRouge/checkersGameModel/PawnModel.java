package filRouge.checkersGameModel;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

public class PawnModel extends PieceModelAbstract {

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake) {
		int isPieceToTakeInt = isPieceToTake ? 2 : 1;
		int factor = pieceColor.equals(PieceSquareColor.BLACK) ? -1 : 1;
		int vertical_offset = (targetCoord.getLigne() - this.getCoord().getLigne());
		int horizontal_offset = Math.abs(targetCoord.getColonne() - this.getCoord().getColonne());
		if ((vertical_offset * factor == isPieceToTakeInt) && (horizontal_offset == isPieceToTakeInt) && Math.abs(vertical_offset) == horizontal_offset)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [Pion : " + pieceColor.toString().charAt(0) + coord + "]";
	}

}
