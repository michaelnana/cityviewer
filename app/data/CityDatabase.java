package data;

import model.City;

import java.util.ArrayList;

public class CityDatabase {
    public ArrayList<City> cityList;

    private static CityDatabase instance;

    private CityDatabase() {
        cityList = new ArrayList<City>();
        createCity(1,"Tokyo",35.685,139.751389);
        createCity(2,"London",51.514125,-0.093689);
        createCity(3,"Toronto",43.666667,-79.416667);
        createCity(4,"Vancouver",49.25,-123.133333);
        createCity(5,"Los Angeles",34.0522222,-118.2427778);
        createCity(6,"Chicago",41.85,-87.65);
        createCity(7,"Moscow",55.752222,37.615556);
        createCity(8,"Beijing",29.346443,116.198733);
        createCity(9,"Paris",48.866667,2.333333);
        createCity(10,"Singapore",1.2930556,103.8558333);
        createCity(11,"Montreal",45.5,-73.583333);
        createCity(12,"Sydney",-33.861481,151.205475);
        createCity(13,"Bangkok",-6.6952,111.5409);
        createCity(14,"Guadalajara",32.544167,-114.864722);
        createCity(15,"Miami",25.7738889,-80.1938889);
    }

    public synchronized static CityDatabase getInstance() {
        if(instance == null) {
            instance = new CityDatabase();
        }
        return instance;
    }

    private void createCity(int id, String name, double latitude, double longitude) {
        City city = new City();

        city.setId(id);
        city.setName(name);
        city.setLatitude(latitude);
        city.setLongitude(longitude);

        cityList.add(city);
    }
}
