package filRouge.checkersGameModel;

import java.util.Collection;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 *
 *         Cette classe g re les aspects m tiers du jeu de dame ind pendement de
 *         toute vue
 * 
 *         Elle d l gue son objet ModelImplementor le stockage des PieceModel
 *         dans une collection
 * 
 *         Les pi ces sont capables de se d placer d'une case en diagonale si la
 *         case de destination est vide ou de 2 cases en diagonale s'il existe
 *         une pi ce du jeu oppos prendre sur le trajet
 * 
 * 
 */
public class Model implements BoardGame<Coord> {

	private ModelImplementor implementor; // objet qui g re la collection de PiecedModel
	private PieceSquareColor currentColor; // couleur du joueur courant
	private boolean isPieceToMove; // pi ce d placer

	public Model() {
		super();
		this.implementor = new ModelImplementor();
		this.currentColor = ModelConfig.BEGIN_COLOR;
		System.out.println(this);
	}

	/**
	 * @param coord
	 * @return true si la PieceModel qui se trouve aux coordonn es indiqu es est de
	 *         la couleur du joueur courant
	 */
	@Override
	public boolean isPieceMoveable(Coord coord) {
		// System.out.println(currentColor);
		return this.implementor.getPieceColor(coord).equals(currentColor);
	}

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return true si le d placement est l gal (s'effectue en diagonale, POUR
	 *         L'INSTANT sans prise) La PieceModel qui se trouve aux coordonn es
	 *         pass es en param tre est capable de r pondre cette question (par
	 *         l'interm diare du ModelImplementor)
	 * 
	 */
	@Override
	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord) {
		return this.implementor.isMovePieceOk(initCoord, targetCoord, false);
	}

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return null car POUR L'INSTANT, test sans prise
	 */
	@Override
	public Coord movePiece(Coord initCoord, Coord targetCoord) {
		if (this.implementor.movePiece(initCoord, targetCoord)) {
			this.currentColor = currentColor == PieceSquareColor.WHITE ? PieceSquareColor.BLACK
					: PieceSquareColor.WHITE;
			return targetCoord;
		}
		return initCoord;
	}

	@Override
	public String toString() {
		return implementor.toString();
	}

}