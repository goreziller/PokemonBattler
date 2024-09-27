import com.google.gson.Gson;
import java.io.IOException;

public class RandomizedPokemon
{
    private int size;
    private int randomPokemonNumber;
    public Pokemon getRandomPokemon()
    {
        TemperaturRandomizer temperaturRandomizer = new TemperaturRandomizer();
        int i = temperaturRandomizer.prozentualleTemperaturZahl(18);

        LoadPokeindex loadPokeindex = new LoadPokeindex();
        loadPokeindex.updatePokeindex();

        Pokemon p = new Pokemon();
        switch (i)
        {
            //Eispokemon
            case 1:
                size = loadPokeindex.getIceList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getIceList().get(randomPokemonNumber));
                System.out.println("Eis" + "|Nummer: " + loadPokeindex.getIceList().get(randomPokemonNumber));
                break;
            //Wasserpokemon
            case 2:
                size = loadPokeindex.getWaterList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getWaterList().get(randomPokemonNumber));
                System.out.println("Wasser" + "|Nummer: " + loadPokeindex.getWaterList().get(randomPokemonNumber));
                break;
            //Geistpokemon
            case 3:
                size = loadPokeindex.getGhostList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getGhostList().get(randomPokemonNumber));
                System.out.println("Geist" + "|Nummer: " + loadPokeindex.getGhostList().get(randomPokemonNumber));
                break;
            //Unlichtpokemon
            case 4:
                size = loadPokeindex.getDarkList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getDarkList().get(randomPokemonNumber));
                System.out.println("Unicht" + "|Nummer: " + loadPokeindex.getDarkList().get(randomPokemonNumber));
                break;
            //Bodenpokemon
            case 5:
                size = loadPokeindex.getGroundList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getGroundList().get(randomPokemonNumber));
                System.out.println("Boden" + "|Nummer: " + loadPokeindex.getGroundList().get(randomPokemonNumber));
                break;
            //Gesteinpokemon
            case 6:
                size = loadPokeindex.getRockList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getRockList().get(randomPokemonNumber));
                System.out.println("Gestein" + "|Nummer: " + loadPokeindex.getRockList().get(randomPokemonNumber));
                break;
            //Giftpokemon
            case 7:
                size = loadPokeindex.getPoisonList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getPoisonList().get(randomPokemonNumber));
                System.out.println("Gift" + "|Nummer: " + loadPokeindex.getPoisonList().get(randomPokemonNumber));
                break;
            //Kampfpokemon
            case 8:
                size = loadPokeindex.getFightingList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getFightingList().get(randomPokemonNumber));
                System.out.println("Kampf" + "|Nummer: " + loadPokeindex.getFightingList().get(randomPokemonNumber));
                break;
            //Normalpokemon
            case 9:
                size = loadPokeindex.getNormalList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getNormalList().get(randomPokemonNumber));
                System.out.println("Normal" + "|Nummer: " + loadPokeindex.getNormalList().get(randomPokemonNumber));
                break;
            //Psychopokemon
            case 10:
                size = loadPokeindex.getPsychicList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getPsychicList().get(randomPokemonNumber));
                System.out.println("Psycho" + "|Nummer: " + loadPokeindex.getPsychicList().get(randomPokemonNumber));
                break;
            //Elektropokemon
            case 11:
                size = loadPokeindex.getElectricList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getElectricList().get(randomPokemonNumber));
                System.out.println("Elektro" + "|Nummer: " + loadPokeindex.getElectricList().get(randomPokemonNumber));
                break;
            //Feenpokemon
            case 12:
                size = loadPokeindex.getFairyList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getFairyList().get(randomPokemonNumber));
                System.out.println("Fee" + "|Nummer: " + loadPokeindex.getFairyList().get(randomPokemonNumber));
                break;
            //Flugpokemon
            case 13:
                size = loadPokeindex.getFlyingList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getFlyingList().get(randomPokemonNumber));
                System.out.println("Flug" + "|Nummer: " + loadPokeindex.getFlyingList().get(randomPokemonNumber));
                break;
            //Drachenpokemon
            case 14:
                size = loadPokeindex.getDragonList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getDragonList().get(randomPokemonNumber));
                System.out.println("Drache" + "|Nummer: " + loadPokeindex.getDragonList().get(randomPokemonNumber));
                break;
            //Käferpokemon
            case 15:
                size = loadPokeindex.getBugList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getBugList().get(randomPokemonNumber));
                System.out.println("Käfer" + "|Nummer: " + loadPokeindex.getBugList().get(randomPokemonNumber));
                break;
            //Pflanzenpokemon
            case 16:
                size = loadPokeindex.getGrassList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getGrassList().get(randomPokemonNumber));
                System.out.println("Pflanze" + "|Nummer: " + loadPokeindex.getGrassList().get(randomPokemonNumber));
                break;
            //Stahlpokemon
            case 17:
                size = loadPokeindex.getSteelList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getSteelList().get(randomPokemonNumber));
                System.out.println("Stahl" + "|Nummer: " + loadPokeindex.getSteelList().get(randomPokemonNumber));
                break;
            //Feuerpokemon
            case 18:
                size = loadPokeindex.getFireList().size();
                randomPokemonNumber = (int) (Math.random() * size);
                p = createRandomEnemy(loadPokeindex.getFireList().get(randomPokemonNumber));
                System.out.println("Feuer" + "|Nummer: " + loadPokeindex.getFireList().get(randomPokemonNumber));
                break;
        }
        return p;
    }

    private Pokemon createRandomEnemy(Integer integer)
    {
        Pokemon p = new Pokemon();
        try
        {
            Gson gson = new Gson();
            java.net.URL url = new java.net.URL("https://pokeapi.co/api/v2/pokemon/" + integer);
            String json = stream(url);
            p = gson.fromJson(json, Pokemon.class);
            p.createMove(p);
            return p;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return p;
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
}