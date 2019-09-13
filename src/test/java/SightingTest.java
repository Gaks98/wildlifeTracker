import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

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

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = setupNewSighting();
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting firstSighting = setupNewSighting();
        firstSighting.save();
        Sighting secondSighting = setupOtherSighting();
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }


    //helper method
    public Sighting setupNewSighting(){
        return new Sighting("Zone A", "Henry");
    }
    public Sighting setupOtherSighting(){
        return new Sighting("Zone B", "Alex");
    }
}