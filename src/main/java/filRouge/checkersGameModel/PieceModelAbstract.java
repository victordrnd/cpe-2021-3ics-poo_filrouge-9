package filRouge.checkersGameModel;



import filRouge.checkersGameNutsAndBolts.PieceSquareColor;


public abstract class PieceModelAbstract implements PieceModel{
    private Coord coord;
    private PieceSquareColor pieceColor;

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


    public abstract boolean isMoveOk(Coord targetCoord, boolean isPieceToTake);

    
    @Override
	public PieceSquareColor getPieceColor() {
		return pieceColor;
    }
    
    

    
}
