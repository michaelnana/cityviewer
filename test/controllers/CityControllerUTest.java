package controllers;

import api.city.CityAPI;
import api.city.CityFacade;
import controllers.city.CityController;
import model.City;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.test.Helpers.contentAsString;

public class CityControllerUTest extends WithApplication {
    CityAPI mockedAPI = mock(CityFacade.class);
    CityController controller = new CityController(mockedAPI);

    public static Application app;
    private final Http.Request request = mock(Http.Request.class);

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication();
        Helpers.start(app);
    }

    @Before
    public void setUp() throws Exception {
        Map<String, String> userData = new HashMap<String, String>();
        Map<String, Object> argData = Collections.emptyMap();
        Long id = 2L;
        play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        Http.Context context = new Http.Context(id, header, request, userData, userData, argData);
        Http.Context.current.set(context);
    }

    private List<City> mockCityList () {
        List<City> cities = new ArrayList<City>();
        City city1 = new City();
        city1.setName("City 1");
        cities.add(city1);
        City city2 = new City();
        city2.setName("City 2");
        cities.add(city2);
        return cities;
    }

    @Test
    public void allCities_returns_an_ok_result_with_all_cities_in_json () {
        when(mockedAPI.all()).thenReturn(mockCityList());
        Result allCitiesResult = controller.allCities();
        assertEquals(allCitiesResult.status(), 200);
        assertEquals(contentAsString(allCitiesResult), "[{\"id\":null,\"name\":\"City 1\",\"latitude\":null,\"longitude\":null},{\"id\":null,\"name\":\"City 2\",\"latitude\":null,\"longitude\":null}]");
    }

    @Test
    public void city_returns_an_ok_result_with_the_city_that_matches_the_id_in_json () {
        when(mockedAPI.byId(1)).thenReturn(mockCityList().get(0));
        Result cityResult = controller.city("1");
        assertEquals(cityResult.status(), 200);
        assertEquals(contentAsString(cityResult), "{\"id\":null,\"name\":\"City 1\",\"latitude\":null,\"longitude\":null}");
    }

    @Test
    public void city_returns_a_not_found_result_if_the_id_passed_in_matches_no_city () {
        when(mockedAPI.byId(10)).thenReturn(null);
        Result cityResult = controller.city("10");
        assertEquals(cityResult.status(), 404);
    }

    @Test
    public void city_returns_an_internal_server_error_if_the_id_passed_in_is_not_an_integer () {
        Result cityResult = controller.city("bla");
        assertEquals(cityResult.status(), 500);
    }

    @Test
    public void city_returns_an_ok_result_with_the_company_page_on_a_city_that_matches_the_id_passed_in_if_header_is_html () {
        when(request.accepts("text/html")).thenReturn(true);
        Result cityResult = controller.city("1");
        assertEquals(cityResult.status(), 200);
        assertEquals(cityResult.contentType(), Optional.of("text/html"));
    }

    @Test
    public void updatedCity_returns_an_ok_result_with_the_updatedCity_on_successful_update () {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("name", "Tokyo");
        userData.put("latitude", "1.0");
        userData.put("longitude", "1.0");

        Http.RequestBuilder mockRequest = new Http.RequestBuilder().method("PUT")
                .bodyForm(userData);
        City cityToUpdate = mockCityList().get(0);
        when(mockedAPI.update(any())).thenReturn(cityToUpdate);
        when(request.body()).
                thenReturn(mockRequest.body());
        Result cityResult = controller.updateCity("1");
        assertEquals(cityResult.status(), 200);
        assertEquals(contentAsString(cityResult), "{\"id\":null,\"name\":\"City 1\",\"latitude\":null,\"longitude\":null}");
    }

    @Test
    public void updatedCity_returns_an_internal_server_error_if_the_id_passed_is_not_an_integer () {
        Result cityResult = controller.updateCity("bla");
        assertEquals(cityResult.status(), 500);
    }

    @Test
    public void delete_returns_an_ok_result_with_a_successful_deletion_if_city_is_successfully_deleted () {
        when(mockedAPI.delete(1)).thenReturn(true);
        Result cityResult = controller.deleteCity("1");
        assertEquals(cityResult.status(), 200);
        assertEquals(contentAsString(cityResult), "{\"successfuldeletion\":true}");
    }

    @Test
    public void delete_returns_an_ok_result_with_an_unsuccessful_deletion_if_city_is_not_successfully_deleted () {
        when(mockedAPI.delete(1)).thenReturn(false);
        Result cityResult = controller.deleteCity("1");
        assertEquals(cityResult.status(), 200);
        assertEquals(contentAsString(cityResult), "{\"successfuldeletion\":false}");
    }

    @Test
    public void delete_returns_an_an_internal_server_error_if_the_id_passed_is_not_an_integer () {
        Result cityResult = controller.deleteCity("bla");
        assertEquals(cityResult.status(), 500);
    }

    @Test
    public void createCity_returns_an_ok_result_with_the_persisted_created_city_on_successful_creation () {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("name", "New Tokyo");
        userData.put("latitude", "1.0");
        userData.put("longitude", "1.0");

        Http.RequestBuilder mockRequest = new Http.RequestBuilder().method("POST")
                .bodyForm(userData);
        City cityToCreate = mockCityList().get(0);
        when(mockedAPI.create(any())).thenReturn(cityToCreate);
        when(request.body()).
                thenReturn(mockRequest.body());
        Result cityResult = controller.createCity();
        assertEquals(cityResult.status(), 200);
        assertEquals(contentAsString(cityResult), "{\"id\":null,\"name\":\"City 1\",\"latitude\":null,\"longitude\":null}");
    }
}
