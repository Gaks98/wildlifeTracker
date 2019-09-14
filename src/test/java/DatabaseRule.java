import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "yvonne", "123456");  //Those with linux or windows use two strings for username and password
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deleteAnimalsQuery).executeUpdate();
        }
    }

}

