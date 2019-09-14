import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    Animal testAnimal = new Animal("Wolf", 1);

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

}