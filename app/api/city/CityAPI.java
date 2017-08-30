package api.city;

import model.City;

import java.util.List;

public interface CityAPI {

    List<City> all();
    City byId(Integer id);
    City update(City city);
    boolean delete(Integer id);
    City create(City City);
}
