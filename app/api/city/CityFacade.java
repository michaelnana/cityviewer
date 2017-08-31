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
        return new CityDAO().findById(id);
    }

    @Override
    public City update(City city) {
        new CityDAO().update(city);
        return city;
    }

    @Override
    public boolean delete(Integer id) {
        new CityDAO().deleteById(id);
        return true;
    }

    @Override
    public City create(City city) {
        new CityDAO().create(city);
        return city;
    }
}
