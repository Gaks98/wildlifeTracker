import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("allAnimals", Animal.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
//            List<EndangeredAnimal> endangeredAnimals = new ArrayList<>();
//            model.put("sightings", Sighting.all());
//            for(EndangeredAnimal animal:EndangeredAnimal.all()){
//                System.out.println(animal.name);
//                model.put("endangeredAnimals", animal);
//            }
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //fresh start

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String nameOfAnimal = request.queryParams("nameOfAnimal");
            int sightingId = Integer.parseInt(request.queryParams("sightingId"));
            String rangerName = request.queryParams("rangerName");
            String location = request.queryParams("location");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            Animal animal = new Animal(nameOfAnimal,sightingId); //missing parameter sightingId
            animal.save();
            Sighting sighting = new Sighting(location,rangerName);
            sighting.save();
            model.put("age",age);
            model.put("health",health);
            model.put("nameOfAnimal",nameOfAnimal);
            model.put("sightingId",sightingId);
            model.put("rangerName",rangerName);
            model.put("location",location);
//            System.out.println(animal.getName());
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangeredAnimal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangeredAnimal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangeredAnimal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String nameOfAnimal = request.queryParams("nameOfAnimal");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String rangerName = request.queryParams("rangerName");
            String location = request.queryParams("location");
            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(nameOfAnimal, health, age);
            endangeredAnimal.save();
            Sighting sighting = new Sighting(location,rangerName);
            sighting.save();
//            model.put("endangeredAnimal",endangeredAnimal);
            model.put("age",age);
            model.put("health",health);
            model.put("nameOfAnimal",nameOfAnimal);
            model.put("rangerName",rangerName);
            model.put("location",location);
            return new ModelAndView(model, "endangered_animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("allAnimals", Animal.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("allSightings", Sighting.all());
            return new ModelAndView(model, "display.hbs");
        }, new HandlebarsTemplateEngine());

    }


}