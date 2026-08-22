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
        return service.getById(id);
    }

    @GetMapping
    List<Customer> getCustomer() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Customer> createUser (@RequestBody Customer request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateUser(@PathVariable Long id, @RequestBody Customer request) {

        return ResponseEntity.ok(
                service.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
