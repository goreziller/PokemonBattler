import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CatchController implements Initializable
{
    @FXML private AnchorPane pane;
    @FXML private ImageView field1;
    @FXML private ImageView field2;
    @FXML private ImageView field3;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    private Pokemon p1;
    private Pokemon p2;
    private Pokemon p3;

    private int randomLevel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image background = new Image("file:src/main/Bilder/CatchBackground.jpg");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        pane.setBackground(bg);
        loadAllPokemon();
        decideWhichField(p1, field1, button1, () -> buttonevent(1));
        decideWhichField(p2, field2, button2, () -> buttonevent(2));
        decideWhichField(p3, field3, button3, () -> buttonevent(3));
    }

    public void loadAllPokemon()
    {
        Thread t1 = new Thread(() ->
        {
            RandomizedPokemon randomizedPokemon = new RandomizedPokemon();
            p1 = randomizedPokemon.getRandomPokemon();
        });

        Thread t2 = new Thread(() ->
        {
            RandomizedPokemon randomizedPokemon = new RandomizedPokemon();
            p2 = randomizedPokemon.getRandomPokemon();
        });

        Thread t3 = new Thread(() ->
        {
            RandomizedPokemon randomizedPokemon = new RandomizedPokemon();
            p3 = randomizedPokemon.getRandomPokemon();
        });

        t1.start();
        t2.start();
        t3.start();

        try
        {
            t1.join();
            t2.join();
            t3.join();
            // Hier kommt der Code, der erst ausgeführt werden soll, wenn alle Threads fertig sind.
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public void decideWhichField(Pokemon p, ImageView field, Button button, Runnable function)
    {
        field.setImage(new Image("file:src/main/Bilder/pokemonGrass.png"));
        button.setOnAction(e -> function.run());
    }

    public void buttonevent(int i)
    {
        field1.setImage(new Image(p1.getSprites().getFront_default()));
        field2.setImage(new Image(p2.getSprites().getFront_default()));
        field3.setImage(new Image(p3.getSprites().getFront_default()));
        switch (i)
        {
            case 1:
                randomLevel = (int) (Math.random() * 20) + 5;
                p1.setLevel(randomLevel);
                SelectPokemonController.getPlayer().addPokemon(p1);
                break;
            case 2:
                randomLevel = (int) (Math.random() * 20) + 5;
                p2.setLevel(randomLevel);
                SelectPokemonController.getPlayer().addPokemon(p2);
                break;
            case 3:
                randomLevel = (int) (Math.random() * 20) + 5;
                p3.setLevel(randomLevel);
                SelectPokemonController.getPlayer().addPokemon(p3);
                break;
            default:
                break;
        }
        SelectPokemonController.getPlayer().newLevel();
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);

        Thread thread = new Thread(() ->
        {
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> returnToMenue());
        });
        thread.start();
    }

    public void returnToMenue()
    {
        GameController.getStage().close();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PlayerMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            SelectPokemonController.getStage().setTitle("Player Menü");
            SelectPokemonController.getStage().setScene(scene);
            SelectPokemonController.getStage().getIcons().add(new Image("file:src/main/Bilder/GameIcon.png"));
            SelectPokemonController.getStage().setOnCloseRequest(e -> {
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
        Player p = SelectPokemonController.getPlayer();
        Thread thread = new Thread(() ->
        {
                StoredDataManagement storedDataManagement = new StoredDataManagement();
                storedDataManagement.playerSave(p);
        });
        thread.start();
    }
}
