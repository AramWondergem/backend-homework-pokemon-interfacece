import java.util.Scanner;

public class PokemonGymOwner extends PokemonTrainer{

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
}
