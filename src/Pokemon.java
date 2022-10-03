import java.util.Arrays;
import java.util.List;

public abstract class Pokemon {
    private String name;
    private String type;
    private int level;
    private int hp;
    private String food;
    private String sound;
    private static int pokemonCount;
    private int energyForAttackCount=0;

    public Pokemon() {}

    public void printAttacks(){
    }

    public void doAttack(int indexAttack,PokemonGymImpl gym){
    }

    public void makeSound(){

    }

    public void eatingFood() {
        //increasing hp with a certain number
    }

    public void addEnergyForAttackCount() {
        //make it max 4
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public static int getPokemonCount() {
        return pokemonCount;
    }

    public static void setPokemonCount(int pokemonCount) {
        Pokemon.pokemonCount = pokemonCount;
    }

    public int getEnergyForAttackCount() {
        return energyForAttackCount;
    }
}
