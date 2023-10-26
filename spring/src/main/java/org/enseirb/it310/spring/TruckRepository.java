package org.enseirb.it310.spring;

import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class TruckRepository {
    HashMap<Integer, Truck> truckRepo;
    public TruckRepository() {
        truckRepo = new HashMap();
        truckRepo.put(0, new Truck("AA-123-BB", "2022-01-05", 550.0));
        truckRepo.put(1, new Truck("CC-456-DD", "2023-05-06", 757.0));
        truckRepo.put(2, new Truck("EE-789-FF", "2022-06-22", 632.0));
    }

    public Truck getById(int id) {
        Truck truck = truckRepo.get(id);
        return truck;
    }
}
