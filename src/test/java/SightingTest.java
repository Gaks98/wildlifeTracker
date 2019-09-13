import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Test
    public void Sighting_instantiatesCorrectly_true() {
        Sighting testSighting = setupNewSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation_ZoneA() {
        Sighting testSighting = setupNewSighting();
        assertEquals("Zone A", testSighting.getLocation());
    }

    @Test
    public void getRangerName_sightingInstantiatesWithRangerName_String() {
        Sighting testSighting = setupNewSighting();
        assertEquals("Henry", testSighting.getRangerName());
    }

    @Test
    public void equals_returnsTrueIfLocationAndRangerNameAreSame_true() {
        Sighting firstSighting = setupNewSighting();
        Sighting anotherSighting = setupNewSighting();
        assertTrue(firstSighting.equals(anotherSighting));
    }



    //helper method
    public Sighting setupNewSighting(){
        return new Sighting("Zone A", "Henry");
    }
    public Sighting setupOtherSighting(){
        return new Sighting("Zone B", "Alex");
    }
}