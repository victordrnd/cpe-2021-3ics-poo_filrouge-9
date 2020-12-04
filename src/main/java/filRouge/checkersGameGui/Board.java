package filRouge.checkersGameGui;

import filRouge.checkersGameModel.BoardGame;
import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author francoiseperrin
 *         <p>
 *         Cette classe repr sente le damier de la vue
 *         <p>
 *         Elle tire les valeurs d'affichage d'une fabrique de constante
 *         (GuiConfig) public final static int size = 10; public final static
 *         double height = 600.0;
 * 
 */
class Board extends GridPane {

	private final BoardGame<Integer> controller;

	private int selectedPieceIndex; // index de la pi ce d placer

	private int nbCol, nbLig; // le nb de ligne et de colonne du damier
	private double height; // taille du damier en pixel

	private EventHandler<MouseEvent> squareListener; // l' couteur d' v nements souris sur les carr s du damier
	private EventHandler<MouseEvent> pieceListener; // l' couteur d' v nements souris sur les pi ces

	public Board(BoardGame<Integer> controller) {
		super();

		this.nbCol = nbLig = GuiConfig.SIZE;
		this.height = (double) View.heightObservable.get();
		View.heightObservable.addListener((obs, old, newVal) -> {
			this.height = (double) newVal;
		});
		this.controller = controller;

		this.squareListener = new SquareListener();
		this.pieceListener = new PieceListener();

		// initialisation du damier
		this.addSquaresOnCheckersBoard();
		this.addPiecesOnCheckersBoard();
	}

	private void addSquaresOnCheckersBoard() {

		Pane square = null;
		PieceSquareColor squareColor = null;

		for (int ligne = 0; ligne < this.nbLig; ligne++) {

			for (int col = 0; col < this.nbCol; col++) {
				// s lection de la couleur de la case
				if ((col % 2 == 0 && ligne % 2 == 0) || (col % 2 != 0 && ligne % 2 != 0)) {
					squareColor = PieceSquareColor.WHITE;
				} else {
					squareColor = PieceSquareColor.BLACK;
				}

				// cr ation d'un Pane
				square = GuiFactory.createSquare(squareColor);

				// ajout d'un couteur sur le carr
				square.setOnMouseClicked(squareListener);

				// gestion de la taille des Pane
				//square.setPrefHeight(this.height / this.nbLig); // TODO - remplacer : bad practice
				//square.setPrefWidth(this.height / this.nbCol); // TODO - remplacer : bad practice
				square.prefHeightProperty().bind(Bindings.divide(View.heightObservable, this.nbLig));
				square.prefWidthProperty().bind(Bindings.divide(View.heightObservable, this.nbCol));
				// ajout du carre sur le damier
				this.add(square, col, ligne);

			}
		}
	}

	private void addPiecesOnCheckersBoard() {

		int blackIndex;
		int whiteIndex;

		// ajout pions noirs et blancs sur les cases noires des 4 lignes du haut et du
		// bas
		// Rq : les index des carr s sur le damier varient de 0 nbLig*nbLig-1 (=99)
		for (int j = 0; j < this.nbLig * 4; j += 2) {

			// recherche index du carr noir qui contient la pi ce noire ou blanche
			if ((j / this.nbLig) % 2 == 0) {
				blackIndex = j + 1;
				whiteIndex = this.nbLig * this.nbLig - j - 2;
			} else {
				blackIndex = j;
				whiteIndex = this.nbLig * this.nbLig - j - 1;
			}

			// ajout effectif du pion noir puis du pion blanc sur les carr s identifi s
			addPieceOnSquare((Pane) this.getChildren().get(blackIndex), PieceSquareColor.BLACK);
			addPieceOnSquare((Pane) this.getChildren().get(whiteIndex), PieceSquareColor.WHITE);
		}

	}

	/**
	 * @param targetSquare
	 * @param pieceColor   Cr ation d'une pi ce et ajout dans le bon carr noir
	 */
	private void addPieceOnSquare(Pane targetSquare, PieceSquareColor pieceColor) {

		Canvas pieceGUI;

		// cr ation de la pi ce
		pieceGUI = GuiFactory.createPiece(pieceColor);

		// ajout d'un couteur de souris
		// si la pi ce est s lectionn e, elle sera supprim de son emplacement actuel
		// et repositionn e sur une autre case
		pieceGUI.setOnMouseClicked(this.pieceListener);
		pieceGUI.heightProperty().bind(targetSquare.prefHeightProperty());
		pieceGUI.widthProperty().bind(targetSquare.prefWidthProperty());
		// Ajout de la pi ce sur le carr noir
		targetSquare.getChildren().add(pieceGUI);


	}

	/**
	 * @param selectedSquareIndex
	 * @param targetSquareIndex
	 * @param tookPieceIndex      Cette m thode est appel e par l' couteur
	 *                            SquareListener lorsqu'une case est cliqu e afin
	 *                            d'y d poser une pi ce pr c demment s lectionn e la
	 *                            promouvoir ventuellement en dame et supprimer l'
	 *                            ventuelle pi ce captur e
	 */
	private void movePieceOnGui(int selectedSquareIndex, int targetSquareIndex, Integer tookPieceIndex) {

		// la PieceGui de la vue est effectivement d plac e
		Board.this.movePiece(selectedSquareIndex, targetSquareIndex);
		if (tookPieceIndex != null) {
			Board.this.removePiece(tookPieceIndex);
		}
		// seul le d placement est g r dans cette version
	}

