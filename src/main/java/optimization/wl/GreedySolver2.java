package optimization.wl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import optimization.wl.InputData.Customer;
import optimization.wl.InputData.Warehouse;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GreedySolver2 implements WlSolver {

    @Override
    public Result solve(InputData input) {
        List<Warehouse> warehouses = sortWarehousesByPrice(input);

        Set<Customer> customers = Sets.newHashSet(input.getCustomers());
        int[] result = new int[customers.size()];

        for (Warehouse warehouse : warehouses) {
            List<Customer> satisfiable = satisfiableCustomersSortedByTravelCost(customers, warehouse);

            for (Customer customer : satisfiable) {
                if (warehouse.canSatisfy(customer)) {
                    warehouse.assign(customer);
                    customers.remove(customer);
                    result[customer.getNumber()] = warehouse.getNumber();
                }

                if (!warehouse.hasCapacityLeft()) {
                    break;
                }
            }

            if (customers.isEmpty()) {
                break;
            }
        }

        Validate.isTrue(customers.isEmpty());

        return new Result(input, result, false);
    }

    private List<Warehouse> sortWarehousesByPrice(InputData input) {
        List<Warehouse> warehouses = Lists.newArrayList(input.getWarehouses());
        Collections.sort(warehouses, new Comparator<Warehouse>() {
            @Override
            public int compare(Warehouse o1, Warehouse o2) {
                return Double.compare(o1.getCost(), o2.getCost());
            }
        });
        return warehouses;
    }

    private List<Customer> satisfiableCustomersSortedByTravelCost(Set<Customer> customers, final Warehouse warehouse) {
        List<Customer> satisfiable = Lists.newArrayList();

        for (Customer customer : customers) {
            if (warehouse.canSatisfy(customer)) {
                satisfiable.add(customer);
            }
        }

        Collections.sort(satisfiable, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                double cost1 = o1.getCost(warehouse.getNumber());
                double cost2 = o2.getCost(warehouse.getNumber());
                return Double.compare(cost1, cost2);
            }
        });
        return satisfiable;
    }

}
