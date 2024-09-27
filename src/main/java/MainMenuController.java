import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javax.swing.*;

public class MainMenuController implements Initializable
{
    @FXML private Pane pane;
    @FXML private ComboBox playerlist;
    private static Stage stage;
    private static String playername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        javafx.scene.image.Image background = new Image("file:src/main/Bilder/MainBackground.jpg");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        pane.setBackground(bg);

        for (Player p: StoredDataManagement.getSpielerliste())
        {
            playerlist.getItems().add(p);
        }

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SelectPokemon.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            stage = new Stage();
            stage.setTitle("Pokemon Auswahl");
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/Bilder/GameIcon.png"));
            stage.setOnCloseRequest(e -> {
                stage.close();
                System.out.println("Fenster geschlossen");
                System.exit(0);
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadData()
    {
        Player player = (Player) playerlist.getValue();
        if(player != null)
        {
            SelectPokemonController selectPokemonController = new SelectPokemonController();
            Main.getPrimaryStage().close();
            selectPokemonController.loadData(player);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Es muss ein Spieler Ausgewählt sein!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void newGame()
    {
        StoredDataManagement storedDataManagement = new StoredDataManagement();
        ArrayList<Player> players = storedDataManagement.getSpielerliste();
        do
        {
            playername = JOptionPane.showInputDialog(null,"Gib deinen Namen ein", "Spielername", JOptionPane.QUESTION_MESSAGE);
            if(playername == null)
            {
                return;
            }
            else
            {
                for (Player player : players)
                {
                    if (player.getName().equals(playername))
                    {
                        playername = "";
                        JOptionPane.showMessageDialog(null, "Dieser Name exsistiert bereits!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
        }while(playername.trim().isEmpty());
        Main.getPrimaryStage().close();
        stage.show();
    }

    public static String getPlayername()
    {
        return playername;
    }

    public static Stage getStage()
    {
        return stage;
    }
}
