import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerController implements Initializable
{
    @FXML private AnchorPane pane;
    @FXML private ImageView slot1;
    @FXML private ImageView slot2;
    @FXML private ImageView slot3;
    @FXML private ImageView slot4;
    @FXML private ImageView slot5;
    @FXML private ImageView slot6;
    @FXML private Label playername;
    @FXML private Label gametime;
    @FXML private Label level;
    @FXML private Label win;
    @FXML private Label lose;
    @FXML private Label levelslot1;
    @FXML private Label levelslot2;
    @FXML private Label levelslot3;
    @FXML private Label levelslot4;
    @FXML private Label levelslot5;
    @FXML private Label levelslot6;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button buttonslot1;
    @FXML private Button buttonslot2;
    @FXML private Button buttonslot3;
    @FXML private Button buttonslot4;
    @FXML private Button buttonslot5;
    @FXML private Button buttonslot6;
    private boolean update = true;
    private static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image background = new Image("file:src/main/Bilder/Playerbackground.jpg");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        pane.setBackground(bg);
        buttonslot1.setVisible(false);
        buttonslot2.setVisible(false);
        buttonslot3.setVisible(false);
        buttonslot4.setVisible(false);
        buttonslot5.setVisible(false);
        buttonslot6.setVisible(false);
        levelslot1.setVisible(false);
        levelslot2.setVisible(false);
        levelslot3.setVisible(false);
        levelslot4.setVisible(false);
        levelslot5.setVisible(false);
        levelslot6.setVisible(false);
        for (int i = 0; i < SelectPokemonController.getPlayer().getPokemonliste().size(); i++)
        {
            ImageView slot = null;
            Button button = null;
            Label level = null;
            switch (i)
            {
                case 0:
                    slot = slot1;
                    button = buttonslot1;
                    level = levelslot1;
                    break;
                case 1:
                    slot = slot2;
                    button = buttonslot2;
                    level = levelslot2;
                    break;
                case 2:
                    slot = slot3;
                    button = buttonslot3;
                    level = levelslot3;
                    break;
                case 3:
                    slot = slot4;
                    button = buttonslot4;
                    level = levelslot4;
                    break;
                case 4:
                    slot = slot5;
                    button = buttonslot5;
                    level = levelslot5;
                    break;
                case 5:
                    slot = slot6;
                    button = buttonslot6;
                    level = levelslot6;
                    break;
                default:
                    break;
            }
            if (slot != null)
            {
                if (SelectPokemonController.getPlayer().getPokemonliste().get(i).isShiny())
                {
                    slot.setImage(new Image(SelectPokemonController.getPlayer().getPokemonliste().get(i).getSprites().getFront_shiny()));
                }
                else
                {
                    slot.setImage(new Image(SelectPokemonController.getPlayer().getPokemonliste().get(i).getSprites().getFront_default()));
                }
                level.setText("Lv." + SelectPokemonController.getPlayer().getPokemonliste().get(i).getLevel());
            }
            String pokemonName = SelectPokemonController.getPlayer().getPokemonliste().get(i).getName();
            pokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1).toLowerCase();
            button.setText(pokemonName);
            button.setVisible(true);
            level.setVisible(true);
        }
        playername.setText(SelectPokemonController.getPlayer().getName());
        t.start();
        level.setText(String.valueOf(SelectPokemonController.getPlayer().getLevel()));
        win.setText(String.valueOf(SelectPokemonController.getPlayer().getStatistik().getSiege()));
        lose.setText(String.valueOf(SelectPokemonController.getPlayer().getStatistik().getNiederlagen()));
        updateButton();
        buttonEvents(buttonslot1, 0);
        buttonEvents(buttonslot2, 1);
        buttonEvents(buttonslot3, 2);
        buttonEvents(buttonslot4, 3);
        buttonEvents(buttonslot5, 4);
        buttonEvents(buttonslot6, 5);
        playername.setOnMouseClicked(e ->
        {
            if (e.getButton().equals(MouseButton.PRIMARY))
            {
                if (e.getClickCount() == 2)
                {
                    newName();
                }
            }
        });
    }

    public void buttonEvents(Button button, int i)
    {
        button.setOnMouseClicked(e ->
        {
            if (e.getButton().equals(MouseButton.PRIMARY))
            {
                if (e.getClickCount() == 1)
                {
                    selectSlot(i);
                }
                else if (e.getClickCount() == 2)
                {
                    pokemonLoeschen(i);
                }
            }
        });
    }

    public void pokemonLoeschen(int i)
    {
        if(SelectPokemonController.getPlayer().getPokemonliste().size() > 1)
        {
            if (0 == JOptionPane.showConfirmDialog(null, "Möchten Sie dieses Pokemon Wirklich Löschen?", "Pokemon Löschen", JOptionPane.YES_NO_OPTION))
            {
                System.out.println("Löschen");
                SelectPokemonController.getPlayer().getPokemonliste().remove(SelectPokemonController.getPlayer().getPokemonliste().get(SelectPokemonController.getPlayer().getSelectedIndex()));
                if(SelectPokemonController.getPlayer().getSelectedIndex() >= SelectPokemonController.getPlayer().getPokemonliste().size())
                {
                    SelectPokemonController.getPlayer().setSelectedIndex(SelectPokemonController.getPlayer().getSelectedIndex() - 1);
                }
                reloadMenue();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Du kannst keine weiteren Pokemon Löschen!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void newName()
    {
        String playernametemp;
        StoredDataManagement storedDataManagement = new StoredDataManagement();
        ArrayList<Player> players = storedDataManagement.getSpielerliste();
        do
        {
            playernametemp = JOptionPane.showInputDialog(null,"Gib deinen neuen Namen ein!", "Spielername", JOptionPane.QUESTION_MESSAGE);
            if(playernametemp == null)
            {
                return;
            }
            else
            {
                for (Player player : players)
                {
                    if (player.getName().equals(playernametemp))
                    {
                        playernametemp = "";
                        JOptionPane.showMessageDialog(null, "Dieser Name exsistiert bereits!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
        }while(playernametemp.trim().isEmpty());

        String file = "src/main/Player/" + SelectPokemonController.getPlayer().getName() + ".ser";
        File file1 = new File(file);
        file1.delete();
        SelectPokemonController.getPlayer().setName(playernametemp);
        playername.setText(SelectPokemonController.getPlayer().getName());
        Player p = SelectPokemonController.getPlayer();
        Thread thread = new Thread(() -> storedDataManagement.playerSave(p));
        thread.start();
    }

    public void updateButton()
    {
        buttonslot1.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonslot2.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonslot3.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonslot4.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonslot5.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonslot6.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonslot1.setTextFill(Color.BLACK);
        buttonslot2.setTextFill(Color.BLACK);
        buttonslot3.setTextFill(Color.BLACK);
        buttonslot4.setTextFill(Color.BLACK);
        buttonslot5.setTextFill(Color.BLACK);
        buttonslot6.setTextFill(Color.BLACK);

        switch (SelectPokemonController.getPlayer().getSelectedIndex())
        {
            case 0:
                buttonslot1.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
                buttonslot1.setTextFill(Color.WHITE);
                break;
            case 1:
                buttonslot2.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
                buttonslot2.setTextFill(Color.WHITE);
                break;
            case 2:
                buttonslot3.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
                buttonslot3.setTextFill(Color.WHITE);
                break;
            case 3:
                buttonslot4.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
                buttonslot4.setTextFill(Color.WHITE);
                break;
            case 4:
                buttonslot5.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
                buttonslot5.setTextFill(Color.WHITE);
                break;
            case 5:
                buttonslot6.setBackground(new Background(new BackgroundFill(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
                buttonslot6.setTextFill(Color.WHITE);
                break;
            default:
                break;
        }
    }

    Thread t = new Thread(new Runnable()
    {
        @Override
        public void run()
        {
            while (update)
            {
                Platform.runLater(() -> gametime.setText(String.valueOf(SelectPokemonController.getPlayer().getStatistik().getSpielStunden()) + ":" + String.valueOf(SelectPokemonController.getPlayer().getStatistik().getSpielMinuten()) + ":" + String.valueOf(SelectPokemonController.getPlayer().getStatistik().getSpielSekunden())));
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {
                    break;
                }
            }
        }
    });

    public void toGameField()
    {
        SelectPokemonController.getStage().close();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GameMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            stage = new Stage();
            stage.setTitle("Game Menü");
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
        update = false;
    }

    public void saveData()
    {
        button1.setVisible(false);
        button2.setVisible(false);
        Thread t = new Thread(() ->
        {
            try
            {
                Platform.runLater(() -> SelectPokemonController.getStage().setTitle("Speichern"));
                Thread.sleep(500);
                Platform.runLater(() -> SelectPokemonController.getStage().setTitle("Speichern."));
                Thread.sleep(500);
                Platform.runLater(() -> SelectPokemonController.getStage().setTitle("Speichern.."));
                Thread.sleep(500);
                Platform.runLater(() -> SelectPokemonController.getStage().setTitle("Speichern..."));
                Thread.sleep(500);
                Platform.runLater(() -> SelectPokemonController.getStage().setTitle("Spieler Menü"));
                Thread.sleep(500);
                Platform.runLater(() ->
                {
                    SelectPokemonController.getStage().close();
                    SelectPokemonController.getPlayer().getStatistik().stopTimer();
                    StoredDataManagement storedDataManagement = new StoredDataManagement();
                    storedDataManagement.playerSave(SelectPokemonController.getPlayer());
                    System.out.println("Fenster geschlossen");
                    System.exit(0);
                });
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });
        t.start();
    }

    public void selectSlot(int i)
    {
        SelectPokemonController.getPlayer().setSelectedIndex(i);
        System.out.println(SelectPokemonController.getPlayer().getSelectedIndex());
        updateButton();
    }

    public static Stage getStage()
    {
        return stage;
    }

    public void reloadMenue()
    {
        SelectPokemonController.getStage().close();
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
                StoredDataManagement storedDataManagement = new StoredDataManagement();
                storedDataManagement.playerSave(SelectPokemonController.getPlayer());
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