	/**
	 * @param selectedSquareIndex
	 * @param targetSquareIndex   Cette m thode est appel e indirectement par l'
	 *                            couteur SquareListener lorsqu'un carr est cliqu
	 *                            afin d'y d poser une pi ce pr c demment s lectionn
	 *                            e travers movePieceOnGui()
	 */
	private void movePiece(int selectedSquareIndex, int targetSquareIndex) {
		Node selectedPiece = null;
		Pane selectedPieceSquare = (Pane) Board.this.getChildren().get(selectedSquareIndex);
		Pane targetSquare = (Pane) Board.this.getChildren().get(targetSquareIndex);

		if (!selectedPieceSquare.getChildren().isEmpty())
			selectedPiece = selectedPieceSquare.getChildren().get(0);
		if (selectedPiece != null) {
			targetSquare.getChildren().add(selectedPiece);
			selectedPieceSquare.getChildren().removeAll();
		}
	}

	/**
	 * 
	 * @param removeSquare
	 * 
	 *                     Cette m thode est appel e indirectement par l' couteur
	 *                     SquareListener suppression effective d'une pi ce
	 */
	private void removePiece(int tookPieceIndex) {

		// clear le carr d'origine de la pi ce supprim e
		Pane tookPieceSquare = (Pane) Board.this.getChildren().get(tookPieceIndex);
		tookPieceSquare.getChildren().clear();
	}

	/**
	 * @return selectedPieceIndex Cette m thode est appel e par l' couteur
	 *         SquareListener lorsqu'un clic est effectu sur une case apr s qu'une
	 *         pi ce ait t s lectionn e
	 */
	private int getSelectedPieceIndex() {
		return this.selectedPieceIndex;
	}

	/**
	 * @param selectedPieceIndex Cette m thode est appel e par l' couteur
	 *                           PieceListener lorsqu'un clic est effectu sur une pi
	 *                           ce avant de la d placer
	 */
	private void setSelectedPieceIndex(int selectedPieceIndex) {
		this.selectedPieceIndex = selectedPieceIndex;
	}

	/**
	 * @return controller Cette m thode est appel par les couteurs de cases et de pi
	 *         ces pour connaitre le controller qui "parler"
	 */
	private BoardGame<Integer> getController() {
		return this.controller;
	}

	/**
	 * @author francoise.perrin
	 *
	 *         Objet qui coute les v nements Souris sur les cases du damier et agit
	 *         en cons quence
	 */
	private class SquareListener implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent mouseEvent) {

			int selectedSquareIndex = Board.this.getSelectedPieceIndex();

			// Recherche SquareGUI s lectionn
			Pane square = (Pane) mouseEvent.getSource();
			int targetSquareIndex = Board.this.getChildren().indexOf(square);

			// Pour l'instant, on ne supprime pas les pi ces pouvant se trouver sur le
			// trajet
			Integer tookPieceIndex = null;

			// Check if piece selectedSquareIndex exists
			boolean pieceExists = (Integer) (selectedSquareIndex) != null;
			if (pieceExists) {
				if (Board.this.controller.isMovePieceOk(selectedSquareIndex, targetSquareIndex)) {
					// la PieceGui de la vue est effectivement d plac e et ventuellement promue
					// l' ventuelle pi ce interm diaire est supprim e dans la vue
					tookPieceIndex = Board.this.controller.movePiece(selectedSquareIndex, targetSquareIndex);
					Board.this.movePieceOnGui(selectedSquareIndex, targetSquareIndex, tookPieceIndex);
					checkIfPromoteNeeded(targetSquareIndex);
				}
			}

			// On vite que le parent ne r cup re l'event
			mouseEvent.consume();
		}
		
		private void checkIfPromoteNeeded(int targetSquareIndex){
			if (targetSquareIndex < 10 || targetSquareIndex > 90) {
				Pane targetSquare = (Pane) Board.this.getChildren().get(targetSquareIndex);
				if (!targetSquare.getChildren().isEmpty()) {
					PieceGui cs = (PieceGui) targetSquare.getChildren().get(0);
					GuiFactory.promoteToQueen(cs);
				}
			}
		}
	}

	/**
	 * @author francoise.perrin
	 *
	 *         Objet qui coute les v nements Souris sur les cases du damier et agit
	 *         en cons quence
	 */
	private class PieceListener implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent mouseEvent) {

			// Recherche PieceGui s lectionn e
			Canvas selectedPiece = (Canvas) mouseEvent.getSource();

			// Recherche coordonn e du carr
			Pane parentSquare = (Pane) selectedPiece.getParent();
			int squareIndex = Board.this.getChildren().indexOf(parentSquare);

			// l'index de la PieceGui de la vue d placer est fix e
			if (Board.this.controller.isPieceMoveable(squareIndex)) {
				Board.this.setSelectedPieceIndex(squareIndex);
			}

			mouseEvent.consume();
		}
	}

}
