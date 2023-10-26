package org.enseirb.it310.spring;

import java.time.Instant;

public class Truck {
    private String immatriculation;
    private String dateOfFirstRegistration;
    private double netWeight;

    public Truck(String immatriculation, String dateOfFirstRegistration, double netWeight) {
        setImmatriculation(immatriculation);
        setDateOfFirstRegistration(dateOfFirstRegistration);
        setNetWeight(netWeight);
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(String dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }
}
