import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonGymOwnerTest {

    @Test
    void when_BenchListcontainsTypeOfPokemon_Expect_ReturnsAListOfThePokemonsOfTheSameType () {
    //Arrange
        List<Pokemon> electricPokemons = new ArrayList<>();
        PokemonGymOwner gymOwner = new PokemonGymOwner("Pietje");
        for (int i=0;i<3;i++) {
            Pokemon p = new Pokemon("raichu");
            gymOwner.addPokemon(p);
            electricPokemons.add(p);

        }
        for (int i=0;i<3;i++) {
            Pokemon p = new Pokemon("sunflora");
            gymOwner.addPokemon(p);
        }
        Pokemon enemy = new Pokemon("blastoise");
        //Act
        List<Pokemon> electricPokemonsFromBench = gymOwner.containsTypeOfPokemon("electric");
        //assert
        assertArrayEquals(electricPokemons.toArray(),electricPokemonsFromBench.toArray());

    }

    @Test
    void when_choosePokemonWithWaterPokemonEnemy_Expect_ReturnElectricPokemon () {
        //Arrange
        List<Pokemon> electricPokemons = new ArrayList<>();
        PokemonGymOwner gymOwner = new PokemonGymOwner("Pietje");
        for (int i = 0; i < 3; i++) {
            Pokemon p = new Pokemon("raichu");
            gymOwner.addPokemon(p);
            electricPokemons.add(p);

        }
        for (int i = 0; i < 3; i++) {
            Pokemon p = new Pokemon("sunflora");
            gymOwner.addPokemon(p);
        }
        Pokemon enemy = new Pokemon("blastoise");
        //Act
        gymOwner.choosePokemon(enemy);
        //assert
        assertSame(electricPokemons.get(0), gymOwner.getAttackPokemon());
    }

    @Test
    void when_doAttackElectricToElectricAndNext3Energy_Expect_EnemyHasLowerHP() {
        //arrange
        PokemonGymOwner gymOwner = new PokemonGymOwner("Pietje");
        Pokemon attackPokemon = new Pokemon("raichu");
        Pokemon enemyPokemon = new Pokemon("raichu");
        gymOwner.setAttackPokemon(attackPokemon);
        for (int i = 0; i < 3; i++) {
            attackPokemon.addEnergyForAttackCount();
        }
        int hPEnemyPokemon = enemyPokemon.getHp();
        assertEquals(60,hPEnemyPokemon);
        //act
        gymOwner.chooseAttackOrFeed(enemyPokemon);
        //Assert
        assertNotEquals(80,enemyPokemon.getHp());
    }
}