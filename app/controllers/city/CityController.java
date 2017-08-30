package controllers.city;

import api.city.CityFacade;
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
        return ok();
    }

    public Result updateCity(String cityId) {
        return ok();
    }

    public Result deleteCity(String cityId) {
        return ok();
    }

    public Result createCity(String cityId) {
        return ok();
    }
}
