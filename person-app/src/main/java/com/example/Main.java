package com.example;

import com.example.model.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

public class Main {
    public static void main(String[] args) {
        try {
            // Exemple légitime : Création d'objets Person avec des sous-types de PhoneNumber
            PhoneNumber domestic = new DomesticNumber(123, 4567890);
            PhoneNumber international = new InternationalNumber(1, 123, 4567890);
            Person person1 = new Person("Alice", 30, domestic);
            Person person2 = new Person("Bob", 40, international);

            // // Configuration d'ObjectMapper avec enableDefaultTyping
            ObjectMapper mapper = new ObjectMapper();
            mapper.activateDefaultTyping(
            mapper.getPolymorphicTypeValidator(),
            ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.PROPERTY
            );
            

            // ObjectMapper mapper = JsonMapper.builder()
            //     .enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)
            //     .build();

            //// Jackson 2.10: Safe Default Typing
            // ObjectMapper mapper = new ObjectMapper();
            // // Activer "Default Typing" avec une liste d'autorisation
            // PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            //     .allowIfSubType(Person.class)    // Classe sécurisée autorisée
            //     .allowIfSubType(PhoneNumber.class)    // Autre classe sécurisée
            //     .allowIfSubType("com.example.safe")    // Paquet de classes sécurisé
            //     .build();

            // mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);


            // Sérialisation des objets Person légitimes en JSON
            String jsonPerson1 = mapper.writeValueAsString(person1);
            String jsonPerson2 = mapper.writeValueAsString(person2);

            System.out.println("JSON de person1 : " + jsonPerson1);
            System.out.println("JSON de person2 : " + jsonPerson2);

            // Désérialisation des objets JSON légitimes en objets Person
            Person deserializedPerson1 = mapper.readValue(jsonPerson1, Person.class);
            Person deserializedPerson2 = mapper.readValue(jsonPerson2, Person.class);

            System.out.println("Objet désérialisé person1 : " + deserializedPerson1);
            System.out.println("Objet désérialisé person2 : " + deserializedPerson2);

            // --- PoC : JSON malveillant pour injecter ExploitBean dans Person ---
            String jsonMalicious = "{\n" +
                    "  \"@class\": \"com.example.model.Person\",\n" +
                    "  \"name\": \"Eve\",\n" +
                    "  \"age\": 25,\n" +
                    "  \"phone\": {\n" +
                    "    \"@class\": \"com.example.model.ExploitBean\",\n" +
                    "    \"command\": \"notepad.exe\"\n" +
                    "  }\n" +
                    "}";

            // Désérialiser le JSON malveillant
            Person maliciousPerson = mapper.readValue(jsonMalicious, Person.class);
            System.out.println("Objet malveillant désérialisé : " + maliciousPerson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
