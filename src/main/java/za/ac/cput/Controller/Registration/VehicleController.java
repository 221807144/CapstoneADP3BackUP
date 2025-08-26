package za.ac.cput.Controller.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Registrations.Vehicle;
import za.ac.cput.Service.impl.VehicleService;


import java.util.List;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:3000") // <-- allow React app

public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create")
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }
//    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
//        Vehicle createdVehicle = vehicleService.create(vehicle);
//        return ResponseEntity.ok(createdVehicle);
//    }


    @GetMapping("/read/{vehicleID}")
    public Vehicle read(@PathVariable int vehicleID) {
        return vehicleService.read(vehicleID);
    }

    @PutMapping("/update")
    public Vehicle update(@RequestBody Vehicle vehicle) {
        return vehicleService.update(vehicle);
    }

//    @DeleteMapping("/delete/{vehicleID}")
//    public void delete(@PathVariable int vehicleID) {
//        vehicleService.delete(vehicleID);
//    }
    @GetMapping("/getAll")
    public List<Vehicle> getAll() {
        return vehicleService.getAll();
    }
}
