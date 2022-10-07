import java.util.NoSuchElementException;
import java.util.Scanner;

public class PokemonGymImpl {
    private String nameOfGymOwner;


//    public PokemonTrainer createGymOwner (String name) {
//        // create gymowner when gym is initialized
//    }


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
