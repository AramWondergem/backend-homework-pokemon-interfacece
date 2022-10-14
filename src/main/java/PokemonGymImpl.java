import java.util.NoSuchElementException;
import java.util.Scanner;

public class PokemonGymImpl {

    private PokemonGymOwner gymOwner;
    private PokemonTrainer visitor;

    public PokemonGymImpl() {
    }

    public void enteredTheGym(PokemonTrainer visitor) {
        PokemonGymOwner gymOwner1 = new PokemonGymOwner("Winowa");
        this.gymOwner = gymOwner1;
        gymOwner1.fillBenchWithPokemon();
        this.visitor = visitor;

        // Hier tekst toevoegen over welkom en hoe het spel gaat lopen
    }




    public PokemonGymOwner getGymOwner() {
        return gymOwner;
    }

    public PokemonTrainer getVisitor() {
        return visitor;
    }

    public static int askingInput(String question) {
        Scanner userInput = new Scanner(System.in);
        int placeholderAnswer=-1;
        boolean correctAnswer;

        do {
            try {
                System.out.println(question);
                System.out.println("number: ");
                placeholderAnswer = userInput.nextInt();
                correctAnswer = true;


            } catch (NoSuchElementException e) {
                System.out.println("Het is niet goed gegaan. Probeer het opnieuw");
                correctAnswer = false;
                userInput.nextLine();//workaround to reset the scanner
            }
        }
        while (!correctAnswer);

        return placeholderAnswer;
    }
}
