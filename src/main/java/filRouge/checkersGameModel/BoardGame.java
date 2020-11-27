package filRouge.checkersGameModel;

/**
 * @author francoise.perrin
 *
 * Cette interface définit le comportement attendu des jeux de plateau
 * @param <T>
 * 
 * 
 */
public interface BoardGame<T>  {

	/**
	 * @param coord
	 * @return true si la PieceModel qui se trouve aux coordonnées indiquées 
	 * est déplaéable :
	 * a minima de la couleur du joueur courant et é terme, celle qui rapporte le plus de coup
	 */
	public boolean isPieceMoveable(T  coord) ;

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return true si le déplacement est légal
	 * 
	 */
	public boolean isMovePieceOk(T initCoord, T targetCoord) ;


	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return éventuellement les coordonnées de la piéce capturée 
	 */
	public T movePiece(T initCoord, T targetCoord);


}