import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SelectPokemonController implements Initializable
{
    @FXML private Pane pane;
    @FXML private ImageView img1;
    @FXML private ImageView img2;
    @FXML private ImageView img3;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label labelgen;
    @FXML private Button btnprevious;
    @FXML private Button btnnext;
    private Pokemon p ;
    private static Player player;
    private static int gen = 0;
    private static int page = 1;
    private static Stage stage;
    private int randomnummerfire = (int) (Math.random() * 100);
    private int randomnummerwater = (int) (Math.random() * 100);
    private int randomnummergrass = (int) (Math.random() * 100);
    private final ArrayList<Pokemon> grassList = new ArrayList<>();
    private final ArrayList<Pokemon> fireList = new ArrayList<>();
    private final ArrayList<Pokemon> waterList = new ArrayList<>();
    private final ArrayList<Integer> starterGrassPkm = new ArrayList<>(Arrays.asList(1, 152, 252, 387, 495, 650, 722, 810, 906));
    private final ArrayList<Integer> starterFirePkm = new ArrayList<>(Arrays.asList(4, 155, 255, 390, 498, 653, 725, 813, 909));
    private final ArrayList<Integer> starterWaterPkm = new ArrayList<>(Arrays.asList(7, 158, 258, 393, 501, 656, 728, 816, 912));

    public void grassStarter()
    {
        Gson gson = new Gson();
        for (Integer integer : starterGrassPkm)
        {
            try
            {
                p = new Pokemon();
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + integer);
                String json = stream(url);
                p = gson.fromJson(json, Pokemon.class);
                grassList.add(p);
                p.createMove(p);
            }
            catch (IOException e)
            {
                new RuntimeException(e);
            }
            catch (Exception e)
            {
                new RuntimeException(e);
            }
        }
    }

    public void grassInfos(Integer gen)
    {
        if(randomnummergrass > 90 && randomnummergrass < 100)
        {
            img1.setImage(new Image(grassList.get(gen).getSprites().getFront_shiny()));
            label1.setText(grassList.get(gen).getName() + " ☆");
            label1.setBackground(new Background(new BackgroundFill(Color.SPRINGGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            label1.setStyle("-fx-border-color: black;");
            grassList.get(gen).setShiny(true);
        }
        else
        {
            img1.setImage(new Image(grassList.get(gen).getSprites().getFront_default()));
            label1.setText(grassList.get(gen).getName());
            label1.setBackground(new Background(new BackgroundFill(Color.SPRINGGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            label1.setStyle("-fx-border-color: black;");
            grassList.get(gen).setShiny(false);
        }
    }

    public void fireStarer()
    {
        Gson gson = new Gson();
        for (Integer integer : starterFirePkm)
        {
            try
            {
                p = new Pokemon();
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + integer);
                String json = stream(url);
                p = gson.fromJson(json, Pokemon.class);
                fireList.add(p);
                p.createMove(p);
            }
            catch (IOException e)
            {
                new RuntimeException(e);
            }
            catch (Exception e)
            {
                new RuntimeException(e);
            }
        }
    }

    public void fireInfos(Integer gen)
    {
        if(randomnummerfire > 90 && randomnummerfire < 100)
        {
            img2.setImage(new Image(fireList.get(gen).getSprites().getFront_shiny()));
            label2.setText(fireList.get(gen).getName() + " ☆");
            label2.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(255,36,0), CornerRadii.EMPTY, Insets.EMPTY)));
            label2.setStyle("-fx-border-color: black;");
            fireList.get(gen).setShiny(true);
        }
        else
        {
            img2.setImage(new Image(fireList.get(gen).getSprites().getFront_default()));
            label2.setText(fireList.get(gen).getName());
            label2.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(255,36,0), CornerRadii.EMPTY, Insets.EMPTY)));
            label2.setStyle("-fx-border-color: black;");
            fireList.get(gen).setShiny(false);
        }
    }

    public void waterStarter()
    {
        Gson gson = new Gson();
        for (Integer integer : starterWaterPkm)
        {
            try
            {
                p = new Pokemon();
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + integer);
                String json = stream(url);
                p = gson.fromJson(json, Pokemon.class);
                waterList.add(p);
                p.createMove(p);
            }
            catch (IOException e)
            {
                new RuntimeException(e);
            }
            catch (Exception e)
            {
                new RuntimeException(e);
            }
        }
    }

    public void waterInfos(Integer gen)
    {
        if(randomnummerwater > 90 && randomnummerwater < 100)
        {
            img3.setImage(new Image(waterList.get(gen).getSprites().getFront_shiny()));
            label3.setText(waterList.get(gen).getName() + " ☆");
            label3.setBackground(new Background(new BackgroundFill(Color.TURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
            label3.setStyle("-fx-border-color: black;");
            waterList.get(gen).setShiny(true);
        }
        else
        {
            img3.setImage(new Image(waterList.get(gen).getSprites().getFront_default()));
            label3.setText(waterList.get(gen).getName());
            label3.setBackground(new Background(new BackgroundFill(Color.TURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
            label3.setStyle("-fx-border-color: black;");
            waterList.get(gen).setShiny(false);
        }
    }

    public static String stream(java.net.URL url) throws java.io.IOException
    {
        try (java.io.InputStream input = url.openStream())
        {
            java.io.InputStreamReader isr = new java.io.InputStreamReader(input);
            java.io.BufferedReader reader = new java.io.BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1)
            {
                json.append((char) c);
            }
            return json.toString();
        }
    }

    public void headInfos()
    {
        labelgen.setText("Generation " + (gen + 1));
        labelgen.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image background = new Image("file:src/main/Bilder/SelectBackground.jpg");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        pane.setBackground(bg);
        btnprevious.setVisible(false);

        headInfos();

        //Grasspkm
        grassStarter();
        grassInfos(gen);

        //Firepkm
        fireStarer();
        fireInfos(gen);

        //Waterpkm
        waterStarter();
        waterInfos(gen);
    }

    public void switchSenceForward(javafx.event.ActionEvent actionEvent) throws IOException
    {
        randomnummerfire = (int) (Math.random() * 100);
        randomnummergrass = (int) (Math.random() * 100);
        randomnummerwater = (int) (Math.random() * 100);
        gen++;
        page++;
        headInfos();
        grassInfos(gen);
        fireInfos(gen);
        waterInfos(gen);

        if(page == 1)
        {
            btnprevious.setVisible(false);
        }
        else if(page > 1 && page < 9)
        {
            btnprevious.setVisible(true);
            btnnext.setVisible(true);
        }
        else if(page >= 9)
        {
            btnnext.setVisible(false);
        }
    }

    public void switchSenceBackward(javafx.event.ActionEvent actionEvent) throws IOException
    {
        randomnummerfire = (int) (Math.random() * 100);
        randomnummergrass = (int) (Math.random() * 100);
        randomnummerwater = (int) (Math.random() * 100);
        gen--;
        page--;
        headInfos();
        grassInfos(gen);
        fireInfos(gen);
        waterInfos(gen);

        if(page == 1)
        {
            btnprevious.setVisible(false);
        }
        else if(page > 1 && page < 9)
        {
            btnprevious.setVisible(true);
            btnnext.setVisible(true);
        }
        else if(page >= 9)
        {
            page = 9;
            btnnext.setVisible(false);
        }
    }

    public void nextStage()
    {
        SelectPokemonController.getPlayer().getStatistik().start();
        MainMenuController.getStage().close();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PlayerMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            stage = new Stage();
            stage.setTitle("Spieler Menü");
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/Bilder/GameIcon.png"));
            stage.setOnCloseRequest(e ->
            {
                stage.close();
                player.getStatistik().stopTimer();
                StoredDataManagement storedDataManagement = new StoredDataManagement();
                storedDataManagement.playerSave(player);
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

    public void loadData(Player player)
    {
        this.player = player;
        StoredDataManagement storedDataManagement = new StoredDataManagement(this.player);
        nextStage();
    }

    public void selectPokemonGrass()
    {
        player = new Player(grassList.get(gen), MainMenuController.getPlayername());
        System.out.println(player.getPokemonliste().get(0).getMove1().getPower());
        nextStage();
    }

    public void selectPokemonWater()
    {
        player = new Player(waterList.get(gen), MainMenuController.getPlayername());
        nextStage();
    }

    public void selectPokemonFire()
    {
        player = new Player(fireList.get(gen), MainMenuController.getPlayername());
        nextStage();
    }

    public static Player getPlayer()
    {
        return player;
    }

    public static Stage getStage()
    {
        return stage;
    }
}
