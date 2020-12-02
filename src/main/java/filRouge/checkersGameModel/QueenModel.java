
package filRouge.checkersGameModel;

import java.util.ArrayList;
import java.util.List;

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

	
	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		List<Coord> coordsOnItinerary = new ArrayList<Coord>();
		int line_offset = this.getCoord().getLigne() - targetCoord.getLigne();
		int factor = line_offset > 0 ? -1 : 1; //
		int diagonal_move_count = Math.abs(line_offset);
		if(diagonal_move_count > 1){
			for(int i = 1; i < diagonal_move_count; i++){
				Coord coord;
				boolean droite = (this.getCoord().getColonne() < targetCoord.getColonne());
				if (droite) 
					coord = new Coord((char) (this.getCoord().getColonne() + i), this.getCoord().getLigne() + (i*factor));
				else 
					coord = new Coord((char) (this.getCoord().getColonne() - i), this.getCoord().getLigne() + (i*factor));
				coordsOnItinerary.add(coord);
			}
		}
		return coordsOnItinerary;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [Reine : "+getPieceColor().toString().charAt(0) + getCoord() + "]";
	}
}

