package com.perfume.expert.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PerfumeRepository perfumeRepository;

    @Autowired
    public DataInitializer(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PerfumeModel> perfumes = new ArrayList<>();

        // Establish connection to the existing database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/perfumescopy", "root", "localhostsql")) {
            // Execute query to retrieve perfume data
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM perfumescopy.perfumescopy")) {
                while (resultSet.next()) {
                    PerfumeModel perfume = new PerfumeModel();
                    perfume.setPerfume_name(resultSet.getString("perfume_name"));
                    perfume.setGender(resultSet.getString("gender"));
                    perfume.setAge(resultSet.getString("age"));
                    perfume.setPrice(resultSet.getString("price"));
                    perfume.setOlfactory_group(resultSet.getString("olfactory_group"));
                    perfume.setSeason(resultSet.getString("season"));
                    perfume.setOccasion(resultSet.getString("occasion"));
                    perfume.setDominant_note(resultSet.getString("dominant_note"));
                    perfume.setImage(resultSet.getString("image"));

                    perfumes.add(perfume);
                }
            }
        }
    }
}