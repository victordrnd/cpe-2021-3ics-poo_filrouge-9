package filRouge.checkersGameModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

public class PawnModel extends PieceModelAbstract implements Promotable {

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake) {
		int isPieceToTakeInt = isPieceToTake ? 2 : 1;
		int factor =1;
		int vertical_offset = (targetCoord.getLigne() - this.getCoord().getLigne());
		int horizontal_offset = Math.abs(targetCoord.getColonne() - this.getCoord().getColonne());
		if(!isPieceToTake){
			factor = pieceColor.equals(PieceSquareColor.BLACK) ? -1 : 1;
		}else{
			vertical_offset = Math.abs(vertical_offset);
		}
		if ((vertical_offset * factor == isPieceToTakeInt) && (horizontal_offset == isPieceToTakeInt) && Math.abs(vertical_offset) == horizontal_offset)
			return true;
		return false;
	}

	@Override
	public boolean isPromotable() {
		if (this.pieceColor.equals(PieceSquareColor.BLACK)){
			return this.coord.getLigne() == 1;
		}else{
			return this.coord.getLigne() == 10;
		}
	}


	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		List<Coord> coordsOnItinerary = new ArrayList<Coord>();
		int diagonal_move_count = Math.abs(this.coord.getLigne() - targetCoord.getLigne());
		boolean go_down = this.coord.getLigne() - targetCoord.getLigne() > 0;
		int factor = go_down ? -1 : 1;
		if(diagonal_move_count > 1){
			for(int i = 1; i < diagonal_move_count; i++){
				Coord coord;
				boolean droite = ((this.coord.getColonne() < targetCoord.getColonne()) ^ go_down);
				if (droite) 
					coord = new Coord((char) (this.getCoord().getColonne() + (factor * i)), this.getCoord().getLigne() + (factor * i));
				else 
					coord = new Coord((char) (this.getCoord().getColonne() - (factor * i)), this.getCoord().getLigne() + (factor * i) );
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
