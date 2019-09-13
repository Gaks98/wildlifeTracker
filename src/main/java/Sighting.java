import org.sql2o.Connection;

import java.util.List;

public class Sighting {

        private String location;
        private String rangerName;
        private int id;

        public Sighting(String location, String rangerName) {
            this.location = location;
            this.rangerName = rangerName;
        }

    @Override
    public boolean equals(Object otherSighting){
        if (!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getLocation().equals(newSighting.getLocation()) &&
                    this.getRangerName().equals(newSighting.getRangerName());
        }
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getId() {
        return id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (location, rangerName) VALUES (:location, :rangerName)";
            con.createQuery(sql)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .executeUpdate();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
}
