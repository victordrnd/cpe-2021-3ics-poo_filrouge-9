package filRouge.checkersGameModel;

import java.util.ArrayList;
import java.util.List;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;


public abstract class PieceModelAbstract implements PieceModel{
    protected Coord coord;
    protected PieceSquareColor pieceColor;

    public PieceModelAbstract(Coord coord, PieceSquareColor pieceColor){
        this.coord = coord;
        this.pieceColor = pieceColor;
    }


    @Override
	public Coord getCoord() {
		Coord ret = this.coord;
		return ret;
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
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		List<Coord> coordsOnItinerary = new ArrayList<Coord>();
		int factor = pieceColor.equals(PieceSquareColor.WHITE) ? 1 : -1;
		int diagonal_move_count = Math.abs(this.coord.getLigne() - targetCoord.getLigne());
		if(diagonal_move_count > 1){
			for(int i = 1; i < diagonal_move_count; i++){
				Coord coord;
				boolean droite = ((this.coord.getColonne() < targetCoord.getColonne()) ^ (pieceColor.equals(PieceSquareColor.BLACK)));
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
}
