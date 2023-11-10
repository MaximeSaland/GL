package org.enseirb.it310.truckTracker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// authors : Maxime Saland et Van Khôi Lê
public class TruckPositionMessage {

    private final int truckId;
    private final double ts;
    private final Position position;

    @JsonCreator
    public TruckPositionMessage(@JsonProperty("truckId") int truckId,
                                @JsonProperty("ts") double ts,
                                @JsonProperty("position") Position position) {
        this.truckId = truckId;
        this.ts = ts;
        this.position = position;
    }

    public int getTruckId() {
        return truckId;
    }

    public double getTs() {
        return ts;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "id: " + truckId + " ts: " + ts + " lat: " + position.getLatitude() + " long: " + position.getLongitude();
    }
}
