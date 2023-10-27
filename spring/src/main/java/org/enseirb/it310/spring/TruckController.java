package org.enseirb.it310.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
// Authors : Van Khôi LE et Maxime SALAND

@RestController
@RequestMapping("/truck")
public class TruckController {
    @Autowired TruckRepository truckRepo;

    @GetMapping("/{id}")
    public Truck getById(@PathVariable int id) {
        return truckRepo.getById(id);
    }
}
