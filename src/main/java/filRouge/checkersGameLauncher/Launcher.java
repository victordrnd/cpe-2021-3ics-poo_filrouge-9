package filRouge.checkersGameLauncher;


import filRouge.checkersGameController.Controller;
import filRouge.checkersGameGui.GuiConfig;
import filRouge.checkersGameGui.View;
import filRouge.checkersGameModel.BoardGame;
import filRouge.checkersGameModel.Coord;
import filRouge.checkersGameModel.Model;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launcher extends Application {

	private BoardGame<Coord> model;
	private BoardGame<Integer> controller;
	private Parent view;
	
	public static void main (String[] args) {

		Launcher.launch();
	}

	@Override
	public void init () throws Exception {
		super.init();
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui g re les aspects m tier du jeu de dame :
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.model = new Model();

		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui contr le les actions de la vue et les transmet au model
		// il renvoie les r ponses du model   la vue
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.controller = new Controller(model);
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Fen tre dans laquelle se dessine le damier
		// et qui propose un menu pour changer la couleur des cases
		// et la forme des pions
		///////////////////////////////////////////////////////////////////////////////////////
				
		this.view = new View(controller);
	}


	@Override
	public void start (Stage primaryStage) {

		primaryStage.setScene(new Scene(this.view, GuiConfig.HEIGHT, GuiConfig.HEIGHT));
		primaryStage.setTitle("Ceci est la fenetre de mon jeu de dames - Version initiale");
		primaryStage.show();
	}

	
}

