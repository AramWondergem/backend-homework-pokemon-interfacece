import java.util.*;

public class PokemonGymOwner extends PokemonTrainer {

    public PokemonGymOwner(String name) {
        super(name);
    }

    @Override
    public void chooseAttack(PokemonGymImpl gym) {
        // check if pokemon can attack: numberofEnergyCards>0
        //get hp of the attack pokemon
        //get strongest attack of opponent
        // if hp<attack --> do strongest attack
        //else if numberofenergycount= highest attack(4) --> attack
        // else --> do not attack

    }

    @Override
    public void chooseFood() {
        List<Pokemon> pokemonsThatCanReceiveFood = super.getListWithPokemonsToFeed();
        boolean foodMatchesPokemons = pokemonsThatCanReceiveFood.size() > 0;

// choosing the pokemon which receive the food. If the attack pokemon can receive food it will be him, else the pokemon on the bench with the lowest HP.
        if (foodMatchesPokemons) {

            if (pokemonsThatCanReceiveFood.contains(attackPokemon)) {
                attackPokemon.eatingFood();
                cardDeckEnergieAndFood.remove(attackPokemon.getFood());
                System.out.println(attackPokemon.getName() + " received an food card from your opponent. His hp is now: " + attackPokemon.getHp());
            } else {
                Map<Integer, Pokemon> sortedPokemonsOnHP = new TreeMap<>();
                for (Pokemon p : pokemonsThatCanReceiveFood) {
                    sortedPokemonsOnHP.put(p.getHp(), p);
                }
                Pokemon luckyPokemon = sortedPokemonsOnHP.get(0);
                luckyPokemon.eatingFood();
                cardDeckEnergieAndFood.remove(luckyPokemon.getFood());
                System.out.println(luckyPokemon.getName() + " received an food card from your opponent. His hp is now: " + luckyPokemon.getHp());

            }
        } else {
            System.out.println("The food cards of the gym owner do not match his pokemons");
        }

    }


