package optimization.wl;

import java.util.List;
import java.util.Set;

import optimization.wl.InputData.Customer;
import optimization.wl.InputData.Warehouse;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Here we suppose that warehouses don't have capacity constraints
 * 
 * @author Grigorev Alexey
 */
public class GreedyUncapacitatedSolver implements WlSolver {

    @Override
    public Result solve(InputData input) {
        Set<Warehouse> openWarehouses = Sets.newHashSet(input.getWarehouses());
        List<Customer> customers = input.getCustomers();

        double bestCost = calcCost(openWarehouses, customers);

        while (atLeastTwoItems(openWarehouses)) {
            boolean changed = false;
            List<Warehouse> it = Lists.newArrayList(openWarehouses);
            for (Warehouse warehouse : it) {
                openWarehouses.remove(warehouse);
                double cost = calcCost(openWarehouses, customers);

                if (cost > bestCost) {
                    openWarehouses.add(warehouse);
                } else {
                    bestCost = cost;
                    changed = true;
                }

                if (!openWarehouses.isEmpty()) {
                    break;
                }
            }

            if (!changed) {
                break;
            }
        }

        int m = customers.size();
        int[] result = new int[m];

        for (Customer customer : customers) {
            Warehouse best = customer.findCheapest(openWarehouses);
            best.assign(customer);
            result[customer.getNumber()] = best.getNumber();
        }

        return new Result(input, result, false);
    }

    private boolean atLeastTwoItems(Set<Warehouse> openWarehouses) {
        return openWarehouses.size() >= 2;
    }

    private double calcCost(Set<Warehouse> openWarehouses, List<Customer> customers) {
        double cost = 0.0;

        for (Warehouse warehouse : openWarehouses) {
            cost = cost + warehouse.getCost();
        }

        for (Customer customer : customers) {
            Warehouse cheapest = customer.findCheapest(openWarehouses);
            cost = cost + customer.getCost(cheapest);
        }

        return cost;
    }

}
