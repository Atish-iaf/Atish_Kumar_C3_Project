import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    // Refactor method to add a restaurant with menu items
    private void addRestaurantWithMenu(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        restaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @BeforeEach
    public void setUp() {
        // Reset the restaurant before each test
        restaurant = null;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //Restaurant restaurantMock = Mockito.mock(Restaurant.class);
        LocalTime expectedTime = LocalTime.parse("11:00:00");
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        Restaurant restaurantMock = Mockito.spy(restaurant);
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(expectedTime);
        assertTrue(restaurantMock.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //Restaurant restaurantMock = Mockito.mock(Restaurant.class);
        LocalTime expectedTime = LocalTime.parse("10:00:00");
        //Mockito.when(restaurantMock.getCurrentTime()).thenReturn(expectedTime);
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("12:00:00"));
        Restaurant restaurantMock = Mockito.spy(restaurant);
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(expectedTime);
        assertFalse(restaurantMock.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<<<<<<<<<<<<<<<CALCULATE ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Failing test case added for calculateOrderValue method
    @Test
    @DisplayName("Calculate order value for items of a restaurant")
    public void calculate_order_value_for_items() throws restaurantNotFoundException {
    addRestaurantWithMenu("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));

    // Assuming "Sweet corn soup" and "Vegetable lasagne" are items in the menu
    double orderValue = restaurant.calculateOrderValue("Sweet corn soup", "Vegetable lasagne");

    assertEquals(388.0, orderValue);
   }
    // <<<<<<<<<<<<<<<<<<<<<CALCULATE ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>
}
