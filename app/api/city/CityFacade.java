package api.city;

import data.CityDAO;
import model.City;

import java.util.List;

public class CityFacade implements CityAPI {
    CityDAO cityDAO;

    public CityFacade() {
        cityDAO = new CityDAO();
    }

    public CityFacade(CityDAO dao) {
        cityDAO = dao;
    }

    @Override
    public List<City> all() {
        return cityDAO.listAll();
    }

    @Override
    public City byId(Integer id) {
        return cityDAO.findById(id);
    }

    @Override
    public City update(City city) {
        cityDAO.update(city);
        return city;
    }

    @Override
    public boolean delete(Integer id) {
        cityDAO.deleteById(id);
        return true;
    }

    @Override
    public City create(City city) {
        cityDAO.create(city);
        return city;
    }
}
