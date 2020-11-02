package filRouge.checkersGameGui;


import filRouge.checkersGameModel.BoardGame;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author francoise.perrin
 * 
 * Cette classe est la fen�tre du jeu de dames
 * Elle d�l�gue a un objet la gestion de l'affichage du damier
 * 
 */
public class View extends GridPane {

	public View (BoardGame<Integer> controller) {
		super();

		// le damier compos� de carr�s noirs et blancs
		// sur lesquels sont positionn�s des pi�ces noires ou blanches
		Pane board = new Board(controller);

		// gestion de la taille du damier
		double height = GuiConfig.HEIGHT;			// TODO - � remplacer (atelier 4) : bad practice
		board.setPrefSize( height, height);			// TODO - � remplacer (atelier 4) : bad practice

		// cr�ation d'un fond d'�cran qui contiendra le damier + les axes (atelier 2)
		BorderPane checkersBoard = new BorderPane();	
		
		// ajout du damier au centre du fond d'�cran
		checkersBoard.setCenter(board);
		
		// ajout du fond d'�cran � la vue
		this.add(checkersBoard, 0, 1);
	}

}


