package filRouge.checkersGameController;

import filRouge.checkersGameModel.BoardGame;
import filRouge.checkersGameModel.Coord;
import filRouge.checkersGameModel.ModelConfig;

/**
 * @author francoiseperrin
 *
 * Mthodes du controller sont invoques par couteurs du damier
 * Elles interrogent le Model pour savoir si les dplacements sont lgaux
 * Elles permettent galement de transformer les coordonnes des pices :
 * 	- index de 0  99 pour la view
 * 	- Coord (col, ligne) pour le model ['a'..'j'][10..1]
 * 
 * On peut dire que le controller se substitue au model vis  vis de la view
 * car la view et le model ne grent pas les coordonnes des pices de la mme faon
 */
public class Controller implements BoardGame<Integer> {

	private BoardGame<Coord> model;

	private int  length;

	public Controller(BoardGame<Coord> model) {
		this.model =  model;
		this.length = ModelConfig.LENGTH;
	}


	/**
	 * @param initSquareIndex
	 * @return true si la PieceGUI slectionne correspond  une PieceModel qui peut tre dplace 
	 * La coordonne d'origine du dplacement est alors conserve
	 */
	public boolean isPieceMoveable(Integer initSquareIndex) {
		boolean bool  = false;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		bool = this.model.isPieceMoveable(initCoord);
		return bool;
	}

	/**
	 * @param targetSquareIndex 
	 * @param squareIndex
	 * @return true si la case de destination peut recevoir la pice slectionne 
	 * c'est  dire si le dplacement est legal du point de vue du model
	 */
	public boolean isMovePieceOk(Integer initSquareIndex, Integer targetSquareIndex) {
		boolean bool  = false;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		Coord targetCoord = this.transformIndexToCoord(targetSquareIndex);
		if (this.model.isPieceMoveable(initCoord)) {
			bool  = this.model.isMovePieceOk(initCoord, targetCoord);
		}
		return bool;
	}


	/**
	 * @param targetSquareIndex 
	 * @param squareIndex
	 * @return index de l'ventuelle pice  capturer, -1 sinon
	 * invite le model  effectuer le dplacement metier
	 */
	public Integer movePiece(Integer initSquareIndex, Integer targetSquareIndex) {
		Integer tookPieceIndex = null;
		Coord tookPieceCoord = null ;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		Coord targetCoord = this.transformIndexToCoord(targetSquareIndex);
		tookPieceCoord  = this.model.movePiece(initCoord, targetCoord);
		// les coord de la pice capture sont retourne  la vue pour l'effacer
		if (tookPieceCoord != null) {
			tookPieceIndex = transformCoordToIndex(tookPieceCoord);
		}
		return tookPieceIndex;
	}

	/**
	 * @param squareIndex
	 * @param length
	 * @return les coordonnes mtier calcules  partir de l'index du SquareGUI de la PieceGUI
	 */
	private Coord transformIndexToCoord (int squareIndex) {
		Coord coord = null;
		char col = (char) ((squareIndex)%length + 'a');
		int ligne = length - (squareIndex)/length;
		coord = new Coord(col, ligne);
		return coord;
	}

	private int transformCoordToIndex (Coord coord) {
		int squareIndex = -1;
		if (coord != null) {
			squareIndex = (length - coord.getLigne()) * length + (coord.getColonne()-'a');
		}
		return squareIndex;
	}


//	@Override
//	public boolean isPieceMoveable(Coord coord) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	@Override
//	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	@Override
//	public Coord movePiece(Coord initCoord, Coord targetCoord) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
