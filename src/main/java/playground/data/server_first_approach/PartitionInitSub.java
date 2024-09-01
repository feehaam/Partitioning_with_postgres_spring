package playground.data.server_first_approach;

public class PartitionInitSub extends PartitionInit{
    @Override
    public String getQ(){
        return "CREATE TABLE IF NOT EXISTS tablexx (id INT PRIMARY KEY, name VARCHAR(255))";
    }
}