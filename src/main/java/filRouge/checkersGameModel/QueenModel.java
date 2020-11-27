
package filRouge.checkersGameModel;


import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoiseperrin
 *
 *le mode de dplacement et de prise de la reine est diffrent de celui du pion
 *
 */
public class QueenModel extends PieceModelAbstract {
	

	
	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake) {
		int vertical_offset = Math.abs(targetCoord.getLigne() - this.getCoord().getLigne());
		int horizontal_offset = Math.abs(targetCoord.getColonne() - this.getCoord().getColonne());
		return vertical_offset == horizontal_offset;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [Reine : "+pieceColor.toString().charAt(0) + coord + "]";
	}
}

