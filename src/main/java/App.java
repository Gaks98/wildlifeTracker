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



//        post("/endangered_sighting", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String rangerName = request.queryParams("rangerName");
////            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
//            String location = request.queryParams("latLong");
//            Sighting sighting = new Sighting(location, rangerName);
//            sighting.save();
//            model.put("sighting", sighting);
//            model.put("animals", EndangeredAnimal.all());
////            String animal = EndangeredAnimal.find(animalIdSelected).getName();
////            model.put("animal", animal);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //non-endangered
//        post("/sighting", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String rangerName = request.queryParams("rangerName");
////            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
//            String location = request.queryParams("location");
//            Sighting sighting = new Sighting(location, rangerName); // two parameters in mine but may be wrong
//            sighting.save();
//            model.put("sighting", sighting);
//            model.put("animals", Animal.all());
////            String animal = Animal.find(animalIdSelected).getName();
////            model.put("animal", animal);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());

//        get("/animal/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("animals", Animal.all());
//            model.put("endangeredAnimals", EndangeredAnimal.all());
//            return new ModelAndView(model, "animal-form.hbs");
//        }, new HandlebarsTemplateEngine());

//        post("/animal/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            boolean endangered = request.queryParamsValues("endangered")!=null;
//            if (endangered) {
//                String name = request.queryParams("name");
//                String health = request.queryParams("health");
//                String age = request.queryParams("age");
//                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
//                endangeredAnimal.save();
//                String rangerName = request.queryParams("rangerName");
//                String location = request.queryParams("location");
//                Sighting sighting = new Sighting(location,rangerName);
//                sighting.save();
//                model.put("animals", Animal.all());
//                model.put("endangeredAnimals", EndangeredAnimal.all());
//            } else {
//                String name = request.queryParams("name");
//                int sightingId = Integer.parseInt(request.queryParams("sightingId"));
//                Animal animal = new Animal(name,sightingId); //missing parameter sightingId
//                animal.save();
//                model.put("sightingId",sightingId);
//                model.put("animals", Animal.all());
//                model.put("endangeredAnimals", EndangeredAnimal.all());
//            }
//            response.redirect("/");
//            return null;
//        });

//        get("/animal/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            Animal animal = Animal.find(Integer.parseInt(request.params("id")));
//            model.put("animal", animal);
//
//            return new ModelAndView(model, "animal.hbs");
//        }, new HandlebarsTemplateEngine());

//        get("/endangered_animal/:id", (request, response) -> { //
//            Map<String, Object> model = new HashMap<String, Object>();
//            String name = request.queryParams("name");
//            String health = request.queryParams("health");
//            String age = request.queryParams("age");
//            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
//            endangeredAnimal.save();
//            String rangerName = request.queryParams("rangerName");
//            String location = request.queryParams("location");
//            Sighting sighting = new Sighting(location,rangerName);
//            sighting.save();
//            endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
//            model.put("name",name);
//            model.put("health",health);
//            model.put("age",age);
//            model.put("rangerName",rangerName);
//            model.put("location",location);
//            model.put("endangeredAnimal", endangeredAnimal);
//            return new ModelAndView(model, "endangered_animal.hbs");
//        }, new HandlebarsTemplateEngine());


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

//      get("/endangeredAnimal-form", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//           return new ModelAndView(model, "endangeredAnimal-form.hbs");
//           }, new HandlebarsTemplateEngine());
    }


}