    @Override
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
            if (pokemonsThatCanReceiveEnergy.contains(attackPokemon)) {
                attackPokemon.addEnergyForAttackCount();
                cardDeckEnergieAndFood.remove(attackPokemon.getType()); //removing the "energy card" from the deck
                System.out.println(attackPokemon.getName() + " received an energy card from your opponent. His attack energy count is: " + attackPokemon.getEnergyForAttackCount());
            } else if (pokemonsThatCanReceiveEnergy.size() > 0) {
                Random random = new Random();
                int randomIndexForPokemon = random.nextInt(pokemonsThatCanReceiveEnergy.size());
                Pokemon luckyPokemon = pokemonsThatCanReceiveEnergy.get(randomIndexForPokemon);
                luckyPokemon.addEnergyForAttackCount();
                cardDeckEnergieAndFood.remove(luckyPokemon.getType()); //removing the "energy card" from the deck
                System.out.println(luckyPokemon.getName() + " received an energy card from your opponent. His attack energy count is: " + luckyPokemon.getEnergyForAttackCount());
            } else {
                System.out.println("Your opponent did not give a energy card to one of his pokemons");
            }

        }
    }

    @Override
    public void choosePokemon(Pokemon enemy) {
        List<Pokemon> waterPokemons = containsTypeOfPokemon("water");
        List<Pokemon> firePokemons = containsTypeOfPokemon("fire");
        List<Pokemon> grassPokemons = containsTypeOfPokemon("grass");
        List<Pokemon> electricPokemons = containsTypeOfPokemon("electric");
        switch (enemy.getType()) {
            case "fire":
                if (waterPokemons.size() > 0) {
                    super.attackPokemon = waterPokemons.get(0);
                } else if (grassPokemons.size() > 0) {
                    super.attackPokemon = grassPokemons.get(0);
                } else if (electricPokemons.size() > 0) {
                    super.attackPokemon = electricPokemons.get(0);
                } else {
                    super.attackPokemon = firePokemons.get(0);
                }
                break;
            case "water":
                if (electricPokemons.size() > 0) {
                    super.attackPokemon = electricPokemons.get(0);
                } else if (firePokemons.size() > 0) {
                    super.attackPokemon = firePokemons.get(0);
                } else if (grassPokemons.size() > 0) {
                    super.attackPokemon = grassPokemons.get(0);
                } else {
                    super.attackPokemon = waterPokemons.get(0);
                }
                break;
            case "electric":
                if (grassPokemons.size() > 0) {
                    super.attackPokemon = grassPokemons.get(0);
                } else if (waterPokemons.size() > 0) {
                    super.attackPokemon = waterPokemons.get(0);
                } else if (firePokemons.size() > 0) {
                    super.attackPokemon = firePokemons.get(0);
                } else {
                    super.attackPokemon = electricPokemons.get(0);
                }
                break;
            case "grass":
                if (firePokemons.size() > 0) {
                    super.attackPokemon = firePokemons.get(0);
                } else if (electricPokemons.size() > 0) {
                    super.attackPokemon = electricPokemons.get(0);
                } else if (waterPokemons.size() > 0) {
                    super.attackPokemon = waterPokemons.get(0);
                } else {
                    super.attackPokemon = grassPokemons.get(0);
                }
                break;
        }

        benchPokemons.remove(this.attackPokemon);
        System.out.println(attackPokemon.getName() + " is ready to fight your pokemon");
        attackPokemon.makeSound();
    }

    public List containsTypeOfPokemon(String type) {
        List<Pokemon> pokemonsOfOneTyp = new ArrayList<>();
        for (Pokemon p : benchPokemons) {
            if (p.getType().equals(type)) {
                pokemonsOfOneTyp.add(p);
            }
        }
        return pokemonsOfOneTyp;
    }

    public void chooseAttackOrFeed(Pokemon enemy) {

        //calculation for damage next attack of opponent
        Integer energyForAttackNextRound = enemy.getEnergyForAttackCount();
        if (energyForAttackNextRound < 4) {
            energyForAttackNextRound++;
        }
        Integer damageNextAttackOpponent = enemy.damageCalculator(enemy.getAttackBasedOnEnergy(Integer.toString(energyForAttackNextRound)), attackPokemon);

        // index of attack which can be done this round in the arraylist by attack pokemon
        Integer indexAttack = null;
        String attackName = this.attackPokemon.getAttackBasedOnEnergy(Integer.toString(this.attackPokemon.getEnergyForAttackCount()));
        Map<String, List<String>> placeholderGeneralAttacksMap = this.attackPokemon.getGeneralAttacks();
        List<String> placeholderAttacksList = placeholderGeneralAttacksMap.get(this.attackPokemon.getType());

        for (int i = 0; i < placeholderAttacksList.size(); i++) {
            String listItem = placeholderAttacksList.get(i);
            if (listItem.equals(attackName)) {
                indexAttack = i;
                break;
            }
        }
        // Warning if indexAttack still stays zero
        if (indexAttack == null) {
            System.out.println("Something went wrong in chooseAttackOrFeed method in PokemonGymOwner class");
        }


        //situation where the attackpokomon is a electric pokemon and next round the thunder attack has a positive effect on enemy of the electric type. --> attack
        boolean opponentElectricAndNextRoundThunder = super.attackPokemon.getType().equals("electric") && enemy.getType().equals("electric") && super.attackPokemon.getEnergyForAttackCount() == 3;
        //situation where the attackpokomon is a water pokemon and next round the raindance attack has a no effect(electric type) or a positive effect on enemy (grass type) --> attack
        boolean attackPokemonWaterAndNextRoundRaindanceHasNoEffect = super.attackPokemon.getType().equals("water") && (enemy.getType().equals("electric") || enemy.getType().equals("grass")) && super.attackPokemon.getEnergyForAttackCount() == 3;
        //situation where the attackpokemon can do his strongest attack --> attack
        boolean strongestAttack = super.getAttackPokemon().getEnergyForAttackCount() == 4;
        //situation in which the opponent can be killed with attack --> attack
        boolean killOpponent = attackPokemon.damageCalculator(attackPokemon.getAttackBasedOnEnergy(Integer.toString(this.attackPokemon.getEnergyForAttackCount())), enemy) >= enemy.getHp();
        // situation where there is no food card --> wait until next time with attack or attack
        boolean noFoodForPokemon = super.getListWithPokemonsToFeed().size() == 0;
        Integer attackPokemonHPWithoutFood = attackPokemon.getHp();
        //situation in which you can give food to attackPokemon
        boolean foodForAttackPokemon = !noFoodForPokemon && super.cardDeckEnergieAndFood.contains(this.attackPokemon.getFood());
        // situation in which you can feedd only bench pokemons
        boolean foodForPokemon = !noFoodForPokemon;




        if (opponentElectricAndNextRoundThunder) {
            //situation where the attackpokomon is a electric pokemon and next round the thunder attack has a positive effect on enemy of the electric type. --> attack
            this.attackPokemon.doAttack(indexAttack, enemy);
        } else if (attackPokemonWaterAndNextRoundRaindanceHasNoEffect) {
            //situation where the attackpokomon is a water pokemon and next round the raindance attack has a no effect(electric type) or a positive effect on enemy (grass type) --> attack
            this.attackPokemon.doAttack(indexAttack, enemy);
        } else if (strongestAttack) {
            //situation where the attackpokemon can do his strongest attack --> attack
            this.attackPokemon.doAttack(indexAttack, enemy);
        } else if (killOpponent) {
            //situation in which the opponent can be killed with attack --> attack
            this.attackPokemon.doAttack(indexAttack, enemy);
        } else if (noFoodForPokemon) {
            // situation where there is no food card
            boolean willNotSurviveAttackNextRound = attackPokemonHPWithoutFood <= damageNextAttackOpponent;
            boolean willSurviveAttackNextRound = attackPokemonHPWithoutFood > damageNextAttackOpponent;
            if (willNotSurviveAttackNextRound) {
                // cannot survive next attack --> attack
                this.attackPokemon.doAttack(indexAttack, enemy);
            } else if (willSurviveAttackNextRound) {
                // can survice next attack --> wait for next round
                System.out.println("Your opponent will wait until next round to attack");
            }
        } else if (foodForAttackPokemon) {
            Integer attackPokemonHPWithFood = attackPokemon.getHp() + 10;// plus 10, because that is the result of eating food for pokemon;
            boolean willNotSurviveAttackNextRound = attackPokemonHPWithFood <= damageNextAttackOpponent;
            boolean willSurviveAttackNextRound = attackPokemonHPWithFood > damageNextAttackOpponent;
            if (willNotSurviveAttackNextRound) {
                // with food your pokemon will not survive the next attack when your opponent can add an energy card to pokemon --> attack
                this.attackPokemon.doAttack(indexAttack, enemy);
            } else if (willSurviveAttackNextRound) {
                // can survive next attack --> wait for next round and give food
                chooseFood();
            }
        } else if (foodForPokemon) {
            boolean willNotSurviveAttackNextRound = attackPokemonHPWithoutFood <= damageNextAttackOpponent;
            boolean willSurviveAttackNextRound = attackPokemonHPWithoutFood > damageNextAttackOpponent;
            if (willNotSurviveAttackNextRound) {
                // cannot survive next attack --> attack
                this.attackPokemon.doAttack(indexAttack, enemy);
            } else if (willSurviveAttackNextRound) {
                // can survive next attack --> give food
                chooseFood();
            }
        } else {
            System.out.println("Something went wrong. All situations should be covered in chooseAttackOrFeed method");
        }

    }
}

