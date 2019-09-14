import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    Animal testAnimal = new Animal("Wolf", 1);
    Animal secondAnimal = new Animal("Spud", 1);
    Animal otherSecondAnimal = new Animal("Spud", 3);

     @Rule
        public DatabaseRule database = new DatabaseRule();

     @Test
     public void Animal_instantiatesCorrectly_true() {
        assertEquals(true, testAnimal instanceof Animal);
     }

     @Test
     public void Animal_instantiatesWithName_String() {
        assertEquals("Wolf", testAnimal.getName());
     }

    @Test
    public void Animal_instantiatesWithSightingId_int() {
        assertEquals(1, testAnimal.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animal anotherAnimal = new Animal("Bubbles", 1);
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        testAnimal.save();
        Animal savedMonster = Animal.all().get(0);
        assertEquals(savedMonster.getId(), testAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        testAnimal.save();
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_otherSecondAnimal() {
        testAnimal.save();
        otherSecondAnimal.save();
        assertEquals(Animal.find(otherSecondAnimal.getId()), otherSecondAnimal);
    }

    @Test
    public void save_savesPersonIdIntoDB_true() {
        Sighting testSighting = new Sighting("Henry", "henry@henry.com");
        testSighting.save();
        Animal testAnimal2 = new Animal("Bubbles", testSighting.getId());
        testAnimal2.save();
        Animal savedAnimal = Animal.find(testAnimal2.getId());
        assertEquals(savedAnimal.getSightingId(), testSighting.getId());
    }

}