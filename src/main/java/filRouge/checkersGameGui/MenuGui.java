package filRouge.checkersGameGui;

import javax.swing.Action;

import filRouge.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public class MenuGui extends MenuBar {

    public MenuGui() {

        Menu displaySettings = new Menu("Paramètre d'affichage");

        MenuItem whiteSquareColor = new MenuItem("Couleur cases blanches");
        MenuItem blackSquareColor = new MenuItem("Couleur cases noires");
        
        Menu pieceShape = new Menu("Forme des pièces");
        
        RadioMenuItem homepodShape = new RadioMenuItem("Homepod");
        RadioMenuItem circleShape = new RadioMenuItem("Circle");
        homepodShape.setSelected(true);
        
        homepodShape.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                circleShape.setSelected(false);
                GuiFactory.getGUIConfig().put("Forme", PieceShape.HOMEPOD);
            }
        });

        circleShape.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                homepodShape.setSelected(false);
                GuiFactory.getGUIConfig().put("Forme", PieceShape.CIRCLE);
            }
        });

        pieceShape.getItems().addAll(homepodShape, circleShape);


        whiteSquareColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleColorPicker(PieceSquareColor.WHITE);
            }
        });

        blackSquareColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleColorPicker(PieceSquareColor.BLACK);
            }
        });
        displaySettings.getItems().addAll(whiteSquareColor, blackSquareColor, pieceShape);
        this.getMenus().add(displaySettings);
    }

    private void handleColorPicker(PieceSquareColor pieceSquareColor){
        ColorPicker colorPicker = new ColorPicker((Color) GuiFactory.getGUIConfig().get(pieceSquareColor));
        Dialog<ColorPicker> dialog = new Dialog<ColorPicker>();
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(ev -> window.hide());
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                Color color = ((ColorPicker)ev.getSource()).getValue();
                GuiFactory.getGUIConfig().put(pieceSquareColor, color);
            }
        });
        dialog.getDialogPane().setContent(colorPicker);
        dialog.showAndWait();
    }
}


