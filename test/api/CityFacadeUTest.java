package api;

import api.city.CityFacade;
import data.CityDAO;
import model.City;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityFacadeUTest {
    CityFacade facade = new CityFacade();

    @Test
    public void all_returns_all_cities_in_db () {
        assertEquals(cityToCityName(facade.all()), asList("Tokyo", "London", "Toronto", "Vancouver", "Los Angeles",
                "Chicago", "Moscow", "Beijing", "Paris", "Singapore", "Montreal", "Sydney", "Bangkok", "Guadalajara",
                "Miami"));
    }

    @Test
    public void byId_takes_an_id_and_returns_a_city_that_matches_the_id () {
        assertEquals(facade.byId(1).getName(), "Tokyo");
        assertEquals((Double)facade.byId(1).getLatitude(), (Double)35.685);
        assertEquals((Double)facade.byId(1).getLongitude(), (Double)139.751389);
        assertEquals(facade.byId(9).getName(), "Paris");
        assertEquals((Double)facade.byId(9).getLatitude(), (Double)48.866667);
        assertEquals((Double)facade.byId(9).getLongitude(), (Double)2.333333);
    }

    @Test
    public void byId_returns_null_if_id_input_matches_no_city () {
        assertEquals(facade.byId(19), null);
    }

    @Test
    public void update_takes_a_city_to_update_and_persists_it () {
        CityDAO mockedCityDAO = mock(CityDAO.class);
        CityFacade updateFacade = new CityFacade(mockedCityDAO);
        City tokyo = new City();
        tokyo.setId(1);
        tokyo.setLatitude(35.685);
        tokyo.setLongitude(139.751389);

        when(mockedCityDAO.findById(1)).thenReturn(tokyo);
        City updatedCity = new City();
        updatedCity.setId(1);
        updatedCity.setName("New Tokyo");
        updatedCity.setLatitude(1.0);
        updatedCity.setLongitude(1.0);

        City cityOnUpdate = updateFacade.update(updatedCity);
        assertEquals(cityOnUpdate.getName(), "New Tokyo");
        assertEquals((Double)cityOnUpdate.getLatitude(), (Double)1.0);
        assertEquals((Double)cityOnUpdate.getLongitude(), (Double)1.0);
    }

    @Test
    public void delete_takes_an_id_and_deletes_the_city_that_matches_it () {
        facade.delete(1);
        assertEquals(facade.all().size(), 14);
    }

    @Test
    public void delete_does_nothing_if_id_passed_in_does_match_a_city () {
        facade.delete(19);
        assertEquals(facade.all().size(), 15);
    }

    private List<String> cityToCityName(List<City> cities) {
        List<String> cityNames = new ArrayList<String>();

        for (City city: cities) {
            cityNames.add(city.getName());
        }
        return cityNames;
    }
}
