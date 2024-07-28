package playground.data.userDataContext.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.data.userDataContext.repository.AddressRepository;
import playground.data.userDataContext.repository.ContactRepository;
import playground.data.userDataContext.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class UserContextController {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    @GetMapping("/users")
    public ResponseEntity<?> products(){
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/contacts")
    public ResponseEntity<?> variants(){
        return ResponseEntity.ok(contactRepository.findAll());
    }
    @GetMapping("/addresses")
    public ResponseEntity<?> tags(){
        return ResponseEntity.ok(addressRepository.findAll());
    }
}
