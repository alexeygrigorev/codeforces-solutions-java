package optimization.vrp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class NaiveGreedySolver implements VrpSolver {

    @Override
    public Result solve(InputData input) {
        Location warehouse = input.getWarehouse();

        Set<Location> locations = Sets.newLinkedHashSet(input.getLocations());
        int numberOfVehicles = input.getNumberOfVehicles();
        int capacity = input.getVehicleCapacity();

        List<Vehicle> vehicles = Lists.newLinkedList();

        for (int i = 0; i < numberOfVehicles; i++) {
            Vehicle vehicle = new Vehicle(i, capacity, warehouse);
            Location currentLocation = warehouse;

            while (true) {
                Location currentBest = findClosestSatisfiableLocation(vehicle, currentLocation, locations);

                if (currentBest == null) {
                    break;
                }

                locations.remove(currentBest);
                vehicle.addLocation(currentBest);
                currentLocation = currentBest;

            }

            vehicles.add(vehicle);
        }

        // sometimes it won't validate!
        Validate.isTrue(locations.isEmpty());

        return new Result(input, vehicles, false);
    }

    private Location findClosestSatisfiableLocation(Vehicle vehicle, Location currentLocation, Set<Location> locations) {
        if (locations.isEmpty()) {
            return null;
        }

        Iterator<Location> iterator = locations.iterator();
        Location best = null;
        double bestDist = Double.POSITIVE_INFINITY;

        while (iterator.hasNext()) {
            Location next = iterator.next();
            if (!vehicle.canSatisfy(next)) {
                continue;
            }

            double dist = currentLocation.squareDist(next);
            if (dist < bestDist) {
                bestDist = dist;
                best = next;
            }

        }

        return best;
    }
}
