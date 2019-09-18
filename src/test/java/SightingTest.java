import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = setupNewSighting();
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsPersonWithSameId_secondPerson() {
        Sighting firstSighting = setupNewSighting();
        firstSighting.save();
        Sighting secondSighting = setupOtherSighting();
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }

    @Test
    public void getAnimals_retrievesAllAnimalsFromDatabase_animalsList() {
        Sighting testSighting = new Sighting("Zone A","Henry");
        testSighting.save();
        Animal firstAnimal = new Animal("Bubbles", testSighting.getId());
        firstAnimal.save();
        Animal secondAnimal2 = new Animal("Spud", testSighting.getId());
        secondAnimal2.save();
        Animal[] animals = new Animal[] { firstAnimal, secondAnimal2 };
        assertTrue(testSighting.getAnimals().containsAll(Arrays.asList(animals)));
    }

    //helper method
    public Sighting setupNewSighting(){
        return new Sighting("Zone A", "Henry");
    }
    public Sighting setupOtherSighting(){
        return new Sighting("Zone B", "Alex");
    }
}