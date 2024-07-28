package playground.jpa.advanced;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import playground.jpa.advanced.entity.manager.Car;
import playground.jpa.advanced.entity.manager.CarRepository;

@SpringBootTest
public class CarServiceTest {
    @Autowired
    CarRepository carRepository;

    @Test
    void testCar(){
        Car car = new Car(2L, "dummy brand", "dummy model", 1899);
        carRepository.create(car);
        var found1 = carRepository.getCarById(1L);
        var found2 = carRepository.getCarById(2L);
        car.setModel("dummy model - updated");
        found1 = carRepository.getCarById(1L);
    }
}
