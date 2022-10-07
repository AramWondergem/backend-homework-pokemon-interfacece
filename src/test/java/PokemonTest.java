import org.junit.jupiter.api.Test;

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
        Pokemon p = new Pokemon("raichu");
        Pokemon p2 = new Pokemon("raichu");
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.addEnergyForAttackCount();
        p.printAttacks(p2);
    }

}