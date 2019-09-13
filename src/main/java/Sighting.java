public class Sighting {

        private String location;
        private String rangerName;

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
}
