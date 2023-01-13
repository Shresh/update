package com.example.assignment.initial;

import com.example.assignment.model.Battery;
import com.example.assignment.repository.BatteryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Repository
public class InitialCreator {
    private final BatteryRepo batteryRepo;

    public void initialSetup() {
        createBattery();
    }


    void createBattery() {
        saveBattery(Battery.builder().name("Cannington").postcode("6107").capacity(13500).build());
        saveBattery(Battery.builder().name("Midland").postcode("6057").capacity(50500).build());
        saveBattery(Battery.builder().name("Hay Street").postcode("6000").capacity(23500).build());
        saveBattery(Battery.builder().name("Mount Adams").postcode("6525").capacity(12000).build());
        saveBattery(Battery.builder().name("Koolan Island").postcode("6733").capacity(10000).build());
        saveBattery(Battery.builder().name("Armadale").postcode("6992").capacity(25000).build());
        saveBattery(Battery.builder().name("Lesmurdie").postcode("6076").capacity(13500).build());
        saveBattery(Battery.builder().name("Kalamunda").postcode("6076").capacity(13500).build());
        saveBattery(Battery.builder().name("Carmel").postcode("6076").capacity(36000).build());
        saveBattery(Battery.builder().name("Bentley").postcode("6102").capacity(85000).build());
        saveBattery(Battery.builder().name("Akunda Bay").postcode("2084").capacity(13500).build());
        saveBattery(Battery.builder().name("Werrington Country").postcode("2747").capacity(13500).build());
        saveBattery(Battery.builder().name("Bagot").postcode("0820").capacity(27000).build());
        saveBattery(Battery.builder().name("Yirrkala").postcode("0880").capacity(13500).build());
        saveBattery(Battery.builder().name("University of Melbourne").postcode("3010").capacity(85000).build());
        saveBattery(Battery.builder().name("Norfolk Island").postcode("2899").capacity(13500).build());
        saveBattery(Battery.builder().name("Ootha").postcode("2875").capacity(13500).build());
        saveBattery(Battery.builder().name("Kent Town").postcode("5067").capacity(13500).build());
        saveBattery(Battery.builder().name("Northgate Mc").postcode("9464").capacity(13500).build());
        saveBattery(Battery.builder().name("Gold Coast Mc").postcode("9729").capacity(50000).build());
    }

    @Transactional
    void saveBattery(Battery battery) {
        if (!batteryRepo.findByName(battery.getName()).isPresent()) {
            batteryRepo.save(battery);
        }
    }

}
