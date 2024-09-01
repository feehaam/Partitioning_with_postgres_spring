package playground.data.server_first_approach;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public abstract class MakePartition {
    abstract void runPartitionQuery();

    @PostConstruct
    void init(){
        runPartitionQuery();
    }
}
