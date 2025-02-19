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
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FightController implements Initializable
{
    @FXML private AnchorPane pane;
    @FXML private ImageView pokemonimage;
    @FXML private ImageView enemyimage;
    @FXML private Button move1;
    @FXML private Button move2;
    @FXML private Button move3;
    @FXML private Button move4;
    @FXML private Label pokemonname;
    @FXML private Label pokemonlevel;
    @FXML private Label showhp;
    @FXML private Label info;
    @FXML private Label enemyname;
    @FXML private Label enemylevel;
    @FXML private Label hpenemy;
    private int hp;
    private int currenthp;
    private int enemyhp;
    private int currentenemyhp;
    private int slot;
    private int playerDamage;
    private int enemyDamage;
    private int randomAttack;
    private int widthSize;
    private int heightSize;
    private Pokemon p;
    private ArrayList<Types> fireTypes;
    private ArrayList<Types> waterTypes;
    private ArrayList<Types> grassTypes;
    private ArrayList<Types> normalTypes;
    private ArrayList<Types> steelTypes;
    private ArrayList<Types> fairyTypes;
    private ArrayList<Types> bugTypes;
    private ArrayList<Types> darkTypes;
    private ArrayList<Types> electricTypes;
    private ArrayList<Types> fightingTypes;
    private ArrayList<Types> flyingTypes;
    private ArrayList<Types> ghostTypes;
    private ArrayList<Types> groundTypes;
    private ArrayList<Types> iceTypes;
    private ArrayList<Types> poisonTypes;
    private ArrayList<Types> psychicTypes;
    private ArrayList<Types> rockTypes;
    private ArrayList<Types> dragonTypes;
    private String enemyType1;
    private String enemyType2;
    private String enemyAttackType;
    private ArrayList<PokemonTypeEffectivity> effectivities;
    private double attackDamage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image background = new Image("file:src/main/Bilder/FightBackground.png");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        pane.setBackground(bg);
        slot = SelectPokemonController.getPlayer().getSelectedIndex();
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).isShiny())
        {
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getSprites().getBack_shiny() == null)
            {
                pokemonimage.setImage(new Image(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getSprites().getFront_shiny()));
            }
            else
            {
                pokemonimage.setImage(new Image(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getSprites().getBack_shiny()));
            }
        }
        else
        {
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getSprites().getBack_default() == null)
            {
                pokemonimage.setImage(new Image(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getSprites().getFront_default()));
            }
            else
            {
                pokemonimage.setImage(new Image(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getSprites().getBack_default()));
            }
        }
        String pokemonName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getName();
        pokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1).toLowerCase();
        info.setText("What will\n" + pokemonName + " do?");
        showFightInfo();

        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().size() == 2)
        {
            loadDatabaseInfos();

            //Normal Types
            for(int i = 0; i < normalTypes.size(); i++)
            {
                if(normalTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                normalTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + normalTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + normalTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Fire Types
            for(int i = 0; i < fireTypes.size(); i++)
            {
                if(fireTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        fireTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + fireTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + fireTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Water Types
            for(int i = 0; i < waterTypes.size(); i++)
            {
                if(waterTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        waterTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + waterTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + waterTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Grass Types
            for(int i = 0; i < grassTypes.size(); i++)
            {
                if(grassTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        grassTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + grassTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + grassTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Dragon Types
            for (int i = 0; i < dragonTypes.size(); i++)
            {
                if(dragonTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                dragonTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + dragonTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + dragonTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Bug Types
            for(int i = 0; i < bugTypes.size(); i++)
            {
                if(bugTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        bugTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + bugTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + bugTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Steel Types
            for(int i = 0; i < steelTypes.size(); i++)
            {
                if(steelTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        steelTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + steelTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + steelTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Fairy Types
            for(int i = 0; i < fairyTypes.size(); i++)
            {
                if(fairyTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        fairyTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + fairyTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + fairyTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Dark Types
            for(int i = 0; i < darkTypes.size(); i++)
            {
                if(darkTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        darkTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + darkTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + darkTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Electric Types
            for(int i = 0; i < electricTypes.size(); i++)
            {
                if(electricTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        electricTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + electricTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + electricTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Flying Types
            for(int i = 0; i < flyingTypes.size(); i++)
            {
                if(flyingTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        flyingTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + flyingTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + flyingTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Ghost Types
            for(int i = 0; i < ghostTypes.size(); i++)
            {
                if(ghostTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        ghostTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + ghostTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + ghostTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Fighting Types
            for(int i = 0; i < fightingTypes.size(); i++)
            {
                if(fightingTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        fightingTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + fightingTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + fightingTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Ground Types
            for(int i = 0; i < groundTypes.size(); i++)
            {
                if(groundTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        groundTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + groundTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + groundTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Ice Types
            for(int i = 0; i < iceTypes.size(); i++)
            {
                if(iceTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        iceTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + iceTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + iceTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Poison Types
            for(int i = 0; i < poisonTypes.size(); i++)
            {
                if(poisonTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        poisonTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + poisonTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + poisonTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Rock Types
            for(int i = 0; i < rockTypes.size(); i++)
            {
                if(rockTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        rockTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + rockTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + rockTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }

            //Psychic Types
            for(int i = 0; i < psychicTypes.size(); i++)
            {
                if(psychicTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                        psychicTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    widthSize = 20;
                    heightSize = 20;

                    Image img = new Image("file:src/main/Bilder/" + psychicTypes.get(i).getPng1());
                    ImageView view = new ImageView(img);
                    view.setFitWidth(widthSize);
                    view.setFitHeight(heightSize);

                    Image img1 = new Image("file:src/main/Bilder/" + psychicTypes.get(i).getPng2());
                    ImageView view1 = new ImageView(img1);
                    view1.setFitWidth(widthSize);
                    view1.setFitHeight(heightSize);

                    hBox.getChildren().addAll(view, view1);
                    pokemonname.setGraphic(hBox);
                    pokemonname.setText(pokemonName);
                }
            }
        }
        else
        {
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire"))
            {
                Image img = new Image("file:src/main/Bilder/FireType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);

                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("water"))
            {
                Image img = new Image("file:src/main/Bilder/WaterType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("grass"))
            {
                Image img = new Image("file:src/main/Bilder/GrassType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("bug"))
            {
                Image img = new Image("file:src/main/Bilder/BugType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("dark"))
            {
                Image img = new Image("file:src/main/Bilder/DarkType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("dragon"))
            {
                Image img = new Image("file:src/main/Bilder/DragonType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("electric"))
            {
                Image img = new Image("file:src/main/Bilder/ElectricType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fairy"))
            {
                Image img = new Image("file:src/main/Bilder/FairyType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fighting"))
            {
                Image img = new Image("file:src/main/Bilder/FightingType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("flying"))
            {
                Image img = new Image("file:src/main/Bilder/FlyingType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("ghost"))
            {
                Image img = new Image("file:src/main/Bilder/GhostType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("ground"))
            {
                Image img = new Image("file:src/main/Bilder/GroundType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("ice"))
            {
                Image img = new Image("file:src/main/Bilder/IceType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("normal"))
            {
                Image img = new Image("file:src/main/Bilder/NormalType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("poison"))
            {
                Image img = new Image("file:src/main/Bilder/PoisonType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("psychic"))
            {
                Image img = new Image("file:src/main/Bilder/PsychicType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("rock"))
            {
                Image img = new Image("file:src/main/Bilder/RockType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("steel"))
            {
                Image img = new Image("file:src/main/Bilder/SteelType.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(20);
                view.setFitHeight(20);
                pokemonname.setGraphic(view);
                pokemonname.setText(pokemonName);
            }
        }

        pokemonlevel.setText("Lv." + SelectPokemonController.getPlayer().getPokemonliste().get(slot).getLevel());
        hp = (int) Math.round(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getStats().get(0).getBase_stat() * (1 + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getLevel() * 0.02)));
        currenthp = hp;
        showhp.setText(currenthp + " / " +  hp);

        createRandomEnemy();
    }

    public void loadDatabaseInfos()
    {
        try(Connection conn = DriverManager.getConnection(DatabaseEnum.PokemonTypesPath.getPath()))
        {
            //Dragon Types
            dragonTypes = new ArrayList<>();
            String dragonSql = "Select Type1, Type2, Png1, Png2 FROM DragonTypes";
            Statement dragonStmt = conn.createStatement();
            ResultSet dragonRs = dragonStmt.executeQuery(dragonSql);

            while (dragonRs.next())
            {
                Types newdragontypes = new Types(dragonRs.getString("Type1"), dragonRs.getString("Type2"), dragonRs.getString("Png1"), dragonRs.getString("Png2"));
                dragonTypes.add(newdragontypes);
            }
            
            //Fire Types
            fireTypes = new ArrayList<>();
            String fireSql = "Select Type1, Type2, Png1, Png2 FROM FireTypes";
            Statement fireStmt = conn.createStatement();
            ResultSet fireRs = fireStmt.executeQuery(fireSql);

            while (fireRs.next())
            {
                Types newfiretypes = new Types(fireRs.getString("Type1"), fireRs.getString("Type2"), fireRs.getString("Png1"), fireRs.getString("Png2"));
                fireTypes.add(newfiretypes);
            }

            //Water Types
            waterTypes = new ArrayList<>();
            String waterSql = "Select Type1, Type2, Png1, Png2 FROM WaterTypes";
            Statement waterStmt = conn.createStatement();
            ResultSet waterRs = waterStmt.executeQuery(waterSql);

            while (waterRs.next())
            {
                Types newwatertypes = new Types(waterRs.getString("Type1"), waterRs.getString("Type2"), waterRs.getString("Png1"), waterRs.getString("Png2"));
                waterTypes.add(newwatertypes);
            }

            //Grass Types
            grassTypes = new ArrayList<>();
            String grassSql = "Select Type1, Type2, Png1, Png2 FROM GrassTypes";
            Statement grassStmt = conn.createStatement();
            ResultSet grassRs = grassStmt.executeQuery(grassSql);

            while (grassRs.next())
            {
                Types newgrasstypes = new Types(grassRs.getString("Type1"), grassRs.getString("Type2"), grassRs.getString("Png1"), grassRs.getString("Png2"));
                grassTypes.add(newgrasstypes);
            }

            //Normal Types
            normalTypes = new ArrayList<>();
            String normalSql = "Select Type1, Type2, Png1, Png2 FROM NormalTypes";
            Statement normalStmt = conn.createStatement();
            ResultSet normalRs = normalStmt.executeQuery(normalSql);

            while (normalRs.next())
            {
                Types newnormaltypes = new Types(normalRs.getString("Type1"), normalRs.getString("Type2"), normalRs.getString("Png1"), normalRs.getString("Png2"));
                normalTypes.add(newnormaltypes);
            }

            //Bug Types
            bugTypes = new ArrayList<>();
            String bugSql = "Select Type1, Type2, Png1, Png2 FROM BugTypes";
            Statement bugStmt = conn.createStatement();
            ResultSet bugRs = bugStmt.executeQuery(bugSql);

            while (bugRs.next())
            {
                Types newbugtypes = new Types(bugRs.getString("Type1"), bugRs.getString("Type2"), bugRs.getString("Png1"), bugRs.getString("Png2"));
                bugTypes.add(newbugtypes);
            }

            //Steel Types
            steelTypes = new ArrayList<>();
            String steelSql = "Select Type1, Type2, Png1, Png2 FROM SteelTypes";
            Statement steelStmt = conn.createStatement();
            ResultSet steelRs = steelStmt.executeQuery(steelSql);

            while (steelRs.next())
            {
                Types newsteeltypes = new Types(steelRs.getString("Type1"), steelRs.getString("Type2"), steelRs.getString("Png1"), steelRs.getString("Png2"));
                steelTypes.add(newsteeltypes);
            }

            //Fairy Types
            fairyTypes = new ArrayList<>();
            String fairySql = "Select Type1, Type2, Png1, Png2 FROM FairyTypes";
            Statement fairyStmt = conn.createStatement();
            ResultSet fairyRs = fairyStmt.executeQuery(fairySql);

            while (fairyRs.next())
            {
                Types newfairytypes = new Types(fairyRs.getString("Type1"), fairyRs.getString("Type2"), fairyRs.getString("Png1"), fairyRs.getString("Png2"));
                fairyTypes.add(newfairytypes);
            }

            //Dark Types
            darkTypes = new ArrayList<>();
            String darkSql = "Select Type1, Type2, Png1, Png2 FROM DarkTypes";
            Statement darkStmt = conn.createStatement();
            ResultSet darkRs = darkStmt.executeQuery(darkSql);

            while (darkRs.next())
            {
                Types newdarktypes = new Types(darkRs.getString("Type1"), darkRs.getString("Type2"), darkRs.getString("Png1"), darkRs.getString("Png2"));
                darkTypes.add(newdarktypes);
            }

            //Electric Types
            electricTypes = new ArrayList<>();
            String electricSql = "Select Type1, Type2, Png1, Png2 FROM ElectricTypes";
            Statement electricStmt = conn.createStatement();
            ResultSet electricRs = electricStmt.executeQuery(electricSql);

            while (electricRs.next())
            {
                Types newelectrictypes = new Types(electricRs.getString("Type1"), electricRs.getString("Type2"), electricRs.getString("Png1"), electricRs.getString("Png2"));
                electricTypes.add(newelectrictypes);
            }

            //Fighting Types
            fightingTypes = new ArrayList<>();
            String fightingSql = "Select Type1, Type2, Png1, Png2 FROM FightingTypes";
            Statement fightingStmt = conn.createStatement();
            ResultSet fightingRs = fightingStmt.executeQuery(fightingSql);

            while (fightingRs.next())
            {
                Types newfightingtypes = new Types(fightingRs.getString("Type1"), fightingRs.getString("Type2"), fightingRs.getString("Png1"), fightingRs.getString("Png2"));
                fightingTypes.add(newfightingtypes);
            }

            //Flying Types
            flyingTypes = new ArrayList<>();
            String flyingSql = "Select Type1, Type2, Png1, Png2 FROM FlyingTypes";
            Statement flyingStmt = conn.createStatement();
            ResultSet flyingRs = flyingStmt.executeQuery(flyingSql);

            while (flyingRs.next())
            {
                Types newflyingtypes = new Types(flyingRs.getString("Type1"), flyingRs.getString("Type2"), flyingRs.getString("Png1"), flyingRs.getString("Png2"));
                flyingTypes.add(newflyingtypes);
            }

            //Ghost Types
            ghostTypes = new ArrayList<>();
            String ghostSql = "Select Type1, Type2, Png1, Png2 FROM GhostTypes";
            Statement ghostStmt = conn.createStatement();
            ResultSet ghostRs = ghostStmt.executeQuery(ghostSql);

            while (ghostRs.next())
            {
                Types newghosttypes = new Types(ghostRs.getString("Type1"), ghostRs.getString("Type2"), ghostRs.getString("Png1"), ghostRs.getString("Png2"));
                ghostTypes.add(newghosttypes);
            }

            //Ground Types
            groundTypes = new ArrayList<>();
            String groundSql = "Select Type1, Type2, Png1, Png2 FROM GroundTypes";
            Statement groundStmt = conn.createStatement();
            ResultSet groundRs = groundStmt.executeQuery(groundSql);

            while (groundRs.next())
            {
                Types newgroundtypes = new Types(groundRs.getString("Type1"), groundRs.getString("Type2"), groundRs.getString("Png1"), groundRs.getString("Png2"));
                groundTypes.add(newgroundtypes);
            }

            //Ice Types
            iceTypes = new ArrayList<>();
            String iceSql = "Select Type1, Type2, Png1, Png2 FROM IceTypes";
            Statement iceStmt = conn.createStatement();
            ResultSet iceRs = iceStmt.executeQuery(iceSql);

            while (iceRs.next())
            {
                Types newicetypes = new Types(iceRs.getString("Type1"), iceRs.getString("Type2"), iceRs.getString("Png1"), iceRs.getString("Png2"));
                iceTypes.add(newicetypes);
            }

            //Poison Types
            poisonTypes = new ArrayList<>();
            String poisonSql = "Select Type1, Type2, Png1, Png2 FROM PoisonTypes";
            Statement poisonStmt = conn.createStatement();
            ResultSet poisonRs = poisonStmt.executeQuery(poisonSql);

            while (poisonRs.next())
            {
                Types newpoisontypes = new Types(poisonRs.getString("Type1"), poisonRs.getString("Type2"), poisonRs.getString("Png1"), poisonRs.getString("Png2"));
                poisonTypes.add(newpoisontypes);
            }

            //Psychic Types
            psychicTypes = new ArrayList<>();
            String psychicSql = "Select Type1, Type2, Png1, Png2 FROM PsychicTypes";
            Statement psychicStmt = conn.createStatement();
            ResultSet psychicRs = psychicStmt.executeQuery(psychicSql);

            while (psychicRs.next())
            {
                Types newpoisontypes = new Types(psychicRs.getString("Type1"), psychicRs.getString("Type2"), psychicRs.getString("Png1"), psychicRs.getString("Png2"));
                psychicTypes.add(newpoisontypes);
            }

            //Rock Types
            rockTypes = new ArrayList<>();
            String rockSql = "Select Type1, Type2, Png1, Png2 FROM RockTypes";
            Statement rockStmt = conn.createStatement();
            ResultSet rockRs = rockStmt.executeQuery(rockSql);

            while (rockRs.next())
            {
                Types newrocktypes = new Types(rockRs.getString("Type1"), rockRs.getString("Type2"), rockRs.getString("Png1"), rockRs.getString("Png2"));
                rockTypes.add(newrocktypes);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void createRandomEnemy()
    {
        try
        {
            RandomizedPokemon randomizedPokemon = new RandomizedPokemon();
            p = randomizedPokemon.getRandomPokemon();
            enemyhp = (int) Math.round(p.getStats().get(0).getBase_stat() * (1 + (p.getLevel() * 0.02)));
            currentenemyhp = enemyhp;
            enemyimage.setImage(new Image(p.getSprites().getFront_default()));
            String enemyName = p.getName();
            enemyName = enemyName.substring(0, 1).toUpperCase() + enemyName.substring(1).toLowerCase();
            if(p.getTypes().size() == 2)
            {
                enemyType1 = p.getTypes().get(0).getType().getName();
                enemyType2 = p.getTypes().get(1).getType().getName();

                loadDatabaseInfos();

                //Normal Types
                for(int i = 0; i < normalTypes.size(); i++)
                {
                    if(normalTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            normalTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + normalTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + normalTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Fire Types
                for(int i = 0; i < fireTypes.size(); i++)
                {
                    if(fireTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            fireTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + fireTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + fireTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Water Types
                for(int i = 0; i < waterTypes.size(); i++)
                {
                    if(waterTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            waterTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + waterTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + waterTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Grass Types
                for(int i = 0; i < grassTypes.size(); i++)
                {
                    if(grassTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            grassTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + grassTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + grassTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Dragon Types
                for (int i = 0; i < dragonTypes.size(); i++)
                {
                    if(dragonTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            dragonTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + dragonTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + dragonTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Bug Types
                for(int i = 0; i < bugTypes.size(); i++)
                {
                    if(bugTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            bugTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + bugTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + bugTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Steel Types
                for(int i = 0; i < steelTypes.size(); i++)
                {
                    if(steelTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            steelTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + steelTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + steelTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Fairy Types
                for(int i = 0; i < fairyTypes.size(); i++)
                {
                    if(fairyTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            fairyTypes.get(i).getType2().equals(p.getTypes().get(0).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + fairyTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + fairyTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Dark Types
                for(int i = 0; i < darkTypes.size(); i++)
                {
                    if(darkTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            darkTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + darkTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + darkTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Electric Types
                for(int i = 0; i < electricTypes.size(); i++)
                {
                    if(electricTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            electricTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + electricTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + electricTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Flying Types
                for(int i = 0; i < flyingTypes.size(); i++)
                {
                    if(flyingTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            flyingTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + flyingTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + flyingTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Ghost Types
                for(int i = 0; i < ghostTypes.size(); i++)
                {
                    if(ghostTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            ghostTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + ghostTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + ghostTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Fighting Types
                for(int i = 0; i < fightingTypes.size(); i++)
                {
                    if(fightingTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            fightingTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + fightingTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + fightingTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Ground Types
                for(int i = 0; i < groundTypes.size(); i++)
                {
                    if(groundTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            groundTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + groundTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + groundTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Ice Types
                for(int i = 0; i < iceTypes.size(); i++)
                {
                    if(iceTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            iceTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + iceTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + iceTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Poison Types
                for(int i = 0; i < poisonTypes.size(); i++)
                {
                    if(poisonTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            poisonTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + poisonTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + poisonTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Rock Types
                for(int i = 0; i < rockTypes.size(); i++)
                {
                    if(rockTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            rockTypes.get(i).getType2().equals(p.getTypes().get(1).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + rockTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + rockTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }

                //Psychic Types
                for(int i = 0; i < psychicTypes.size(); i++)
                {
                    if(psychicTypes.get(i).getType1().equals(p.getTypes().get(0).getType().getName()) &&
                            psychicTypes.get(i).getType2().equals(p.getTypes().get(0).getType().getName()))
                    {
                        HBox hBox = new HBox();
                        widthSize = 20;
                        heightSize = 20;

                        Image img = new Image("file:src/main/Bilder/" + psychicTypes.get(i).getPng1());
                        ImageView view = new ImageView(img);
                        view.setFitWidth(widthSize);
                        view.setFitHeight(heightSize);

                        Image img1 = new Image("file:src/main/Bilder/" + psychicTypes.get(i).getPng2());
                        ImageView view1 = new ImageView(img1);
                        view1.setFitWidth(widthSize);
                        view1.setFitHeight(heightSize);

                        hBox.getChildren().addAll(view, view1);
                        enemyname.setGraphic(hBox);
                        enemyname.setText(enemyName);
                    }
                }
            }
            else
            {
                enemyType1 = p.getTypes().get(0).getType().getName();

                if(p.getTypes().get(0).getType().getName().equals("fire"))
                {
                    Image img = new Image("file:src/main/Bilder/FireType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);

                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("water"))
                {
                    Image img = new Image("file:src/main/Bilder/WaterType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("grass"))
                {
                    Image img = new Image("file:src/main/Bilder/GrassType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("bug"))
                {
                    Image img = new Image("file:src/main/Bilder/BugType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("dark"))
                {
                    Image img = new Image("file:src/main/Bilder/DarkType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("dragon"))
                {
                    Image img = new Image("file:src/main/Bilder/DragonType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("electric"))
                {
                    Image img = new Image("file:src/main/Bilder/ElectricType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("fairy"))
                {
                    Image img = new Image("file:src/main/Bilder/FairyType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("fighting"))
                {
                    Image img = new Image("file:src/main/Bilder/FightingType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("flying"))
                {
                    Image img = new Image("file:src/main/Bilder/FlyingType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("ghost"))
                {
                    Image img = new Image("file:src/main/Bilder/GhostType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("ground"))
                {
                    Image img = new Image("file:src/main/Bilder/GroundType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("ice"))
                {
                    Image img = new Image("file:src/main/Bilder/IceType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("normal"))
                {
                    Image img = new Image("file:src/main/Bilder/NormalType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("poison"))
                {
                    Image img = new Image("file:src/main/Bilder/PoisonType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("psychic"))
                {
                    Image img = new Image("file:src/main/Bilder/PsychicType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("rock"))
                {
                    Image img = new Image("file:src/main/Bilder/RockType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
                if(p.getTypes().get(0).getType().getName().equals("steel"))
                {
                    Image img = new Image("file:src/main/Bilder/SteelType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    enemyname.setGraphic(view);
                    enemyname.setText(enemyName);
                }
            }

            //enemyname.setText(enemyName);
            enemylevel.setText("Lv." + p.getLevel());
            hpenemy.setText(currentenemyhp + " / " +  enemyhp);
        }
        catch (Exception e)
        {
            new RuntimeException(e);
        }
    }

    public void showFightInfo()
    {
        for(int i = 0; (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1() == null || SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2() == null ||
                SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3() == null || SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4() == null) && i < 10; i++)
        {
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).createMove(SelectPokemonController.getPlayer().getPokemonliste().get(slot));
        }
        try
        {
            typespecificButton();
        }
        catch (Exception e)
        {
            returnToMenue();
            e.printStackTrace();
        }
    }

    public void typespecificButton()
    {
        //Fire Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().size() == 2)
        {
            if((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fire")) ||
                    (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fire")))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move1.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() * 1.25) / 4));
                move1.setText(moveName + " " + attackDamage);
                move1.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else if((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("fire")) ||
                    (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("fire")))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move2.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() * 1.25) / 4));
                move2.setText(moveName + " " + attackDamage);
                move2.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else if((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("fire")) ||
                    (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("fire")))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move3.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() * 1.25) / 4));
                move3.setText(moveName + " " + attackDamage);
                move3.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else if((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("fire")) ||
                    (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("fire")))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move4.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() * 1.25) / 4));
                move4.setText(moveName + " " + attackDamage);
                move4.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else
            {
                if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fire"))
                {
                    Image img = new Image("file:src/main/Bilder/FireType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    move1.setGraphic(view);
                    String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
                    moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                    attackDamage = (double) SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4;
                    move1.setText(moveName + " " + attackDamage);
                    move1.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
        else
        {
            if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fire"))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move1.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() * 1.25) / 4));
                move1.setText(moveName + " " + attackDamage);
                move1.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("fire"))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move2.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() * 1.25) / 4));
                move2.setText(moveName + " " + attackDamage);
                move2.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("fire"))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move3.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() * 1.25) / 4));
                move3.setText(moveName + " " + attackDamage);
                move3.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals("fire") && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("fire"))
            {
                HBox hBox = new HBox();
                widthSize = 20;
                heightSize = 20;

                Image img = new Image("file:src/main/Bilder/boostIcon.png");
                ImageView view = new ImageView(img);
                view.setFitWidth(widthSize);
                view.setFitHeight(heightSize);

                Image img1 = new Image("file:src/main/Bilder/FireType.png");
                ImageView view1 = new ImageView(img1);
                view1.setFitWidth(widthSize);
                view1.setFitHeight(heightSize);

                hBox.getChildren().addAll(view, view1);
                move4.setGraphic(hBox);
                String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
                moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                attackDamage = Math.round(((SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() * 1.25) / 4));
                move4.setText(moveName + " " + attackDamage);
                move4.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else
            {
                if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fire"))
                {
                    Image img = new Image("file:src/main/Bilder/FireType.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(20);
                    view.setFitHeight(20);
                    move1.setGraphic(view);
                    String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
                    moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
                    attackDamage = (double) SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4;
                    move1.setText(moveName + " " + attackDamage);
                    move1.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }

        //Water Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("water"))
        {
            Image img = new Image("file:src/main/Bilder/WaterType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(99, 144, 240), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("water"))
        {
            Image img = new Image("file:src/main/Bilder/WaterType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(99, 144, 240), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("water"))
        {
            Image img = new Image("file:src/main/Bilder/WaterType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(99, 144, 240), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("water"))
        {
            Image img = new Image("file:src/main/Bilder/WaterType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(99, 144, 240), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Normal Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("normal"))
        {
            Image img = new Image("file:src/main/Bilder/NormalType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(168, 167, 122), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("normal"))
        {
            Image img = new Image("file:src/main/Bilder/NormalType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(168, 167, 122), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("normal"))
        {
            Image img = new Image("file:src/main/Bilder/NormalType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(168, 167, 122), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("normal"))
        {
            Image img = new Image("file:src/main/Bilder/NormalType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(168, 167, 122), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Bug Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("bug"))
        {
            Image img = new Image("file:src/main/Bilder/BugType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(166, 185, 26), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("bug"))
        {
            Image img = new Image("file:src/main/Bilder/BugType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(166, 185, 26), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("bug"))
        {
            Image img = new Image("file:src/main/Bilder/BugType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(166, 185, 26), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("bug"))
        {
            Image img = new Image("file:src/main/Bilder/BugType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(166, 185, 26), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Dark Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("dark"))
        {
            Image img = new Image("file:src/main/Bilder/DarkType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(112, 87, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("dark"))
        {
            Image img = new Image("file:src/main/Bilder/DarkType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(112, 87, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("dark"))
        {
            Image img = new Image("file:src/main/Bilder/DarkType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(112, 87, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("dark"))
        {
            Image img = new Image("file:src/main/Bilder/DarkType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(112, 87, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Dragon Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("dragon"))
        {
            Image img = new Image("file:src/main/Bilder/DragonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(111, 53, 252), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("dragon"))
        {
            Image img = new Image("file:src/main/Bilder/DragonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(111, 53, 252), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("dragon"))
        {
            Image img = new Image("file:src/main/Bilder/DragonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(111, 53, 252), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("dragon"))
        {
            Image img = new Image("file:src/main/Bilder/DragonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(111, 53, 252), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Electic Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("electric"))
        {
            Image img = new Image("file:src/main/Bilder/ElectricType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("electric"))
        {
            Image img = new Image("file:src/main/Bilder/ElectricType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("electric"))
        {
            Image img = new Image("file:src/main/Bilder/ElectricType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("electric"))
        {
            Image img = new Image("file:src/main/Bilder/ElectricType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //FairyType
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fairy"))
        {
            Image img = new Image("file:src/main/Bilder/FairyType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(214, 133, 173), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("fairy"))
        {
            Image img = new Image("file:src/main/Bilder/FairyType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(214, 133, 173), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("fairy"))
        {
            Image img = new Image("file:src/main/Bilder/FairyType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(214, 133, 173), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("fairy"))
        {
            Image img = new Image("file:src/main/Bilder/FairyType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(214, 133, 173), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //FightingType
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fighting"))
        {
            Image img = new Image("file:src/main/Bilder/FightingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(194, 46, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("fighting"))
        {
            Image img = new Image("file:src/main/Bilder/FightingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(194, 46, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("fighting"))
        {
            Image img = new Image("file:src/main/Bilder/FightingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(194, 46, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("fighting"))
        {
            Image img = new Image("file:src/main/Bilder/FightingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(194, 46, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //FlyingType
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("flying"))
        {
            Image img = new Image("file:src/main/Bilder/FlyingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(169, 143, 243), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("flying"))
        {
            Image img = new Image("file:src/main/Bilder/FlyingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(169, 143, 243), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("flying"))
        {
            Image img = new Image("file:src/main/Bilder/FlyingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(169, 143, 243), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("flying"))
        {
            Image img = new Image("file:src/main/Bilder/FlyingType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(169, 143, 243), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Ghost Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("ghost"))
        {
            Image img = new Image("file:src/main/Bilder/GhostType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(115,87,151), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("ghost"))
        {
            Image img = new Image("file:src/main/Bilder/GhostType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(115,87,151), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("ghost"))
        {
            Image img = new Image("file:src/main/Bilder/GhostType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(115,87,151), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("ghost"))
        {
            Image img = new Image("file:src/main/Bilder/GhostType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(115,87,151), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Grass Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("grass"))
        {
            Image img = new Image("file:src/main/Bilder/GrassType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(122,199,76), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("grass"))
        {
            Image img = new Image("file:src/main/Bilder/GrassType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(122,199,76), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("grass"))
        {
            Image img = new Image("file:src/main/Bilder/GrassType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(122,199,76), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("grass"))
        {
            Image img = new Image("file:src/main/Bilder/GrassType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(122,199,76), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Ground Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("ground"))
        {
            Image img = new Image("file:src/main/Bilder/GroundType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(226,191,101), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("ground"))
        {
            Image img = new Image("file:src/main/Bilder/GroundType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(226,191,101), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("ground"))
        {
            Image img = new Image("file:src/main/Bilder/GroundType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(226,191,101), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("ground"))
        {
            Image img = new Image("file:src/main/Bilder/GroundType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(226,191,101), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Ice Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("ice"))
        {
            Image img = new Image("file:src/main/Bilder/IceType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(150,217,214), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("ice"))
        {
            Image img = new Image("file:src/main/Bilder/IceType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(150,217,214), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("ice"))
        {
            Image img = new Image("file:src/main/Bilder/IceType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(150,217,214), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("ice"))
        {
            Image img = new Image("file:src/main/Bilder/IceType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(150,217,214), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Poison Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("poison"))
        {
            Image img = new Image("file:src/main/Bilder/PoisonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(163,62,161), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("poison"))
        {
            Image img = new Image("file:src/main/Bilder/PoisonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(163,62,161), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("poison"))
        {
            Image img = new Image("file:src/main/Bilder/PoisonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(163,62,161), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("poison"))
        {
            Image img = new Image("file:src/main/Bilder/PoisonType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(163,62,161), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Psychic Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("psychic"))
        {
            Image img = new Image("file:src/main/Bilder/PsychicType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(249,85,135), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("psychic"))
        {
            Image img = new Image("file:src/main/Bilder/PsychicType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(249,85,135), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("psychic"))
        {
            Image img = new Image("file:src/main/Bilder/PsychicType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(249,85,135), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("psychic"))
        {
            Image img = new Image("file:src/main/Bilder/PsychicType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(249,85,135), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Rock Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("rock"))
        {
            Image img = new Image("file:src/main/Bilder/RockType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(182,161,54), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("rock"))
        {
            Image img = new Image("file:src/main/Bilder/RockType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(182,161,54), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("rock"))
        {
            Image img = new Image("file:src/main/Bilder/RockType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(182,161,54), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("rock"))
        {
            Image img = new Image("file:src/main/Bilder/RockType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(182,161,54), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        //Steel Type
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("steel"))
        {
            Image img = new Image("file:src/main/Bilder/SteelType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(183,183,206), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("steel"))
        {
            Image img = new Image("file:src/main/Bilder/SteelType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(183,183,206), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("steel"))
        {
            Image img = new Image("file:src/main/Bilder/SteelType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(183,183,206), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("steel"))
        {
            Image img = new Image("file:src/main/Bilder/SteelType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(183,183,206), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void updatehp()
    {
        hpenemy.setText(currentenemyhp + " / " +  enemyhp);
        showhp.setText(currenthp + " / " +  hp);
    }

    public void selectEnemyAttack()
    {
        randomAttack = (int) (Math.random() * 4);
        switch (randomAttack)
        {
            case 1:
                enemyAttackType = p.getMove1().getType().getName();
                System.out.println(enemyAttackType);
                enemyDamage = typeEffectivityEnemy(enemyAttackType,p.getMove1().getPower() / 4);
            case 2:
                enemyAttackType = p.getMove2().getType().getName();
                System.out.println(enemyAttackType);
                enemyDamage = typeEffectivityEnemy(enemyAttackType,p.getMove2().getPower() / 4);
            case 3:
                enemyAttackType = p.getMove3().getType().getName();
                System.out.println(enemyAttackType);
                enemyDamage = typeEffectivityEnemy(enemyAttackType,p.getMove3().getPower() / 4);
            case 4:
                enemyAttackType = p.getMove4().getType().getName();
                System.out.println(enemyAttackType);
                enemyDamage = typeEffectivityEnemy(enemyAttackType,p.getMove4().getPower() / 4);
        }
    }

    public void chooseAttackOne()
    {
        //Turn Player
        String playerAttackType = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName();
        System.out.println(playerAttackType);
        int playerAttack = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4;
        playerDamage = typeEffectivityPlayer(playerAttackType, playerAttack);
        System.out.println("Playerschaden: " + playerDamage);

        currentenemyhp = currentenemyhp - playerDamage;
        //Turn Enemy
        selectEnemyAttack();
        System.out.println("Gegnerschaden: " + enemyDamage);
        currenthp = currenthp - enemyDamage;

        updatehp();
        if (currentenemyhp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neuerSieg();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            SelectPokemonController.getPlayer().earnCoins();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().loseLife();
            returnToMenue();
        }
    }

    public void chooseAttackTwo()
    {
        //Turn Player
        String playerAttackType = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName();
        System.out.println(playerAttackType);
        int playerAttack = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4;
        playerDamage = typeEffectivityPlayer(playerAttackType, playerAttack);
        System.out.println("Playerschaden: " + playerDamage);

        currentenemyhp = currentenemyhp - playerDamage;
        //Turn Enemy
        selectEnemyAttack();
        System.out.println("Gegnerschaden: " + enemyDamage);
        currenthp = currenthp - enemyDamage;

        updatehp();
        if (currentenemyhp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neuerSieg();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            SelectPokemonController.getPlayer().earnCoins();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().loseLife();
            returnToMenue();
        }
    }

    public void chooseAttackThree()
    {
        //Turn Player
        String playerAttackType = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName();
        System.out.println(playerAttackType);
        int playerAttack = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4;
        playerDamage = typeEffectivityPlayer(playerAttackType, playerAttack);
        System.out.println("Playerschaden: " + playerDamage);

        currentenemyhp = currentenemyhp - playerDamage;
        //Turn Enemy
        selectEnemyAttack();
        System.out.println("Gegnerschaden: " + enemyDamage);
        currenthp = currenthp - enemyDamage;

        updatehp();
        if (currentenemyhp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neuerSieg();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            SelectPokemonController.getPlayer().earnCoins();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().loseLife();
            returnToMenue();
        }
    }

    public void chooseAttackFour()
    {
        //Turn Player
        String playerAttackType = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName();
        System.out.println(playerAttackType);
        int playerAttack = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4;
        playerDamage = typeEffectivityPlayer(playerAttackType, playerAttack);
        System.out.println("Playerschaden: " + playerDamage);

        currentenemyhp = currentenemyhp - playerDamage;
        //Turn Enemy
        selectEnemyAttack();
        System.out.println("Gegnerschaden: " + enemyDamage);
        currenthp = currenthp - enemyDamage;

        updatehp();
        if (currentenemyhp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neuerSieg();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            SelectPokemonController.getPlayer().earnCoins();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().loseLife();
            returnToMenue();
        }
    }

    public void loadEffectivityDatabase()
    {
        try(Connection conn = DriverManager.getConnection(DatabaseEnum.PokemonTypeEffectivityPath.getPath()))
        {
            effectivities = new ArrayList<>();
            String Sql = "Select Type1, Type2, twice, half, zero FROM PokemonEffectivity";
            Statement Stmt = conn.createStatement();
            ResultSet Rs = Stmt.executeQuery(Sql);

            while (Rs.next())
            {
                PokemonTypeEffectivity effectivity = new PokemonTypeEffectivity(Rs.getString("Type1"), Rs.getString("Type2"), Rs.getBoolean("twice"), Rs.getBoolean("half"), Rs.getBoolean("zero"));
                effectivities.add(effectivity);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public int typeEffectivityPlayer(String attackType, int attackDamage)
    {
        loadEffectivityDatabase();
        if(enemyType2 == null)
        {
            for(int i = 0; i < effectivities.size(); i++)
            {
                if(attackType.equals(effectivities.get(i).getType1()) && enemyType1.equals(effectivities.get(i).getType2()))
                {
                    if(effectivities.get(i).isTwice())
                    {
                        System.out.println("Player: Sehr effektiv");
                        return attackDamage * 2;
                    }
                    else if(effectivities.get(i).isHalf())
                    {
                        System.out.println("Player: Nicht sehr effektiv");
                        return attackDamage / 2;
                    }
                    else
                    {
                        System.out.println("Player: Hat keine Wirkung");
                        return 0;
                    }
                }
            }
        }
        else
        {
            for(int i = 0; i < effectivities.size(); i++)
            {
                if(attackType.equals(effectivities.get(i).getType1()) && ( enemyType1.equals(effectivities.get(i).getType2()) || enemyType2.equals(effectivities.get(i).getType2()) ))
                {
                    if(effectivities.get(i).isTwice())
                    {
                        System.out.println("Player: Sehr effektiv");
                        return attackDamage * 2;
                    }
                    else if(effectivities.get(i).isHalf())
                    {
                        System.out.println("Player: Nicht sehr effektiv");
                        return attackDamage / 2;
                    }
                    else
                    {
                        System.out.println("Player: Hat keine Wirkung");
                        return 0;
                    }
                }
            }
        }
        return attackDamage;
    }

    public int typeEffectivityEnemy(String attackType, int attackDamage)
    {
        loadEffectivityDatabase();
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().size() != 2)
        {
            for(int i = 0; i < effectivities.size(); i++)
            {
                if(attackType.equals(effectivities.get(i).getType1()) && SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals(effectivities.get(i).getType1()))
                {
                    if(effectivities.get(i).isTwice())
                    {
                        System.out.println("Enemy: Sehr effektiv");
                        return attackDamage * 2;
                    }
                    else if(effectivities.get(i).isHalf())
                    {
                        System.out.println("Enemy: Nicht sehr effektiv");
                        return attackDamage / 2;
                    }
                    else
                    {
                        System.out.println("Enemy: Hat keine Wirkung");
                        return 0;
                    }
                }
            }
        }
        else
        {
            for(int i = 0; i < effectivities.size(); i++)
            {
                if(attackType.equals(effectivities.get(i).getType1()) && ( SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName().equals(effectivities.get(i).getType2()) || SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName().equals(effectivities.get(i).getType2()) ))
                {
                    if(effectivities.get(i).isTwice())
                    {
                        System.out.println("Enemy: Sehr effektiv");
                        return attackDamage * 2;
                    }
                    else if(effectivities.get(i).isHalf())
                    {
                        System.out.println("Enemy: Nicht sehr effektiv");
                        return attackDamage / 2;
                    }
                    else
                    {
                        System.out.println("Enemy: Hat keine Wirkung");
                        return 0;
                    }
                }
            }
        }
        return attackDamage;
    }

    public void returnToMenue()
    {
        GameController.getNewstage().close();
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