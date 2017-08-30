package controllers.city;

import api.city.CityFacade;
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
        return ok();
    }

    public Result city() {
        return ok();
    }

    public Result updateCity() {
        return ok();
    }

    public Result deleteCity() {
        return ok();
    }

    public Result createCity() {
        return ok();
    }
}
