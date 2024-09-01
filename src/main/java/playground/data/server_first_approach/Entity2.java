package playground.data.server_first_approach;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Entity @Getter @Setter
public class Entity2 extends PartitionedEntity  {

    @Id
    private int id;
    private String name;

    @Override
    public List<String> getAllPartitionedSubTableCreateQueries() {
        // Provide the create table queries for partitions
        return Arrays.asList(
                "CREATE TABLE IF NOT EXISTS example_partition1 (id INT, name VARCHAR(255))",
                "CREATE TABLE IF NOT EXISTS example_partition2 (id INT, name VARCHAR(255))"
        );
    }
}