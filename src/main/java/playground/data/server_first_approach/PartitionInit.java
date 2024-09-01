package playground.data.server_first_approach;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PartitionInit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // SQL statements for table creation
    private static final String CREATE_TABLE_1 = "CREATE TABLE IF NOT EXISTS table6 (id INT PRIMARY KEY, name VARCHAR(255))";
    private static final String CREATE_TABLE_2 = "CREATE TABLE IF NOT EXISTS table7 (id INT PRIMARY KEY, description VARCHAR(255))";

    public String getQ(){
        return CREATE_TABLE_1;
    }

    @PostConstruct
    public void init() {
        System.out.println("---------------------------------------");
        jdbcTemplate.execute(getQ());
        jdbcTemplate.execute(CREATE_TABLE_2);
    }
}