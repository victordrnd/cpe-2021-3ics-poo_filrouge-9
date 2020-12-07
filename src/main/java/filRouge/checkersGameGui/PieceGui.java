package filRouge.checkersGameGui;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PieceGui extends Canvas {

    public PieceGui(PieceSquareColor pieceColor) {
        super();
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        this.setHeight(GuiConfig.HEIGHT / GuiConfig.SIZE);
        this.setWidth(GuiConfig.HEIGHT / GuiConfig.SIZE);
        View.heightObservable.addListener(evt -> draw());
        GuiFactory.getGUIConfig().addListener((MapChangeListener<? super Object,? super Object>) ev -> draw());
        this.heightProperty().bind(View.heightObservable);
        Color color = Color.rgb(40, 40, 40);
        if (pieceColor == PieceSquareColor.WHITE) {
            color = Color.rgb(245, 245, 245);
        }
        graphicsContext.setFill(color);

       draw();
    }

    private void draw() {
        double rowWidth = this.getWidth();
        double rowHeight = this.getHeight();
        // System.out.println(rowHeight);
        int offset = (int) ((rowWidth + rowHeight) / 6);
        double width = rowWidth - offset;
        double height = rowHeight - offset;
        double upperLeftWidth = offset / 2;
        double upperLeftHeight = offset / 2;
        // graphicsContext.fillArc(upperLeftWidth, upperLeftHeight, width, height, 30,
        // 300, ArcType.ROUND);
        if(GuiFactory.getGUIConfig().get("Forme") ==  PieceShape.HOMEPOD){
            getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
            getGraphicsContext2D().fillRoundRect(upperLeftWidth, upperLeftHeight, width, height, 30, 300);
        }else if(GuiFactory.getGUIConfig().get("Forme") ==  PieceShape.CIRCLE){
            getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
            getGraphicsContext2D().fillOval(upperLeftWidth, upperLeftHeight, width, height);
        }
    }

    public void promoteToQueen() {
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        double rowWidth = this.getWidth();
        double rowHeight = this.getHeight();
        int offset = (int) ((rowWidth + rowHeight) / 6);
        double width = rowWidth - offset;
        double height = rowHeight - offset;
        graphicsContext.setStroke(Color.rgb(218, 165, 32));
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeOval(width / 2 + 5, height / 2 + 5, 10, 10);
        graphicsContext.setFill(Color.rgb(255, 222, 173));
        graphicsContext.fillOval(width / 2 + 5, height / 2 + 5, 10, 10);
    }

}
