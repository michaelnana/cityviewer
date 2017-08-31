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
        try {
            if (request().accepts("text/html")) {
                return ok(views.html.city.render());
            } else {
                City retrievedCity = cityAPI.byId(Integer.parseInt(cityId));
                if (retrievedCity == null) {
                    return notFound();
                } else {
                    return ok(Json.toJson(retrievedCity));
                }
            }
        } catch (Exception e) {
            return internalServerError();
        }
    }

    public Result updateCity(String cityId) {
        try {
            City cityToUpdate = fromRequest(Form.form().bindFromRequest());
            cityToUpdate.setId(Integer.parseInt(cityId));
            City updatedCity = cityAPI.update(cityToUpdate);
            if (updatedCity == null) {
                return notFound();
            } else {
                return ok(Json.toJson(updatedCity));
            }
        } catch (Exception e) {
            return internalServerError();
        }
    }

    public Result deleteCity(String cityId) {
        try {
            ObjectNode json = Json.newObject();
            if (cityAPI.delete(Integer.parseInt(cityId))) {
                json.put("successfuldeletion", true);
            } else {
                json.put("successfuldeletion", false);
            }
            return ok(json);
        } catch (Exception e) {
            return internalServerError();
        }
    }

    public Result createCity() {
        try {
            City cityToCreate = fromRequest(Form.form().bindFromRequest());
            return ok(Json.toJson(cityAPI.create(cityToCreate)));
        } catch (Exception e) {
            return internalServerError();
        }
    }

    private City fromRequest(DynamicForm form) {
        String cityName = form.get("name");
        String latitude = form.get("latitude");
        String longitude = form.get("longitude");
        City city = new City();
        city.setName(cityName);
        city.setLatitude(Double.parseDouble(latitude));
        city.setLongitude(Double.parseDouble(longitude));
        return city;
    }
}
