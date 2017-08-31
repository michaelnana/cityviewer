package controllers.city;

import api.city.CityFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.City;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import api.city.CityAPI;

public class CityController extends Controller {
    CityAPI cityAPI;

    public CityController() {
        cityAPI = new CityFacade();
    }

    public CityController(CityAPI api) {
        cityAPI = api;
    }

    public Result allCities() {
        return ok(Json.toJson(cityAPI.all()));
    }

    public Result city(String cityId) {
        if (request().accepts("text/html")) {
            return ok(views.html.city.render());
        } else {
            return ok(Json.toJson(cityAPI.byId(Integer.parseInt(cityId))));
        }
    }

    public Result updateCity(String cityId) {
        DynamicForm form = Form.form().bindFromRequest();
        String cityName = form.get("name");
        String latitude = form.get("latitude");
        String longitude = form.get("longitude");
        City cityToUpdate = new City();
        cityToUpdate.setId(Integer.parseInt(cityId));
        cityToUpdate.setName(cityName);
        cityToUpdate.setLatitude(Double.parseDouble(latitude));
        cityToUpdate.setLongitude(Double.parseDouble(longitude));
        return ok(Json.toJson(cityAPI.update(cityToUpdate)));
    }

    public Result deleteCity(String cityId) {
        ObjectNode json = Json.newObject();
        if(cityAPI.delete(Integer.parseInt(cityId))) {
            json.put("successfuldeletion", true);
        } else {
            json.put("successfuldeletion", false);
        }
        return ok(json);
    }

    public Result createCity() {
        DynamicForm form = Form.form().bindFromRequest();
        String cityName = form.get("name");
        String latitude = form.get("latitude");
        String longitude = form.get("longitude");
        City cityToCreate = new City();
        cityToCreate.setName(cityName);
        cityToCreate.setLatitude(Double.parseDouble(latitude));
        cityToCreate.setLongitude(Double.parseDouble(longitude));
        return ok(Json.toJson(cityAPI.create(cityToCreate)));
    }
}
