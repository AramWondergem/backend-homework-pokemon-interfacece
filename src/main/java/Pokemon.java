import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Pokemon {
    private String name;
    private String type;
    private int level;
    private int hp;
    private String food;
    private String sound;
    private static int pokemonCount;
    private int energyForAttackCount;
    private PokemonTrainer trainer;
    // place to save all the attacks which can be done
    private Map<String, List<String>> generalAttacks = new HashMap<>();
    private List<String> attacksElectric = new ArrayList<>();
    private List<String> attacksGrass = new ArrayList<>();
    private List<String> attacksFire = new ArrayList<>();
    private List<String> attacksWater = new ArrayList<>();



    public Pokemon(String name) {
        pokemonCount++;
        loadAttacks("src/main/resources/attacksElectric.txt", attacksElectric);
        loadAttacks("src/main/resources/attacksWater.txt", attacksWater);
        loadAttacks("src/main/resources/attacksGrass.txt", attacksGrass);
        loadAttacks("src/main/resources/attacksFire.txt", attacksFire);

        generalAttacks.put("electric", attacksElectric);
        generalAttacks.put("fire", attacksFire);
        generalAttacks.put("water", attacksWater);
        generalAttacks.put("grass", attacksGrass);
        this.name = name;
        pokemonBirth();

    }

    private void loadAttacks(String pathname, List attacksInfo) {
        try {
            File attacksFile = new File(pathname);
            Scanner fileScanner = new Scanner(attacksFile);

            while (fileScanner.hasNext()) {

                String nameAttack = fileScanner.nextLine();
                attacksInfo.add(nameAttack);
                String forceAttack = fileScanner.nextLine();
                attacksInfo.add(forceAttack);
                String energyNeeded = fileScanner.nextLine();
                attacksInfo.add(energyNeeded);

            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Het document bestaat niet");
        }

    }

    public void pokemonBirth() {
        try {
            File pokemonsFile = new File("src/main/resources/bluePrintPokemons.txt");
            Scanner fileScanner = new Scanner(pokemonsFile);
            fileScanner.useDelimiter(";");
            while (fileScanner.hasNext()) {

                String placeholderOutputFilescanner = fileScanner.next();

                if (placeholderOutputFilescanner.equals(name)) {
                    this.level = Integer.parseInt(fileScanner.next());
                    this.hp = Integer.parseInt(fileScanner.next());
                    this.food = fileScanner.next();
                    this.sound = fileScanner.next();
                    this.energyForAttackCount = Integer.parseInt(fileScanner.next());
                    this.type=fileScanner.next();
                }

            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Het document bestaat niet");
        }
        Main.waitingMain(1000);
        System.out.println("New " + name + " is build");
        Main.waitingMain(1000);


    }

    public int damageCalculator(String attackName, Pokemon enemy) {
        int placeholderDamage = 0;
        if (attackName.equals("Raindance") && enemy.getType().equals("electric")) {
            placeholderDamage = 0;
        }  else { //standard damage distribution between the types of pokemons

            boolean mostEffect = (this.type.equals("fire") && enemy.getType().equals("grass")) || (this.type.equals("grass") && enemy.getType().equals("electric")) || (this.type.equals("electric") && enemy.getType().equals("water")) || (this.type.equals("water") && enemy.getType().equals("fire"));
            boolean lessEffect1 = (this.type.equals("fire") && enemy.getType().equals("water")) || (this.type.equals("grass") && enemy.getType().equals("fire")) || (this.type.equals("electric") && enemy.getType().equals("grass")) || (this.type.equals("water") && enemy.getType().equals("electric"));
            boolean lessEffect2 = (this.type.equals("fire") && enemy.getType().equals("electric")) || (this.type.equals("grass") && enemy.getType().equals("water")) || (this.type.equals("electric") && enemy.getType().equals("fire")) || (this.type.equals("water") && enemy.getType().equals("grass"));
            boolean leastEffect = (this.type.equals("fire") && enemy.getType().equals("fire")) || (this.type.equals("grass") && enemy.getType().equals("grass")) || (this.type.equals("electric") && enemy.getType().equals("electric")) || (this.type.equals("water") && enemy.getType().equals("water"));

            if (mostEffect) {
                placeholderDamage = getStandardDamageOrEnergyCosts(attackName, 1) * 2;
            } else if (lessEffect1) {
                placeholderDamage = getStandardDamageOrEnergyCosts(attackName, 1);
            } else if (lessEffect2) {
                placeholderDamage = (getStandardDamageOrEnergyCosts(attackName, 1) / 5) * 3;
            } else if (leastEffect) {
                placeholderDamage = (getStandardDamageOrEnergyCosts(attackName, 1) / 5);
            }

            if (attackName.equals("Raindance") && enemy.getType().equals("grass")) {
                placeholderDamage = placeholderDamage * -1; // multiple with -1 because it will boost the hp instead of giving damage
            }else if (attackName.equals("Thunder") && enemy.getType().equals("electric")) {
                placeholderDamage = placeholderDamage * -1; //casting to integer because it is a list of strings and multipe with -1 because it will boost the hp instead of giving damage
            }
        }
        return placeholderDamage;
    }

    public int getStandardDamageOrEnergyCosts(String attackName, int oneForDamageTwoForEnergyCosts) {
        int placeholderIndex;
        int placeholderDamageOrEnergyCosts = 0;

        for (List<String> list : generalAttacks.values()) {
            for (int i = 0; i < list.size(); i++) {
                String listItem = list.get(i);
                if (listItem.equals(attackName)) {
                    placeholderIndex = i + oneForDamageTwoForEnergyCosts;//+1 or +2 because the index of the damage is the index of attackname plus one and for energy costs it is plus two
                    placeholderDamageOrEnergyCosts = Integer.parseInt(list.get(placeholderIndex));
                }
            }
        }
        return placeholderDamageOrEnergyCosts;
    }

    public String getAttackBasedOnEnergy(String energyForAttackCount) {
        List<String> placeholderListAttacksType = generalAttacks.get(this.type);
        int placeholderIndex;
        String placeHolderAttack="";

        for (int i = 0; i < placeholderListAttacksType.size(); i++) {
                String listItem = placeholderListAttacksType.get(i);
                if (listItem.equals(energyForAttackCount)) {
                    placeholderIndex = i -2 ;//-2 because the index of the attack is the index of energy needed minus two
                    placeHolderAttack = placeholderListAttacksType.get(placeholderIndex);
                    break;
                }
            }

        if (placeHolderAttack.equals("")) {
            System.out.println("something went wrong in the getAttacksBasedOnEnergy method in pokemon class");
        }

        return placeHolderAttack;
    }


    public void printAttacks(Pokemon enemy) {
        List<String> placeholderAttacksArray = generalAttacks.get(this.type);
        List<Integer> placeholderIndexAttacks = new ArrayList<>();
        boolean energyMatchesCosts = false;
        for (int i = 0; i < placeholderAttacksArray.size(); i++) {
            try {
            energyMatchesCosts = Integer.parseInt(placeholderAttacksArray.get(i)) <= energyForAttackCount;
            } catch (NumberFormatException e) {
            }

            if (energyMatchesCosts) {
                placeholderIndexAttacks.add(i - 2); // minus two, because that is the index of the attack in relation to the energycosts. See attacks file and load attacks method.
            }
            energyMatchesCosts=false;
        }

        System.out.println("index:attack(damage)");
        for (Integer j : placeholderIndexAttacks) {
            String nameAttack = placeholderAttacksArray.get(j);
            Integer damage = damageCalculator(nameAttack, enemy);

            System.out.print("\n" + j + ":" + nameAttack + "(" + damage + ")");

            if (nameAttack.equals("Raindance") && damage == 0) {
                System.out.println("---It has no effect on " + enemy.getName() + ", because it is an electric pokemon");
            } else if (nameAttack.equals("Raindance") && enemy.getType().equals("grass")) {
                System.out.println("---It will boost the hp of the enemy, because it is a grass pokemon");
            } else if (nameAttack.equals("Thunder") && enemy.getType().equals("electric")) {
                System.out.println("---It will boost the hp of the enemy, because it is a electric pokemon");
            } else if (nameAttack.equals("LeechSeed")) {
                System.out.println("---It will add the HP lose of your enemy to the HP of your own pokemon");
            }
        }


    }

    public void doAttack(int indexAttack, Pokemon enemy) {
        List<String> placeholderAttacksArray = generalAttacks.get(this.type);
        String nameAttack = placeholderAttacksArray.get(indexAttack);
        Integer damage = damageCalculator(nameAttack, enemy);

        if (nameAttack.equals("Raindance") && damage == 0) {
            this.makeSound();
            System.out.println(nameAttack + " has no effect on " + enemy.getName() + ", because it is an electric Pokemon");
        } else if (nameAttack.equals("Raindance") && enemy.getType().equals("grass")) {
            enemy.losesHP(damage);
            this.makeSound();
            System.out.println(this.name + " does the attack " + nameAttack.toUpperCase(Locale.ROOT) + " on " + enemy.getName() + ". It boosted his HP with " + (Math.abs(damage)) + " to " + enemy.getHp());
        } else if (nameAttack.equals("Thunder") && enemy.getType().equals("electric")) {
            enemy.losesHP(damage);
            this.makeSound();
            System.out.println(this.name + " does the attack " + nameAttack.toUpperCase(Locale.ROOT) + " on " + enemy.getName() + ". It boosted his HP with " + (Math.abs(damage)) + " to " + enemy.getHp());
        } else if (nameAttack.equals("LeechSeed")) {
            enemy.losesHP(damage);
            this.losesHP(-damage);
            this.makeSound();
            System.out.println(this.name + " does the attack " + nameAttack.toUpperCase(Locale.ROOT) + " on " + enemy.getName() + ". It boosted his own HP with " + (damage) + " to " + this.getHp() + ". The HP of " + enemy.getName() + " lowered with " + damage + " to " + enemy.getName());
        } else {
            enemy.losesHP(damage);
            this.makeSound();
            System.out.println(this.name + " does the attack " + nameAttack.toUpperCase(Locale.ROOT) + " on " + enemy.getName() + ". It lowered his HP with " + (damage) + " to " + enemy.getHp());

        }

        System.out.println("Energy before: " + energyForAttackCount);
        this.energyForAttackCount -= this.getStandardDamageOrEnergyCosts(nameAttack, 2);
        System.out.println("Energy after: " + energyForAttackCount);
    }

    public void makeSound() {
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.print(this.sound);
        }
        System.out.println();
    }

    public void eatingFood() {
        this.hp += 10; //When this has to be changed, also change attackPokemonHpWIthFOod in chooseAttackOrFeed method of the PokemonGymOwnerClass
    }

    public void addEnergyForAttackCount() {
        if (energyForAttackCount < 4) {
            energyForAttackCount++;
        } else {
            System.out.println("Your pokemon cannot receive more energy");
        }
    }

    public void losesHP(int damage) {
        this.hp-=damage;
        if (this.hp<=0) {
            System.out.println(this.name + " is dead.");
            removeTrainer(this.trainer);
        }
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



    public int getEnergyForAttackCount() {
        return energyForAttackCount;
    }

    public PokemonTrainer getTrainer() {
        return trainer;
    }

    public void setTrainer (PokemonTrainer trainer){
        if (this.trainer != trainer && this.trainer != null){
            this.trainer.removePokemon(this);
        }
        if (!trainer.hasPokemon(this)) {
            trainer.addPokemon(this);
        }
        this.trainer=trainer;
    }

    public void removeTrainer(PokemonTrainer trainer) {
        if (trainer.hasPokemon(this)) {
            trainer.removePokemon(this);
        }
        this.trainer = null;
    }

    public Map<String, List<String>> getGeneralAttacks() {
        return generalAttacks;
    }
}
