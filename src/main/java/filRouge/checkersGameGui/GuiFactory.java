package filRouge.checkersGameGui;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;


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
