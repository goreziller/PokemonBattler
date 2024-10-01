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
            for (int i = 0; i < dragonTypes.size(); i++)
            {
                if(dragonTypes.get(i).getType1().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(0).getType().getName()) &&
                dragonTypes.get(i).getType2().equals(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getTypes().get(1).getType().getName()))
                {
                    HBox hBox = new HBox();
                    int widthSize = 20;
                    int heightSize = 20;

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
            enemyname.setText(enemyName);
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
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("fire"))
        {
            Image img = new Image("file:src/main/Bilder/FireType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("fire"))
        {
            Image img = new Image("file:src/main/Bilder/FireType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("fire"))
        {
            Image img = new Image("file:src/main/Bilder/FireType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("fire"))
        {
            Image img = new Image("file:src/main/Bilder/FireType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move4.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move4.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
            move4.setBackground(new Background(new BackgroundFill(Color.rgb(238, 129, 48), CornerRadii.EMPTY, Insets.EMPTY)));
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
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getType().getName().equals("electic"))
        {
            Image img = new Image("file:src/main/Bilder/ElecticType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move1.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move1.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move1.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getType().getName().equals("electic"))
        {
            Image img = new Image("file:src/main/Bilder/ElecticType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move2.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move2.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move2.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getType().getName().equals("electic"))
        {
            Image img = new Image("file:src/main/Bilder/ElecticType.png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            move3.setGraphic(view);
            String moveName = SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName();
            moveName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase();
            move3.setText(moveName + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move3.setBackground(new Background(new BackgroundFill(Color.rgb(247, 208, 44), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getType().getName().equals("electic"))
        {
            Image img = new Image("file:src/main/Bilder/ElecticType.png");
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
                enemyDamage = (p.getMove1().getPower() / 4);
            case 2:
                enemyDamage = (p.getMove2().getPower() / 4);
            case 3:
                enemyDamage = (p.getMove3().getPower() / 4);
            case 4:
                enemyDamage = (p.getMove4().getPower() / 4);
        }
    }

    public void chooseAttackOne()
    {
        //Turn Player
        playerDamage = (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4);
        System.out.println(playerDamage);
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
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            returnToMenue();
        }
    }

    public void chooseAttackTwo()
    {
        //Turn Player
        playerDamage = (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4);
        System.out.println(playerDamage);
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
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            returnToMenue();
        }
    }

    public void chooseAttackThree()
    {
        //Turn Player
        playerDamage = (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4);
        System.out.println(playerDamage);
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
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            returnToMenue();
        }
    }

    public void chooseAttackFour()
    {
        //Turn Player
        playerDamage = (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4);
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
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newStage();
            returnToMenue();
        }
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