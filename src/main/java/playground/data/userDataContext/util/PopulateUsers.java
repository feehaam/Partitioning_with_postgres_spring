package playground.data.userDataContext.util;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.data.userDataContext.entities.Address;
import playground.data.userDataContext.entities.User;
import playground.data.userDataContext.entities.Contact;
import playground.data.userDataContext.repository.AddressRepository;
import playground.data.userDataContext.repository.ContactRepository;
import playground.data.userDataContext.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PopulateUsers {
    private final Random random = new Random();
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @PostConstruct
    protected void loadProducts(){
        clear();
        load();
    }

    private void load() {
        List<User> generatedUsers = new ArrayList<>();
        for (int i=1; i<=10; i++) {
            generatedUsers.add(generateUser(i));
        }
        userRepository.saveAll(generatedUsers);
    }

    private void clear() {
        addressRepository.deleteAll();
        userRepository.deleteAll();
        contactRepository.deleteAll();
    }

    private User generateUser(int si) {
        return User.builder()
                .firstName("First-name-" + si)
                .lastName("last-name-" + si)
                .email("mail" + si + "@gmail.com")
                .addresses(generateAddress(si))
                .contacts(generateContact(si))
                .build();
    }

    private List<Address> generateAddress(int si) {
        List<Address> addresses = new ArrayList<>();
        double price = random.nextInt(200) * 10;
        for (int i=0; i<random.nextInt(4) + 1; i++) {
            addresses.add(
                    Address.builder()
                            .isPermanent(i == 0)
                            .city("City" + (si % 7))
                            .country("Country" + (si % 3))
                            .street("Country" + (si % 10))
                            .postCode("PC-" + (si % 10))
                            .detail("This is the detail of the particular user address.")
                            .build()
            );
        }
        return addresses;
    }

    private List<Contact> generateContact(int si) {
        List<Contact> contacts = new ArrayList<>();
        for (int i=0; i<random.nextInt(4) + 1; i++) {
            contacts.add(
                    Contact.builder()
                            .type("Type-" + (si%3))
                            .contactId("0123456789")
                            .isPrimary(i == 0)
                            .build()
            );
        }
        return contacts;
    }
}
