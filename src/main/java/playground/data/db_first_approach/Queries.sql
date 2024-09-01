-- There will be a product table which can have 100 millions of products.
-- we'll make partitions for each 10 million

-- Drop previous tables
DO $$
DECLARE
    partition_table_name text;
BEGIN
    -- Dropping partition tables if they exist
    FOR partition_table_name IN
        SELECT table_name
        FROM information_schema.tables
        WHERE table_schema = 'public'
        AND table_name LIKE 'product_part_%' LOOP
        EXECUTE format('DROP TABLE IF EXISTS %I;', partition_table_name);
    END LOOP;

    -- Dropping the main table
    DROP TABLE IF EXISTS Product;
END $$;

-- Main table creation with partitioning
CREATE TABLE Product (
    product_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    sku_id VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    available_quantity INT NOT NULL,
    is_available BOOLEAN NOT NULL,
    PRIMARY KEY(product_id)
) PARTITION BY RANGE(product_id);

-- Create partition tables and indexes using loop
DO $$
DECLARE
    i INT;
    start_val BIGINT;
    end_val BIGINT;
    table_name TEXT;
BEGIN
    FOR i IN 0..9 LOOP
        start_val := i * 1000000;
        end_val := (i + 1) * 1000000;
        table_name := format('Product_part_%s', i + 1);

        -- Create partition table
        EXECUTE format('
            CREATE TABLE %I PARTITION OF Product
            FOR VALUES FROM (%s) TO (%s);
        ', table_name, start_val, end_val);

        -- Add index on product_id, name, price and category+price
		-- for partition table
        EXECUTE format('CREATE INDEX ON %I (product_id);', table_name);
		EXECUTE format('CREATE INDEX ON %I (name);', table_name);
		EXECUTE format('CREATE INDEX ON %I (price);', table_name);
		EXECUTE format('CREATE INDEX ON %I (category, price);', table_name);
    END LOOP;
END $$;

-- The old product table without partition
DROP TABLE IF EXISTS Old_Product;
CREATE TABLE Old_Product (
    product_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    sku_id VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    available_quantity INT NOT NULL,
    is_available BOOLEAN NOT NULL,
    PRIMARY KEY(product_id)
);
CREATE INDEX ON Old_Product (product_id);
CREATE INDEX ON Old_Product (name);
CREATE INDEX ON Old_Product (price);
CREATE INDEX ON Old_Product (category, price);

-- Populate tables
DO $$
DECLARE
   r RECORD;
   i BIGINT;
   availableQty INT;
   categories TEXT[] := ARRAY['Electronics', 'Grocery', 'Clothing', 'Accessories', 'Babies'];
BEGIN
   -- Generate and insert new data
   FOR i IN 1..5000000 LOOP
       availableQty := FLOOR(RANDOM() * 1000);

       INSERT INTO Product (product_id, name, category, sku_id, price, available_quantity, is_available)
       VALUES (
           i,
           'Product name ' || i,
           categories[FLOOR(RANDOM() * 5) + 1],
           LEFT(md5(random()::text), 6),
           RANDOM() * 100000,
           availableQty,
           availableQty > 0
       );

       INSERT INTO Old_Product (product_id, name, category, sku_id, price, available_quantity, is_available)
       VALUES (
           i,
           'Product name ' || i,
           categories[FLOOR(RANDOM() * 5) + 1],
           LEFT(md5(random()::text), 6),
           RANDOM() * 100000,
           availableQty,
           availableQty > 0
       );
   END LOOP;
END $$;