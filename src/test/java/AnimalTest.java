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

}