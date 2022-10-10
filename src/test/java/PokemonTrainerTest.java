import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTrainerTest {

    @Test
    void when_chooseAttack_Expect_EnemyPokemonHPChangedAndAttackPokemonHasLowerEnergy(){
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("6".getBytes());
        System.setIn(in);
        PokemonTrainer trainer = new PokemonTrainer("hans");
        Pokemon attackPokemon = new Pokemon("raichu");
        Pokemon enemyPokemon = new Pokemon("raichu");
        for (int i=0;i<4;i++){
            attackPokemon.addEnergyForAttackCount();
        }
        trainer.setAttackPokemon(attackPokemon);
        int hpLevelEnemyPokemon = enemyPokemon.getHp();
        int energyLevelAttackPokemon = attackPokemon.getEnergyForAttackCount();
        //Act
        trainer.chooseAttack(enemyPokemon);
        //Assert
        assertNotEquals(hpLevelEnemyPokemon,enemyPokemon.getHp());
        assertNotEquals(energyLevelAttackPokemon,attackPokemon.getEnergyForAttackCount());
    }

    @Test
    void when_choosePokemon_Expect_attackPokemonIsFilledAndAttackpokemonIsNotInBenchPokemons(){
        //Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        PokemonTrainer trainer = new PokemonTrainer("hans");
        Pokemon p1 = new Pokemon("raichu");
        Pokemon p2 = new Pokemon("raichu");
        Pokemon p3 = new Pokemon("venusaur");
        Pokemon p4 = new Pokemon("venusaur");
        Pokemon p5 = new Pokemon("gyarados");
        Pokemon p6 = new Pokemon("gyarados");
        Pokemon enemy = new Pokemon("Gyarados");
        trainer.addPokemon(p1);
        trainer.addPokemon(p2);
        trainer.addPokemon(p3);
        trainer.addPokemon(p4);
        trainer.addPokemon(p5);
        trainer.addPokemon(p6);
        //Act
        trainer.choosePokemon(enemy);
        //Assert
        assertSame(p5,trainer.getAttackPokemon());
        assertFalse(trainer.getBenchPokemons().contains(p5));
    }




}