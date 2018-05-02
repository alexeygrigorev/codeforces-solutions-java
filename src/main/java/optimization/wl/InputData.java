package optimization.wl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.testng.internal.annotations.Sets;

public class InputData {

    private final List<Warehouse> warehouses;
    private final List<Customer> customers;

    public InputData(List<Warehouse> warehouses, List<Customer> customers) {
        this.warehouses = warehouses;
        this.customers = customers;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public static class Warehouse {

        private final int number;
        private final double cost;
        private int capacity;
        private Set<Customer> assigned = Sets.newLinkedHashSet();

        public Warehouse(int number, int capacity, double cost) {
            this.number = number;
            this.capacity = capacity;
            this.cost = cost;
        }

        public boolean canSatisfy(Customer customer) {
            return capacity >= customer.demand;
        }

        public void assign(Customer customer) {
            assigned.add(customer);
            capacity = capacity - customer.demand;
        }

        public double calcCost() {
            if (assigned.isEmpty()) {
                return 0.0;
            }
            double totalCost = cost;
            for (Customer customer : assigned) {
                totalCost = totalCost + customer.cost[number];
            }
            return totalCost;
        }

        public boolean hasCapacityLeft() {
            return capacity > 0;
        }

        public int getCapacity() {
            return capacity;
        }

        public double getCost() {
            return cost;
        }

        public int getNumber() {
            return number;
        }
    }

    public static class Customer {

        private final int number;
        private final int demand;
        private final double[] cost;

        public Customer(int number, int demand, double[] cost) {
            this.number = number;
            this.demand = demand;
            this.cost = cost;
        }

        public int getNumber() {
            return number;
        }

        public int getDemand() {
            return demand;
        }

        public double getCost(Warehouse warehouse) {
            return cost[warehouse.getNumber()];
        }

        public double getCost(int warehouse) {
            return cost[warehouse];
        }

        @Override
        public int hashCode() {
            return number;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Customer) {
                Customer other = (Customer) obj;
                return this.number == other.number;
            }
            return false;
        }

        public Warehouse findCheapest(Set<Warehouse> warehouses) {
            Validate.notEmpty(warehouses);

            Iterator<Warehouse> iterator = warehouses.iterator();
            Warehouse best = iterator.next();
            double bestPrice = cost[best.getNumber()];

            while (iterator.hasNext()) {

                Warehouse next = iterator.next();
                double price = cost[next.getNumber()];

                if (price < bestPrice) {
                    bestPrice = price;
                    best = next;
                }
            }

            return best;
        }
    }
}
