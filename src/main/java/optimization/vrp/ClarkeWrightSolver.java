package optimization.vrp;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;

public class ClarkeWrightSolver implements VrpSolver {

    @Override
    public Result solve(InputData input) {
        List<Location> locations = input.getLocations();
        Location warehouse = input.getWarehouse();

        List<Vehicle> vehicles = Lists.newArrayListWithExpectedSize(locations.size());
        int capacity = input.getVehicleCapacity();
        for (int i = 0; i < locations.size(); i++) {
            Vehicle vehicle = new Vehicle(i, capacity, warehouse);
            vehicle.addLocation(locations.get(i));
            vehicles.add(vehicle);
        }

        List<LocationPair> savings = Lists.newArrayListWithCapacity(locations.size() * locations.size() / 2 + 1);
        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                savings.add(new LocationPair(warehouse, locations.get(i), locations.get(j)));
            }
        }

        Collections.sort(savings);

        for (LocationPair saving : savings) {
            Vehicle firstVehicle = saving.getFirstLocation().getVehicle();
            Vehicle secondVehicle = saving.getSecondLocation().getVehicle();

            if (firstVehicle.equals(secondVehicle)) {
                continue;
            }

            if (firstVehicle.canMergeWith(secondVehicle)) {
                firstVehicle.mergeWith(secondVehicle, saving);
            }
        }

        List<Vehicle> result = Lists.newArrayList();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isUsed()) {
                result.add(vehicle);
            }
        }

        while (result.size() < input.getNumberOfVehicles()) {
            result.add(new Vehicle(-1, capacity, warehouse));
        }

        return new Result(input, result, false);
    }

    static class LocationPair implements Comparable<LocationPair> {
        private final Location warehouse;
        private final Location first;
        private final Location second;

        public LocationPair(Location warehouse, Location first, Location second) {
            this.warehouse = Validate.notNull(warehouse);
            this.first = Validate.notNull(first);
            this.second = Validate.notNull(second);
        }

        public Location getFirstLocation() {
            return first;
        }

        public Location getSecondLocation() {
            return second;
        }

        public double calcSaving() {
            return warehouse.dist(first) + warehouse.dist(second) - first.dist(second);
        }

        @Override
        public int compareTo(LocationPair that) {
            return Double.compare(that.calcSaving(), this.calcSaving());
        }

        @Override
        public String toString() {
            return "[" + first.getIndex() + ", " + second.getIndex() + ": savings: " + calcSaving() + "]";
        }

    }
}
