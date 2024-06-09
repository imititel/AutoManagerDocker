package com.sos.proiect.controller;

import com.sos.proiect.model.Vehicle;
import com.sos.proiect.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PutMapping("/update/{vin}")
    public Vehicle updateVehicle(@PathVariable String vin, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vin, vehicle);
    }

    @DeleteMapping("/delete/{vin}")
    public void deleteVehicle(@PathVariable String vin) {
        vehicleService.deleteVehicle(vin);
    }
}

