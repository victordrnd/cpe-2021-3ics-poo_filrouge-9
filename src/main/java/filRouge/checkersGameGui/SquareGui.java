package filRouge.checkersGameGui;

import java.util.concurrent.Callable;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SquareGui extends Pane {

    public SquareGui(PieceSquareColor squareColor) {
        super();
        
        ObjectProperty<Background> bgProperty = this.backgroundProperty();
        bgProperty.bind(Bindings.createObjectBinding(((Callable<Background>) () -> {
            BackgroundFill fill = new BackgroundFill((Color) GuiFactory.getGUIConfig().get(squareColor),
                    CornerRadii.EMPTY, Insets.EMPTY);
            return new Background(fill);
        }), (Observable) GuiFactory.getGUIConfig()));
    }

}
