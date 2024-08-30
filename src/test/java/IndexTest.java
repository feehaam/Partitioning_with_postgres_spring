import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import playground.data.Product;
import playground.data.ProductRepo;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IndexTest {
    @Autowired
    private ProductRepo productRepo;
    @Test
    public void getAllProduct(){
        List<Product> products = productRepo.findAll();
        System.out.println(products.toString());
    }
}
