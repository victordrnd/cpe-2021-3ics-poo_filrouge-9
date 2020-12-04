package filRouge.checkersGameGui;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import filRouge.checkersGameModel.BoardGame;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;

/**
 * @author francoise.perrin
 * 
 * Cette classe est la fen tre du jeu de dames
 * Elle d l gue a un objet la gestion de l'affichage du damier
 * 
 */
public class View extends GridPane {

	public static DoubleProperty heightObservable = new SimpleDoubleProperty(GuiConfig.HEIGHT);

	
	public View (BoardGame<Integer> controller) {
		super();

		// le damier compos  de carr s noirs et blancs
		// sur lesquels sont positionn s des pi ces noires ou blanches
		Pane board = new Board(controller);

		// gestion de la taille du damier
		//board.setPrefSize( heightObservable.get(), heightObservable.get());			// TODO -   remplacer (atelier 4) : bad practice
		this.sizeChangeListener();
		// this.prefWidthProperty().bind(heightObservable);
		// this.prefHeightProperty().bind(heightObservable);
		board.prefWidthProperty().bind(heightObservable);
		board.prefHeightProperty().bind(heightObservable);

		this.minWidthProperty().bind(heightObservable);
		this.minHeightProperty().bind(heightObservable);

		// cr ation d'un fond d' cran qui contiendra le damier + les axes (atelier 2)
		BorderPane checkersBoard = new BorderPane();	
		GridPane yaxis = new GridPane();
		yaxis.setPrefSize(30, heightObservable.get());
		yaxis.prefHeightProperty().bind(heightObservable);
		GridPane xaxis = new GridPane();
		xaxis.setPrefSize(heightObservable.get(), 30);
		for(int i = 0; i < 10; i++){
			Label xlabel = new Label(Character.toString((char)('a' + i)));
			xlabel.prefWidthProperty().bind(Bindings.divide(heightObservable, 10));
			xlabel.setAlignment(Pos.CENTER);
			xaxis.add(xlabel, i + 1, 0);

			Label label = new Label(Integer.toString(10-i));
			label.prefHeightProperty().bind(Bindings.divide(heightObservable, 10));
			label.setPrefWidth(10);
			label.setAlignment(Pos.CENTER);
			yaxis.add(label, 0, i);
		}
		checkersBoard.setTop(xaxis);
		checkersBoard.setLeft(yaxis);
		checkersBoard.setCenter(board);
		MenuGui menuBar = GuiFactory.createMenuBar();
		this.add(menuBar, 0, 0);
		this.add(checkersBoard, 0, 1);
	}

	private void sizeChangeListener(){
		this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                heightObservable.set((double) newValue);
            }
		});
		
		this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                heightObservable.set((double) newValue);
            }
        });
	}
}


