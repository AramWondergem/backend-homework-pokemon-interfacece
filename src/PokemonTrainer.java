import java.util.*;

public class PokemonTrainer {
    private String name;
    private List<Pokemon> benchPokemons = new ArrayList<>();
    private Pokemon attackPokemon;
    private List<String> cardDeckEnergieAndFood= new ArrayList<>();

    public PokemonTrainer(String name){
        this.name=name;
    }

    public void chooseAttack(PokemonGymImpl gym) {
        Scanner userInput=new Scanner(System.in);
        int placeholderAnswer;

        attackPokemon.printAttacks();
        placeholderAnswer = PokemonGymImpl.askingInput("Which attack do you choose? Type in the number of the attack.");
        attackPokemon.doAttack(placeholderAnswer,gym);
    }

    public void choosePokemon(PokemonGymImpl gym) {
        this.printBenchPokemon();
        int placeholderAnswer = PokemonGymImpl.askingInput("Whick do you choose from your deck? Type in the number of the pokemon.")
        this.attackPokemon = benchPokemons.get(placeholderAnswer);
        benchPokemons.remove(placeholderAnswer);
        System.out.println(attackPokemon.getName() + " is ready to fight his opponent");
        attackPokemon.makeSound();
    }

    public void printBenchPokemon() {
        for (Pokemon p:this.benchPokemons){
            System.out.println(benchPokemons.indexOf(p) + ":" + p.getName());
        }

    }

    public void chooseEnergyCard (){
        //Make a list of pokemons that can receive energy. They must have less than 4 energyForAttackCount and in the energy and food deck should be an card they can receive
        List<Pokemon> pokemonsThatCanReceiveEnergy= new ArrayList<>();
        boolean deckMatchCards = false;

        for (Pokemon p: benchPokemons) {
            if(cardDeckEnergieAndFood.contains(p.getType())&&p.getEnergyForAttackCount()<4) {
                deckMatchCards=true;
                pokemonsThatCanReceiveEnergy.add(p);
            }
        }
        if(cardDeckEnergieAndFood.contains(attackPokemon.getType())&&attackPokemon.getEnergyForAttackCount()<4){
            deckMatchCards=true;
            pokemonsThatCanReceiveEnergy.add(attackPokemon);
        }

        if (deckMatchCards) {
            printCardDeck();
            for (Pokemon p:pokemonsThatCanReceiveEnergy) {
                System.out.println(pokemonsThatCanReceiveEnergy.indexOf(p) + ":" + p.getName());
            }
            int placeholderAnswer=PokemonGymImpl.askingInput("Which pokemon do you want to give an energy card?");
            Pokemon energyReceivingPokemon = pokemonsThatCanReceiveEnergy.get(placeholderAnswer);
            energyReceivingPokemon.addEnergyForAttackCount();
            cardDeckEnergieAndFood.remove(energyReceivingPokemon.getType()); //removing the "energy card" from the deck
            System.out.println(energyReceivingPokemon.getName() + " received an energy card. His attack energy count is: " + energyReceivingPokemon.getEnergyForAttackCount());
            printCardDeck();
        } else {
            System.out.println("Your energy cards do not match your pokemons");
        }

    }

    public void printCardDeck(){
        System.out.println("----------Your Card Deck ------------");
        for (int i=0;1<cardDeckEnergieAndFood.size();i++){
            System.out.println(i + ":" + cardDeckEnergieAndFood.get(i));
        }

    }

    public void chooseFood () {
        List<Pokemon> pokemonsThatCanReceiveFood= new ArrayList<>();
        boolean foodMatchesPokemons = false;

        for (Pokemon p: benchPokemons) {
            if(cardDeckEnergieAndFood.contains(p.getFood())) {
                foodMatchesPokemons=true;
                pokemonsThatCanReceiveFood.add(p);
            }
        }
        if(cardDeckEnergieAndFood.contains(attackPokemon.getFood())){
            foodMatchesPokemons=true;
            pokemonsThatCanReceiveFood.add(attackPokemon);
        }

        if (foodMatchesPokemons) {
            printCardDeck();
            for (Pokemon p:pokemonsThatCanReceiveFood) {
                System.out.println(pokemonsThatCanReceiveFood.indexOf(p) + ":" + p.getName());
            }
            int placeholderAnswer=PokemonGymImpl.askingInput("Which pokemon do you want to give an food card?");
            Pokemon foodReceivingPokemon = pokemonsThatCanReceiveFood.get(placeholderAnswer);
            foodReceivingPokemon.eatingFood();
            cardDeckEnergieAndFood.remove(foodReceivingPokemon.getFood()); //removing the "food card" from the deck
            System.out.println(foodReceivingPokemon.getName() + " received an food card. His hp is now: " + foodReceivingPokemon.getHp());
            printCardDeck();
        } else {
            System.out.println("Your food cards do not match your pokemons");
        }

    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getBenchPokemons() {
        return benchPokemons;
    }

    public void setBenchPokemons(List<Pokemon> benchPokemons) {
        this.benchPokemons = benchPokemons;
    }

    public Pokemon getAttackPokemon() {
        return attackPokemon;
    }

    public void setAttackPokemon(Pokemon attackPokemon) {
        this.attackPokemon = attackPokemon;
    }

    public List<String> getCardDeckEnergieAndFood() {
        return cardDeckEnergieAndFood;
    }

    public void setCardDeckEnergieAndFood(List<String> cardDeckEnergieAndFood) {
        this.cardDeckEnergieAndFood = cardDeckEnergieAndFood;
    }
}
