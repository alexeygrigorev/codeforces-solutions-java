package optimization.vrp;

import java.util.Arrays;
import java.util.List;

import optimization.vrp.ClarkeWrightSolver.LocationPair;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VehicleTest {

    Location warehouse = new Location(0, 0, 0.0, 0.0);

    @Test
    public void getLocations_containsWarehouses() {
        Vehicle vehicle = new Vehicle(1, 10, warehouse);
        assertEquals(vehicle.getLocations(), Arrays.asList(warehouse, warehouse));
    }

    @Test
    public void addLocation() {
        Vehicle vehicle = new Vehicle(1, 10, warehouse);

        Location location = new Location(1, 5, 1.0, 1.0);
        vehicle.addLocation(location);

        assertEquals(vehicle.getLocations(), Arrays.asList(warehouse, location, warehouse));
    }

    @Test
    public void canSatisfy() {
        Vehicle vehicle = new Vehicle(1, 10, warehouse);
        assertFalse(vehicle.canSatisfy(new Location(1, 50, 1.0, 1.0)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addLocation_unsatisfiable() {
        Vehicle vehicle = new Vehicle(1, 10, warehouse);
        vehicle.addLocation(new Location(1, 50, 1.0, 1.0));
    }

    @Test
    public void calcCost() {
        Vehicle vehicle = new Vehicle(1, 100, warehouse);

        vehicle.addLocation(new Location(1, 5, 0.0, 1.0));
        vehicle.addLocation(new Location(2, 5, 1.0, 1.0));
        vehicle.addLocation(new Location(3, 5, 1.0, 0.0));

        assertEquals(vehicle.calcCost(), 4.0, 0.01);
    }

    @Test
    public void merge_bothNormalOrder() {
        Vehicle vehicle1 = new Vehicle(1, 100, warehouse);
        Vehicle vehicle2 = new Vehicle(2, 100, warehouse);

        List<Location> locations = locationsInOrder();

        vehicle1.addLocation(locations.get(0));
        vehicle1.addLocation(locations.get(1));
        vehicle2.addLocation(locations.get(2));
        vehicle2.addLocation(locations.get(3));

        merge(vehicle1, vehicle2, locations);

        assertLocationsInOrder(vehicle1, locations);
    }

    private void merge(Vehicle vehicle1, Vehicle vehicle2, List<Location> locations) {
        LocationPair mergingEdge = mergingEdgeOnIndexes(locations, 1, 2);
        boolean result = vehicle1.mergeWith(vehicle2, mergingEdge);
        assertTrue(result);
    }

    private static void assertLocationsInOrder(Vehicle vehicle, List<Location> locations) {
        List<Location> actualLocations = vehicle.getLocationsWithoutDepot();
        assertEquals(actualLocations, locations);
    }

    private List<Location> locationsInOrder() {
        Location first = new Location(1, 5, -1.0, 0.0);
        Location second = new Location(2, 5, -1.0, -1.0);
        Location third = new Location(3, 5, 0.0, -1.0);
        Location forth = new Location(4, 5, 1.0, -1.0);
        return Arrays.asList(first, second, third, forth);
    }

    private LocationPair mergingEdgeOnIndexes(List<Location> locations, int i, int j) {
        return new LocationPair(warehouse, locations.get(i), locations.get(j));
    }

    @Test
    public void merge_secondWrongOrder() {
        Vehicle vehicle1 = new Vehicle(1, 100, warehouse);
        Vehicle vehicle2 = new Vehicle(2, 100, warehouse);

        List<Location> locations = locationsInOrder();

        vehicle1.addLocation(locations.get(0));
        vehicle1.addLocation(locations.get(1));
        vehicle2.addLocation(locations.get(3));
        vehicle2.addLocation(locations.get(2));

        merge(vehicle1, vehicle2, locations);
        assertLocationsInOrder(vehicle1, locations);
    }

    @Test
    public void merge_firstWrongOrder() {
        Vehicle vehicle1 = new Vehicle(1, 100, warehouse);
        Vehicle vehicle2 = new Vehicle(2, 100, warehouse);

        List<Location> locations = locationsInOrder();

        vehicle1.addLocation(locations.get(1));
        vehicle1.addLocation(locations.get(0));
        vehicle2.addLocation(locations.get(2));
        vehicle2.addLocation(locations.get(3));

        merge(vehicle1, vehicle2, locations);
        assertLocationsInOrder(vehicle1, locations);
    }

    @Test
    public void merge_bothWrongOrder() {
        Vehicle vehicle1 = new Vehicle(1, 100, warehouse);
        Vehicle vehicle2 = new Vehicle(2, 100, warehouse);

        List<Location> locations = locationsInOrder();

        vehicle1.addLocation(locations.get(1));
        vehicle1.addLocation(locations.get(0));
        vehicle2.addLocation(locations.get(3));
        vehicle2.addLocation(locations.get(2));

        merge(vehicle1, vehicle2, locations);
        assertLocationsInOrder(vehicle1, locations);
    }

    @Test
    public void merge_notPossible() {
        Vehicle vehicle1 = new Vehicle(1, 100, warehouse);
        Vehicle vehicle2 = new Vehicle(2, 100, warehouse);

        Location first = new Location(1, 5, -1.0, 0.0);
        Location second = new Location(2, 5, -1.0, -1.0);
        Location third = new Location(3, 5, 0.0, -1.0);
        Location forth = new Location(4, 5, 1.0, -1.0);
        Location fith = new Location(5, 5, 1.0, 0.0);
        Location sixth = new Location(6, 5, 1.0, 1.0);

        vehicle1.addLocation(first);
        vehicle1.addLocation(second);
        vehicle1.addLocation(third);
        vehicle2.addLocation(forth);
        vehicle2.addLocation(fith);
        vehicle2.addLocation(sixth);

        LocationPair mergingEdge = new LocationPair(warehouse, second, fith);
        boolean result = vehicle1.mergeWith(vehicle2, mergingEdge);
        assertFalse(result);
    }
}
