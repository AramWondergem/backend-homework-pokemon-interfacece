import java.util.*;

public class PokemonTrainer {
    private String name;
    List<Pokemon> benchPokemons = new ArrayList<>();
    Pokemon attackPokemon;
    List<String> cardDeckEnergieAndFood = new ArrayList<>();

    public PokemonTrainer(String name) {
        this.name = name;
    }

    public void chooseAttack(PokemonGymImpl gym) {
        int placeholderAnswer;

        attackPokemon.printAttacks();
        placeholderAnswer = PokemonGymImpl.askingInput("Which attack do you choose? Type in the number of the attack.");
        attackPokemon.doAttack(placeholderAnswer, gym);
    }

    public void choosePokemon(Pokemon enemy) {
        this.printBenchPokemon();
        int placeholderAnswer = PokemonGymImpl.askingInput("Which do you choose from your deck? Type in the number of the pokemon.");
        this.attackPokemon = benchPokemons.get(placeholderAnswer);
        benchPokemons.remove(placeholderAnswer);
        System.out.println(attackPokemon.getName() + " is ready to fight his opponent");
        attackPokemon.makeSound();
    }

    public void printBenchPokemon() {
        for (Pokemon p : this.benchPokemons) {
            System.out.println(benchPokemons.indexOf(p) + ":" + p.getName());
        }

    }

    public void chooseEnergyCard() {
        //Make a list of pokemons that can receive energy. They must have less than 4 energyForAttackCount and in the energy and food deck should be an card they can receive
        List<Pokemon> pokemonsThatCanReceiveEnergy = new ArrayList<>();
        boolean deckMatchCards = false;

        for (Pokemon p : benchPokemons) {
            if (cardDeckEnergieAndFood.contains(p.getType()) && p.getEnergyForAttackCount() < 4) {
                deckMatchCards = true;
                pokemonsThatCanReceiveEnergy.add(p);
            }
        }
        if (cardDeckEnergieAndFood.contains(attackPokemon.getType()) && attackPokemon.getEnergyForAttackCount() < 4) {
            deckMatchCards = true;
            pokemonsThatCanReceiveEnergy.add(attackPokemon);
        }

        if (deckMatchCards) {
            printCardDeck();
            for (Pokemon p : pokemonsThatCanReceiveEnergy) {
                System.out.println(pokemonsThatCanReceiveEnergy.indexOf(p) + ":" + p.getName());
            }
            int placeholderAnswer = PokemonGymImpl.askingInput("Which pokemon do you want to give an energy card?");
            Pokemon energyReceivingPokemon = pokemonsThatCanReceiveEnergy.get(placeholderAnswer);
            energyReceivingPokemon.addEnergyForAttackCount();
            cardDeckEnergieAndFood.remove(energyReceivingPokemon.getType()); //removing the "energy card" from the deck
            System.out.println(energyReceivingPokemon.getName() + " received an energy card. His attack energy count is: " + energyReceivingPokemon.getEnergyForAttackCount());
            printCardDeck();
        } else {
            System.out.println("Your energy cards do not match your pokemons");
        }

    }

    public void printCardDeck() {
        System.out.println("----------Your Card Deck ------------");
        for (int i = 0; 1 < cardDeckEnergieAndFood.size(); i++) {
            System.out.println(i + ":" + cardDeckEnergieAndFood.get(i));
        }

    }
    public List getListWithPokemonsToFeed() {
        List<Pokemon> pokemonsThatCanReceiveFood = new ArrayList<>();

// filling a list with the pokemons that can receive food based on the card deck
        for (Pokemon p : benchPokemons) {
            if (cardDeckEnergieAndFood.contains(p.getFood())) {
                pokemonsThatCanReceiveFood.add(p);
            }
        }
        if (cardDeckEnergieAndFood.contains(attackPokemon.getFood())) {

            pokemonsThatCanReceiveFood.add(attackPokemon);
        }
        return pokemonsThatCanReceiveFood;
    }
    public void chooseFood() {
        List<Pokemon> pokemonsThatCanReceiveFood = getListWithPokemonsToFeed();
        boolean foodMatchesPokemons = pokemonsThatCanReceiveFood.size()>0;

        if (foodMatchesPokemons) {
            printCardDeck();
            for (Pokemon p : pokemonsThatCanReceiveFood) {
                System.out.println(pokemonsThatCanReceiveFood.indexOf(p) + ":" + p.getName());
            }
            int placeholderAnswer = PokemonGymImpl.askingInput("Which pokemon do you want to give an food card?");
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

    public Pokemon getAttackPokemon() {
        return attackPokemon;
    }

    public void setAttackPokemon(Pokemon attackPokemon) {
        this.attackPokemon = attackPokemon;
    }

    public void addPokemon(Pokemon pokemon) {
        benchPokemons.add(pokemon);
        if (pokemon.getTrainer() != this && pokemon.getTrainer() != null) {
            pokemon.removeTrainer(pokemon.getTrainer());
        }
        pokemon.setTrainer(this);
    }

    public void removePokemon(Pokemon pokemon) {
        if (benchPokemons.contains(pokemon)) {
            benchPokemons.remove(pokemon);
        } else if (attackPokemon.equals(pokemon)) {
            attackPokemon = null;
        }
        pokemon.removeTrainer(this);
    }

    public boolean hasPokemon(Pokemon pokemon) {
        boolean hasPokemon = benchPokemons.contains(pokemon)||attackPokemon.equals(pokemon);
        return hasPokemon;

    }
}
