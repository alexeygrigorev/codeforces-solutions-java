package optimization.vrp;

public class Location {

    private final int index;
    private final int demand;
    private final double x;
    private final double y;

    private Vehicle vehicle;

    public Location(int index, int demand, double x, double y) {
        this.index = index;
        this.demand = demand;
        this.x = x;
        this.y = y;
    }

    public double squareDist(Location other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return dx * dx + dy * dy;
    }

    public double dist(Location other) {
        return Math.sqrt(squareDist(other));
    }

    public int getIndex() {
        return index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDemand() {
        return demand;
    }

    public void assignTo(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean hasAssignedVehicle() {
        return vehicle != null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "Location [" + index + ", d=" + demand + ", (" + x + ", " + y + ")]";
    }

}