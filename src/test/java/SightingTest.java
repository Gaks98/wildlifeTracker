import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Test
    public void Sighting_instantiatesCorrectly_true() {
        Sighting testSighting = new Sighting("Zone A", "Henry");
        assertEquals(true, testSighting instanceof Sighting);
    }

    

}