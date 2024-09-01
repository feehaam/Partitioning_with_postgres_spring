package playground.data.server_first_approach;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.MappedSuperclass;
import org.springframework.stereotype.Service;

import java.util.List;

@MappedSuperclass @Service
public abstract class PartitionedEntity {
    public abstract List<String> getAllPartitionedSubTableCreateQueries();

    @PostConstruct
    public void init(){
        int x = 10;
    }
}