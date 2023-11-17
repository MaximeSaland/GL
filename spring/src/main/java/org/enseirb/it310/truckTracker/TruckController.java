package org.enseirb.it310.truckTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

// authors : Maxime Saland et Van Khôi Lê
@RestController
@RequestMapping("/truck")
public class TruckController {
    @Autowired
    Consumer consumer;

    @Autowired
    MapService mapService;

    @GetMapping
    public List<TruckPositionMessage> getPositions() {
        return consumer.getPositions();
    }

    @GetMapping("/{id}")
    public TruckPositionMessage getLatestPositionFromTruckId(@PathVariable int id) throws ResponseStatusException {
        if (consumer.checkTruckPresenceById(id)) {
            return consumer.getPositions()
                    .stream()
                    .filter(p -> p.getTruckId() == id)
                    .sorted(Comparator.comparing(TruckPositionMessage::getTs))
                    .toList()
                    .getLast();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Truck with id " + id + "has no recorded position");
    }

    @GetMapping("/{id}/map")
    public ResponseEntity<Void> getMapURLFromPosition(@PathVariable int id) throws ResponseStatusException {
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(mapService.getMapURL(getLatestPositionFromTruckId(id).getPosition())))
                    .build();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Truck with id " + id + "has no recorded position");
        }
    }
}
