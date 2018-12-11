package com.gus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gus.data.DataAccess;
import com.gus.domain.Offer;
import com.gus.service.OfferService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class Main {
    DataAccess dataAccess = new DataAccess();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Bean
    CommandLineRunner runner(OfferService offerService) {
        return args -> {
            // read JSON in resources and load to database

            List jsonFileNames = dataAccess.getFileNamesInResources();

            for (int i = 0; i < jsonFileNames.size(); i++) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                TypeReference<List<Offer>> typeReference = new TypeReference<List<Offer>>() {
                };
                InputStream inputStream =
                        TypeReference.class.getResourceAsStream("/cbop/" + jsonFileNames.get(i));

                try {
                    List<Offer> offers = mapper.readValue(inputStream, typeReference);
                    offerService.save(offers);
                    System.out.println("Offers Saved!");
                } catch (IOException e) {
                    System.out.println("Unable to save offers: " + e.getMessage());
                }
                inputStream.close();
            }
        };
    }
}

