package filRouge.checkersGameGui;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class GuiFactory {

	public static Pane createSquare(PieceSquareColor squareColor) {
		return new SquareGui(squareColor);
	}

	public static Canvas createPiece(PieceSquareColor pieceColor) {
		return new PieceGui(pieceColor);
	}

	public static void promoteToQueen(PieceGui piece_gui){
		piece_gui.promoteToQueen();
	}
}
