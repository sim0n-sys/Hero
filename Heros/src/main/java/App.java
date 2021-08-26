

import static spark.Spark.*;

import models.Heroes;
import models.Squads;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;
import java.util.HashMap;

public class App {


    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        }else {
            port = 4567;
        }
        port(port);

        staticFileLocation("/public");

        //Get: View the Home page
        get("/", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            return new ModelAndView(user, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //Get: Add squad form
        get("/squads/new", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            return new ModelAndView(user, "Squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Post: Submit data from the add squad form
        post("/squads/new", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            String squadName = request.queryParams("squadName");
            String theme = request.queryParams("theme");
            String url = request.queryParams("url");
            int numberOf = Integer.parseInt(request.queryParams("max"));
            Squads userSquad = new Squads(squadName, theme, url, numberOf);
            user.put("userSquad", Squads.getAllSquads());
            return new ModelAndView(user, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //Get: View all squads
        get("/squads", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            user.put("userSquad", Squads.getAllSquads());
            return new ModelAndView(user, "Squads.hbs");
        }, new HandlebarsTemplateEngine());

        //Get: View squad-details e.g heroes
        get("/squads/:id", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            int theId = Integer.parseInt(request.params("id"));
            Squads pageId = Squads.squadWithId(theId);
            user.put("squadHeroes", pageId);
            return new ModelAndView(user, "Heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //Get: Add hero form
        get("/squads/:id/new", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            int theId = Integer.parseInt(request.params("id"));
            Squads heroes = Squads.squadWithId(theId);
            user.put("heroes", heroes);
            user.put("squads", Squads.getAllSquads());
            return new ModelAndView(user, "Hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Get: View the added heroes
        get("/squads/:id/heroes", (request, response) -> {
            Map<String, Object> user  = new HashMap<>();
            int theId = Integer.parseInt(request.params("id"));
            Squads squad = Squads.squadWithId(theId);
            user.put("allHeroes", squad.getHeroesInSquad());
            return new ModelAndView(user, "View-heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //Post: Submit add hero form
        post("/squads/:id/new", (request, response) -> {
            Map<String, Object> user = new HashMap<>();
            int theId = Integer.parseInt(request.params("id"));
            Squads squad = Squads.squadWithId(theId);
            String heroName = request.queryParams("hero");
            String superPowers = request.queryParams("superPowers");
            String role = request.queryParams("role");
            Heroes newHero = new Heroes(heroName, superPowers, role);
            if (squad.getMaxHeroes() <= squad.getHeroesInSquad().size()){
                String sorry = "Sorry, this squad is full";
                user.put("full", sorry);
            }else{
                squad.addHero(newHero);
                user.put("heroes", squad.getHeroesInSquad());
            }

            return new ModelAndView(user, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //Get: Delete a squad
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> user = new HashMap<>();
            int theId = Integer.parseInt(req.params("id"));
            Squads pageId = Squads.squadWithId(theId);
            pageId.deleteSquad();
            return new ModelAndView(user, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}