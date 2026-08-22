package customers_backend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private final Map<Long, Customer> customers = new HashMap<>();

    public CustomerService() {
        customers.put(1L, new Customer("An", "0123", "an@com"));
        customers.put(2L, new Customer("Trang", "0124", "trang@vn"));
        customers.put(3L, new Customer("Tuyen", "0594", "tuyen@vn"));
        customers.put(4L, new Customer("Dung", "0745", "dung@com"));
        customers.put(5L, new Customer("Mai", "0369", "mai@vn"));
    }

    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }

    public Customer getById(Long id) {
        return customers.get(id);
    }

    public Customer create(Customer customer) {
        Long newId = (long) customers.size()+1;
        customers.put(newId, customer);
        return customer;
    }

    public Customer update(Long id, Customer customer) {
        customers.put(id, new Customer(customer));
        return customers.get(id);
    }

    public void delete(Long id) {
        customers.remove(id);
    }

    public void reset() {
        customers.clear();

        customers.put(1L, new Customer("An", "0123", "an@com"));
        customers.put(2L, new Customer("Trang", "0124", "trang@vn"));
        customers.put(3L, new Customer("Tuyen", "0594", "tuyen@vn"));
        customers.put(4L, new Customer("Dung", "0745", "dung@com"));
        customers.put(5L, new Customer("Mai", "0369", "mai@vn"));
    }
}
