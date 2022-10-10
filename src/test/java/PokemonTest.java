import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    @Test
    void when_GameStarts_expected_GeneralAttacksWillBeFilledWithAttacks() {
        //Arrange
        Pokemon p = new Pokemon("raichu");
        //assert
        assertEquals("ThunderPunch", p.getGeneralAttacks().get("electric").get(0));
        assertEquals("5", p.getGeneralAttacks().get("electric").get(1));
        assertEquals("1", p.getGeneralAttacks().get("electric").get(2));

        assertEquals("FireLash", p.getGeneralAttacks().get("fire").get(0));
        assertEquals("5", p.getGeneralAttacks().get("fire").get(1));
        assertEquals("1", p.getGeneralAttacks().get("fire").get(2));

        assertEquals("LeafStorm", p.getGeneralAttacks().get("grass").get(0));
        assertEquals("5", p.getGeneralAttacks().get("grass").get(1));
        assertEquals("1", p.getGeneralAttacks().get("grass").get(2));

        assertEquals("Surf", p.getGeneralAttacks().get("water").get(0));
        assertEquals("5", p.getGeneralAttacks().get("water").get(1));
        assertEquals("1", p.getGeneralAttacks().get("water").get(2));
    }

    @Test
    void when_PokemonBirthIsCalled_Expect_LevelHPFoodSoundEnergyTypeAreFilled() {
        //Arrange and Act
        Pokemon p = new Pokemon("venusaur");
        //Assert
        assertEquals(31, p.getLevel());
        assertEquals(80, p.getHp());
        assertEquals("tulips", p.getFood());
        assertEquals("broooaaaah", p.getSound());
        assertEquals(0, p.getEnergyForAttackCount());
        assertEquals("grass", p.getType());

    }

    @Test
    void when_damageCalculatorIsCalled_Expect_ItWillChangeTheStandardDamageDependedOnAttackAndTypePokemonAttackPokemonAndTypePokemonEnemy() {
        //Arrange
        Pokemon attackPokemonWater = new Pokemon("blastoise");
        Pokemon attackPokemonElectric = new Pokemon("raichu");
        Pokemon enemyPokemonElectric = new Pokemon("raichu");
        Pokemon enemyPokemonGrass = new Pokemon("sunflora");

        //Act
        int attackHydropumpOnGrass = attackPokemonWater.damageCalculator("Hydropump", enemyPokemonGrass);
        int attackRaindanceOnElectric = attackPokemonWater.damageCalculator("Raindance", enemyPokemonElectric);
        int attackRaindanceOnGrass = attackPokemonWater.damageCalculator("Raindance", enemyPokemonGrass);
        int attackThunderOnElectric = attackPokemonElectric.damageCalculator("Thunder", enemyPokemonElectric);
        //Assert
        assertEquals(9, attackHydropumpOnGrass);
        assertEquals(0, attackRaindanceOnElectric);
        assertEquals(-24, attackRaindanceOnGrass);
        assertEquals(-6, attackThunderOnElectric);
    }

    @Test
    void when_getStandardDamageOrEnergyCosts_Expect_returnsStandardDamageOrEnergyCosts() {
        //Arrange and Act
        Pokemon p = new Pokemon("blastoise");
        int attackRaindanceDamage = p.getStandardDamageOrEnergyCosts("Raindance", 1);
        int attackRaindanceEnergyCosts = p.getStandardDamageOrEnergyCosts("Raindance", 2);
        //Assert
        assertEquals(40, attackRaindanceDamage);
        assertEquals(4, attackRaindanceEnergyCosts);
    }

    @Test
    void when_getAttackBasedOnEnergy_Expect_ReturnAttackName() {
        //Arrange and Act
        Pokemon p = new Pokemon("raichu");
        String attackName4 = p.getAttackBasedOnEnergy("4");
        String attackName3 = p.getAttackBasedOnEnergy("3");
        //Assert
        assertEquals("Thunder", attackName4);
        assertEquals("ElectroBall", attackName3);
    }

    @Test
    void when_printAttacks_Expect_printsAllAttackbasedOnEnergycostsAndEnergy() {
        //Arrange and Act
        Pokemon p = new Pokemon("blastoise");
        Pokemon p2 = new Pokemon("raichu");
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //act
        p.printAttacks(p2);
        //assert
        assertTrue(outContent.toString().contains("Surf"));
        assertTrue(outContent.toString().contains("HydroCanon"));
        assertTrue(outContent.toString().contains("Hydropump"));
        assertTrue(outContent.toString().contains("Raindance"));

    }

    @Test
    void when_doAttack_Expect_printsTheInformationInTHeConsoleBasedOnAttackAndOppenent () {
        //Arrange and Act
        Pokemon p = new Pokemon("sunflora");
        Pokemon p2 = new Pokemon("raichu");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //Act
        p.doAttack(3, p2);
        //assert
        assertTrue(outContent.toString().contains("sunflora does the attack SOLARBEAM on raichu"));

    }

    @Test
    void when_energyForAttackCountIs4AndAddEnergyForAttackCount_Expect_WarningIsPrinted(){
       //Arrange and Act
        Pokemon p = new Pokemon("blastoise");
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        //Assert
        assertEquals(4,p.getEnergyForAttackCount());
    }

@Test
    void when_losesHPMoreThanHP_Expect_TrainerIsRemovedInPokemonObjectAndThePokemonIsRemovedInTrainerObject(){
   //Arrange
    Pokemon p = new Pokemon("venusaur");
    PokemonTrainer t= new PokemonTrainer("Hans");
    p.setTrainer(t);
    assertTrue(t.hasPokemon(p));
    assertSame(t, p.getTrainer());
    t.setAttackPokemon(p);
    //Act
    p.losesHP(80);
    //Assert
    assertFalse(t.hasPokemon(p));
    assertEquals(null,p.getTrainer());



}

    @Test
    void makeSound() {
        //Arrange
        Pokemon p=new Pokemon("raichu");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //Act
        p.makeSound();
        //assert
        assertEquals("\r\nRAAAAICHUUURAAAAICHUUURAAAAICHUUURAAAAICHUUURAAAAICHUUU\r\n", outContent.toString());

    }
}