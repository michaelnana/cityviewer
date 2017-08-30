package data;

import model.City;

import java.util.List;
import java.util.Random;

public class CityDAO {

    public City findById(Integer id) {
        CityDatabase cityDatabase = CityDatabase.getInstance();

        for(City city : cityDatabase.cityList) {
            if(city.getId().equals(id))
                return city;
        }

        return null;
    }

    public void deleteById(Integer id) {
        CityDatabase cityDatabase = CityDatabase.getInstance();
        City city = this.findById(id);
        cityDatabase.cityList.remove(city);
    }

    public List<City> listAll() {
        return CityDatabase.getInstance().cityList;
    }

    public Integer create(City city) {
        CityDatabase cityDatabase = CityDatabase.getInstance();
        Random random = new Random();
        Integer id = java.lang.Math.abs(random.nextInt());

        city.setId(id);
        cityDatabase.cityList.add(city);

        return id;
    }

    public void update(City updatedCity) {
        City city = this.findById(updatedCity.getId());

        if(city == null)
            return;

        city.setName(updatedCity.getName());
        city.setLatitude(updatedCity.getLatitude());
        city.setLongitude(updatedCity.getLongitude());
    }
}
