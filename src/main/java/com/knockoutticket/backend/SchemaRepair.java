package com.knockoutticket.backend;
import org.flywaydb.core.Flyway;
public class SchemaRepair {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/knockoutticket_database", "root", "@240420042501")
                .load();


        flyway.repair();
    }
}
