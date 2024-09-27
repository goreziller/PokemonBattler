import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable
{
    @FXML private AnchorPane pane;
    @FXML private ImageView field1;
    @FXML private ImageView field2;
    @FXML private ImageView field3;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Label level;
    private static Stage stage;
    private int fieldOneNumber = (int) (Math.random() * 100);
    private int fieldTwoNumber = (int) (Math.random() * 100);
    private int fieldThreeNumber = (int) (Math.random() * 100);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image background = new Image("file:src/main/Bilder/GameBackground.jpg");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        pane.setBackground(bg);
        level.setText(String.valueOf(SelectPokemonController.getPlayer().getLevel()));
        decideWhichField(fieldOneNumber, field1, button1);
        decideWhichField(fieldTwoNumber, field2, button2);
        decideWhichField(fieldThreeNumber, field3, button3);
    }

    public void decideWhichField(int chance, ImageView field, Button button)
    {
        System.out.println(chance);
        if(chance < 60)
        {
            field.setImage(new Image("file:src/main/Bilder/FightField.jpg"));
            button.setOnAction(e -> createButtonAction("FightMenu.fxml", true));
        }
        else if(chance < 90)
        {
            field.setImage(new Image("file:src/main/Bilder/CatchField.jpg"));
            button.setOnAction(e -> createButtonAction("CatchMenu.fxml", false));
        }
        else
        {
            field.setImage(new Image("file:src/main/Bilder/CloverLeafField.jpg"));
            button.setOnAction(e -> createLuck());
        }
    }

    public void createButtonAction(String path, boolean kampf)
    {
        PlayerController.getStage().close();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            stage = new Stage();
            if(kampf)
            {
                stage.setTitle("Kampf Menü");
            }
            else
            {
                stage.setTitle("Fang Menü");
            }
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/Bilder/GameIcon.png"));
            stage.setOnCloseRequest(e ->
            {
                stage.close();
                SelectPokemonController.getPlayer().getStatistik().stopTimer();
                StoredDataManagement storedDataManagement = new StoredDataManagement();
                storedDataManagement.playerSave(SelectPokemonController.getPlayer());
                System.out.println("Fenster geschlossen");
                System.exit(0);
            });
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createLuck()
    {
        System.out.println("switch shiny!");
        SelectPokemonController.getPlayer().getPokemonliste().get(SelectPokemonController.getPlayer().getSelectedIndex()).setShiny(!SelectPokemonController.getPlayer().getPokemonliste().get(SelectPokemonController.getPlayer().getSelectedIndex()).isShiny());
        SelectPokemonController.getPlayer().getPokemonliste().get(SelectPokemonController.getPlayer().getSelectedIndex()).createMove(SelectPokemonController.getPlayer().getPokemonliste().get(SelectPokemonController.getPlayer().getSelectedIndex()));
        Thread thread = new Thread(() -> Platform.runLater(() -> returnToMenue()));
        thread.start();
    }

    public static Stage getStage()
    {
        return stage;
    }

    public void returnToMenue()
    {
        PlayerController.getStage().close();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PlayerMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            SelectPokemonController.getStage().setTitle("Player Menü");
            SelectPokemonController.getStage().setScene(scene);
            SelectPokemonController.getStage().getIcons().add(new Image("file:src/main/Bilder/GameIcon.png"));
            SelectPokemonController.getStage().setOnCloseRequest(e ->
            {
                SelectPokemonController.getStage().close();
                SelectPokemonController.getPlayer().getStatistik().stopTimer();
                StoredDataManagement storedDataManagement2 = new StoredDataManagement();
                storedDataManagement2.playerSave(SelectPokemonController.getPlayer());
                System.out.println("Fenster geschlossen");
                System.exit(0);
            });
            SelectPokemonController.getStage().show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
