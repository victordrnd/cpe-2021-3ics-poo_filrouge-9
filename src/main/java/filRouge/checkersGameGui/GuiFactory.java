package filRouge.checkersGameGui;

import java.util.Map;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class GuiFactory {

	private static ObservableMap<Object, Object> gUIConfigMap = FXCollections.observableHashMap();
	static {
		GuiFactory.gUIConfigMap.put(PieceSquareColor.BLACK, GuiConfig.CASEBLACK);
		GuiFactory.gUIConfigMap.put(PieceSquareColor.WHITE, GuiConfig.CASEWHITE);
		GuiFactory.gUIConfigMap.put("Forme", GuiConfig.SHAPE);
	}

	public static ObservableMap<Object, Object> getGUIConfig() {
		return GuiFactory.gUIConfigMap;
	}

	public static MenuGui createMenuBar(){
		return new MenuGui();
	}
	public static SquareGui createSquare(PieceSquareColor squareColor) {
		return new SquareGui(squareColor);
	}

	public static PieceGui createPiece(PieceSquareColor pieceColor) {
		return new PieceGui(pieceColor);
	}

	public static void promoteToQueen(PieceGui piece_gui) {
		piece_gui.promoteToQueen();
	}
}
