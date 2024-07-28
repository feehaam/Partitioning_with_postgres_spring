package playground.jpa.advanced.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.jpa.advanced.repository.PhotoRepository;
import playground.jpa.advanced.repository.ProductRepository;
import playground.jpa.advanced.repository.TagRepository;
import playground.jpa.advanced.repository.VariantRepository;

@RestController
@RequiredArgsConstructor
public class GetAllController {

    private final ProductRepository productsRepository;
    private final VariantRepository variantRepository;
    private final PhotoRepository photoRepository;
    private final TagRepository tagRepository;

    @GetMapping("/products")
    public ResponseEntity<?> products(){
        return ResponseEntity.ok(productsRepository.findAll());
    }
    @GetMapping("/variants")
    public ResponseEntity<?> variants(){
        return ResponseEntity.ok(variantRepository.findAll());
    }
    @GetMapping("/tags")
    public ResponseEntity<?> tags(){
        return ResponseEntity.ok(tagRepository.findAll());
    }
    @GetMapping("/photos")
    public ResponseEntity<?> photos(){
        return ResponseEntity.ok(photoRepository.findAll());
    }
}
