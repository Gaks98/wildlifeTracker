import org.sql2o.Connection;

public class Animal {
    private String name;
    private int sightingId;
    private int id;

    public Animal(String name, int sightingId) {
        this.name = name;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getSightingId() == newAnimal.getSightingId();
        }
    }

    public String getName() {
        return name;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getId() {
        return id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, sightingid) VALUES (:name, :sightingId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate()
                    .getKey();
        }
    }
}
