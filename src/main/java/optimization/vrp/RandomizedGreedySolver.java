package optimization.vrp;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RandomizedGreedySolver implements VrpSolver {

    @Override
    public Result solve(InputData input) {
        Location warehouse = input.getWarehouse();

        int numberOfVehicles = input.getNumberOfVehicles();
        int capacity = input.getVehicleCapacity();

        int trials = 100;

        double bestCost = Double.POSITIVE_INFINITY;
        Result best = null;

        while (trials > 0) {
            List<Location> rnd = Lists.newArrayList(input.getLocations());
            Collections.shuffle(rnd);

            List<Vehicle> vehicles = Lists.newLinkedList();
            Iterator<Location> iterator = rnd.iterator();
            for (int i = 0; i < numberOfVehicles; i++) {
                Vehicle vehicle = new Vehicle(i, capacity, warehouse);
                vehicle.addLocation(iterator.next());
                vehicles.add(vehicle);
            }

            Set<Location> locations = Sets.newLinkedHashSet();
            Iterators.addAll(locations, iterator);

            for (Vehicle vehicle : vehicles) {
                Location currentLocation = vehicle.getLocations().get(0);

                while (true) {
                    Location currentBest = findClosestSatisfiableLocation(vehicle, currentLocation, locations);

                    if (currentBest == null) {
                        break;
                    }

                    locations.remove(currentBest);
                    vehicle.addLocation(currentBest);
                    currentLocation = currentBest;
                }
            }

            // sometimes it won't validate!
            if (locations.isEmpty()) {
                Result result = new Result(input, vehicles, false);
                double cost = result.calcCost();

                if (bestCost > cost) {
                    best = result;
                    bestCost = cost;
                }
            }

            trials--;
        }

        Validate.isTrue(best != null, "didn't find any feasible solution");
        return best;
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
