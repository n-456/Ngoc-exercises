package customers_backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    Customer getAllCustomer(@PathVariable Long id) {
        int i = Math.toIntExact(id);
        return ConnectDB.getCustomer(i);
    }

    @GetMapping
    List<Customer> getCustomer() {
        return ConnectDB.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<Customer> createUser (@RequestBody Customer request) {

        ConnectDB.insertCustomer(request.getName(),request.getPhone(),request.getEmail());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateUser(@PathVariable Long id, @RequestBody Customer request) {

        int i = Math.toIntExact(id);
        return ResponseEntity.ok(
                ConnectDB.updateCustomer(i,request.getName(),request.getPhone(),request.getEmail())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        int i = Math.toIntExact(id);
        ConnectDB.deleteCustomer(i);
        return ResponseEntity.noContent().build();
    }
}
