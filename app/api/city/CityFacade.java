package api.city;

import data.CityDAO;
import model.City;

import java.util.List;

public class CityFacade implements CityAPI {

    @Override
    public List<City> all() {
        return new CityDAO().listAll();
    }

    @Override
    public City byId(Integer id) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public City create(City City) {
        return null;
    }
}
