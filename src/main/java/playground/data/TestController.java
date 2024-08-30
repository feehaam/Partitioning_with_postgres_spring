package playground.data;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {
    private final ProductRepo productRepo;
    @GetMapping
    public Object analyse() {
        return productRepo.findAll();
    }
}
