package optimization.vrp;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import optimization.vrp.ClarkeWrightSolver.LocationPair;
import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;

public class Vehicle {

    private final int index;
    private final Location warehouse;
    private final List<Location> locations = Lists.newLinkedList();

    private int capacity;
    private int usedCapacity = 0;

    public Vehicle(int index, int capacity, Location warehouse) {
        this.index = index;
        this.warehouse = warehouse;
        this.capacity = capacity;
    }

    public boolean canSatisfy(Location location) {
        return capacity >= location.getDemand();
    }

    public void addLocation(Location location) {
        Validate.isTrue(canSatisfy(location), "can't satisfy the demand of location %d", location.getIndex());
        location.assignTo(this);
        locations.add(location);
        capacity = capacity - location.getDemand();
        usedCapacity = usedCapacity + location.getDemand();
    }

    public double calcCost() {
        if (locations.isEmpty()) {
            return 0.0;
        }

        double cost = 0.0;
        Iterator<Location> iterator = getLocations().iterator();
        Location first = iterator.next();
        Location second = iterator.next();

        cost = cost + first.dist(second);

        while (iterator.hasNext()) {
            Location next = iterator.next();
            first = second;
            second = next;
            cost = cost + first.dist(second);
        }

        return cost;
    }

    public boolean canMergeWith(Vehicle other) {
        if (other.equals(this)) {
            return false;
        }

        return other.usedCapacity < this.capacity;
    }

    public boolean mergeWith(Vehicle other, LocationPair mergingEdge) {
        Location firstLocation = mergingEdge.getFirstLocation();
        boolean isFirstAtTheBeginning = this.isFirst(firstLocation);
        boolean isFirstAtTheEnd = this.isLast(firstLocation);

        Location secondLocation = mergingEdge.getSecondLocation();
        boolean isSecondAtTheBeginning = other.isFirst(secondLocation);
        boolean isSecondAtTheEnd = other.isLast(secondLocation);

        if (isFirstAtTheEnd && isSecondAtTheBeginning) {
            return addOthers(other);
        }

        if (isFirstAtTheEnd && isSecondAtTheEnd) {
            other.reverseLocations();
            return addOthers(other);
        }

        if (isFirstAtTheBeginning && isSecondAtTheBeginning) {
            this.reverseLocations();
            return addOthers(other);
        }

        if (isFirstAtTheBeginning && isSecondAtTheEnd) {
            this.reverseLocations();
            other.reverseLocations();
            return addOthers(other);
        }

        return false;
    }

    private boolean addOthers(Vehicle other) {
        for (Location location : other.locations) {
            this.addLocation(location);
        }
        other.clear();
        return true;
    }

    private void reverseLocations() {
        Collections.reverse(locations);
    }

    private void clear() {
        capacity = capacity + usedCapacity;
        usedCapacity = 0;
        locations.clear();
    }

    private boolean isFirst(Location location) {
        return locations.get(0).equals(location);
    }

    private boolean isLast(Location location) {
        return locations.get(locations.size() - 1).equals(location);
    }

    public List<Location> getLocations() {
        List<Location> result = Lists.newLinkedList();
        result.add(warehouse);
        result.addAll(locations);
        result.add(warehouse);
        return result;
    }

    public List<Location> getLocationsWithoutDepot() {
        return locations;
    }

    public boolean isUsed() {
        return !locations.isEmpty();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Vehicle) {
            Vehicle that = (Vehicle) obj;
            return this.index == that.index;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public String toString() {
        return "Vehicle [" + index + ", c=" + capacity + "; " + locations + "]";
    }



}
