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

    public Vehicle updateVehicle(String vin, Vehicle updatedVehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vin);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();
            existingVehicle.setMake(updatedVehicle.getMake());
            existingVehicle.setModel(updatedVehicle.getModel());
            existingVehicle.setYear(updatedVehicle.getYear());
            existingVehicle.setOwner(updatedVehicle.getOwner());
            return vehicleRepository.save(existingVehicle);
        } else {
            throw new RuntimeException("Vehicle not found with VIN: " + vin);
        }
    }

    public void deleteVehicle(String vin) {
        vehicleRepository.deleteById(vin);
    }
}

