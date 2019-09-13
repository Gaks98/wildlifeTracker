public class Sighting {

        private String location;
        private String rangerName;

        public Sighting(String location, String rangerName) {
            this.location = location;
            this.rangerName = rangerName;
        }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }
}
