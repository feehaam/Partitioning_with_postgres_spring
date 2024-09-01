package playground.data.server_first_approach;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBInit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // SQL statements for table creation
    private static final String CREATE_TABLE_1 = "CREATE TABLE IF NOT EXISTS table1 (id INT PRIMARY KEY, name VARCHAR(255))";
    private static final String CREATE_TABLE_2 = "CREATE TABLE IF NOT EXISTS table2 (id INT PRIMARY KEY, description VARCHAR(255))";

    @PostConstruct
    public void init() {
        System.out.println("---------------------------------------");
        jdbcTemplate.execute(CREATE_TABLE_1);
        jdbcTemplate.execute(CREATE_TABLE_2);
    }
}