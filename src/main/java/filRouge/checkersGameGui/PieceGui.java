package filRouge.checkersGameGui;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PieceGui extends Canvas {

    public PieceGui(PieceSquareColor pieceColor) {
        super();
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        // Gestion de la taille des canvas // TODO - remplacer atelier 4 : bad practice
        this.setHeight(GuiConfig.HEIGHT / GuiConfig.SIZE);
        this.setWidth(GuiConfig.HEIGHT / GuiConfig.SIZE);

        // la couleur est d finie en dur
        Color color = Color.rgb(40, 40, 40);
        if (pieceColor == PieceSquareColor.WHITE) {
            color = Color.WHITE;
        }
        graphicsContext.setFill(color);

        // calcul taille et position pi ce en fonction du carr
        double rowWidth = this.getWidth();
        double rowHeight = this.getHeight();
        int offset = (int) ((rowWidth + rowHeight) / 6);
        double width = rowWidth - offset;
        double height = rowHeight - offset;
        double upperLeftWidth = offset / 2;
        double upperLeftHeight = offset / 2;
        // graphicsContext.fillArc(upperLeftWidth, upperLeftHeight, width, height, 30,
        // 300, ArcType.ROUND);
        graphicsContext.fillRoundRect(upperLeftWidth, upperLeftHeight, width, height, 30, 300);

    }

    void promoteToQueen() {
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        double rowWidth = this.getWidth();
        double rowHeight = this.getHeight();
        int offset = (int) ((rowWidth + rowHeight) / 6);
        double width = rowWidth - offset;
        double height = rowHeight - offset;
        graphicsContext.setStroke(Color.rgb(220, 20, 60).brighter());
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeOval(width / 2 + 5, height / 2 + 5, 10, 10);
        graphicsContext.setFill(Color.rgb(218, 165, 32));
        graphicsContext.fillOval(width / 2 + 5, height / 2 + 5, 10, 10);
    }

}
