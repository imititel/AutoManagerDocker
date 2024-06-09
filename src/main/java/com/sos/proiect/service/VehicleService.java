package com.sos.proiect.service;

import com.sos.proiect.model.Vehicle;
import com.sos.proiect.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByVin(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new RuntimeException("Vehicle not found with VIN: " + vin);
        }
    }

    public Vehicle updateVehicle(String vin, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(vin);
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            vehicle.setMake(updatedVehicle.getMake());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setYear(updatedVehicle.getYear());
            vehicle.setOwner(updatedVehicle.getOwner());
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with VIN: " + vin);
        }
    }

    public void deleteVehicle(String vin) {
        vehicleRepository.deleteById(vin);
    }
}

