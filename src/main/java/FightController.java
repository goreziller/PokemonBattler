import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
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
        info.setText("What will\n" + SelectPokemonController.getPlayer().getPokemonliste().get(slot).getName() + " do?");
        showFightInfo();
        pokemonname.setText(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getName());
        pokemonlevel.setText("Lv." + SelectPokemonController.getPlayer().getPokemonliste().get(slot).getLevel());
        hp = (int) Math.round(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getStats().get(0).getBase_stat() * (1 + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getLevel() * 0.02)));
        currenthp = hp;
        showhp.setText(currenthp + " / " +  hp);

        createRandomEnemy();
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
            enemyname.setText(p.getName());
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
            move1.setText(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getName() +  " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove1().getPower() / 4));
            move2.setText(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getName() + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove2().getPower() / 4));
            move3.setText(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getName() + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove3().getPower() / 4));
            move4.setText(SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getName() + " " + (SelectPokemonController.getPlayer().getPokemonliste().get(slot).getMove4().getPower() / 4));
        }
        catch (Exception e)
        {
            returnToMenue();
            e.printStackTrace();
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
            SelectPokemonController.getPlayer().newLevel();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newLevel();
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
            SelectPokemonController.getPlayer().newLevel();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newLevel();
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
            SelectPokemonController.getPlayer().newLevel();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newLevel();
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
            SelectPokemonController.getPlayer().newLevel();
            SelectPokemonController.getPlayer().getPokemonliste().get(slot).newLevel();
            returnToMenue();
        }
        else if(currenthp <= 0)
        {
            SelectPokemonController.getPlayer().getStatistik().neueNiederlage();
            SelectPokemonController.getPlayer().newLevel();
            returnToMenue();
        }
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