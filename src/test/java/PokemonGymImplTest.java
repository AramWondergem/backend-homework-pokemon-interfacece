import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonGymImplTest {

    @Test
    void when_enteredTheGym_Expect_GymOwnerIsCreated_BenchIsFilled_VisitorHasPokemonTrainerObject(){
        //Arrange
        PokemonTrainer p = new PokemonTrainer("dummy");
        PokemonGymImpl gym = new PokemonGymImpl();
        //Act
        gym.enteredTheGym(p);
        //Assert
        assertEquals("Winowa",gym.getGymOwner().getName());
        assertNotEquals(0,gym.getGymOwner().getBenchPokemons().size());
        assertSame(p,gym.getVisitor());
    }




}