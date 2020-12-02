package filRouge.checkersGameGui;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SquareGui extends Pane{

    public SquareGui(PieceSquareColor squareColor){
        super();
        Color color = PieceSquareColor.BLACK.equals(squareColor) ?
                GuiConfig.CASEBLACK : GuiConfig.CASEWHITE;
        this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }


}
