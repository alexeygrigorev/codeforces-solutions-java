package optimization.wl;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import optimization.wl.InputData.Customer;
import optimization.wl.InputData.Warehouse;

import org.apache.commons.lang3.Validate;

/**
 * For each customer c,
 * 
 * <ul>
 * <li>find the cheapest warehouse w* that can satisfy the demand</li>
 * <li>assign c to w*</li>
 * </ul>
 * 
 * @author Grigorev Alexey
 */
public class GreedySolver implements WlSolver {

    @Override
    public Result solve(InputData input) {
        List<Customer> customers = input.getCustomers();
        List<Warehouse> warehouses = input.getWarehouses();

        int m = customers.size();
        int[] result = new int[m];

        for (Customer customer : customers) {
            Queue<Warehouse> q = pq(m, warehouses, customer);
            boolean found = false;
            while (!q.isEmpty()) {
                Warehouse best = q.poll();
                if (best.canSatisfy(customer)) {
                    best.assign(customer);
                    result[customer.getNumber()] = best.getNumber();
                    found = true;
                    break;
                }
            }
            Validate.isTrue(found);
        }

        return new Result(input, result, false);
    }

    private static Queue<Warehouse> pq(int m, List<Warehouse> warehouses, Customer customer) {
        Queue<Warehouse> q = new PriorityQueue<Warehouse>(m, new GreedyWarehouseComparator(customer));
        q.addAll(warehouses);
        return q;
    }

    static class GreedyWarehouseComparator implements Comparator<Warehouse> {

        private final Customer customer;

        public GreedyWarehouseComparator(Customer customer) {
            this.customer = customer;
        }

        @Override
        public int compare(Warehouse w1, Warehouse w2) {
            double cost1 = customer.getCost(w1.getNumber());
            double cost2 = customer.getCost(w2.getNumber());
            return Double.compare(cost1, cost2);
        }

    }

}
