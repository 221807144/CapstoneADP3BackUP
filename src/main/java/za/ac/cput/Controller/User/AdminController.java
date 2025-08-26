package za.ac.cput.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.User.Admin;
import za.ac.cput.Domain.User.Applicant;
import za.ac.cput.Domain.bookings.Bookings;
import za.ac.cput.Domain.bookings.TestAppointment;
import za.ac.cput.Domain.bookings.VehicleDisc;
import za.ac.cput.Domain.payment.Payment;
import za.ac.cput.Domain.payment.Ticket;
import za.ac.cput.Domain.Registrations.Registration;
import za.ac.cput.Service.IAdminService;
import za.ac.cput.Service.impl.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/CapstoneADP3/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Create Admin
    @PostMapping("/create")
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.create(admin));
    }

    // Read Admin by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<Admin> read(@PathVariable Integer id) {
        Admin admin = adminService.read(id);
        if (admin == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(admin);
    }

    // Update Admin
    @PutMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
        Admin updated = adminService.update(admin);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = adminService.delete(id);
        if (!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all-data")
    public ResponseEntity<Map<String, Object>> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("admins", adminService.getAllAdmins());
        data.put("applicants", adminService.getAllApplicants());
        data.put("bookings", adminService.getBookings());
        data.put("payments", adminService.getPayments());
        data.put("registrations", adminService.getRegistration());
        data.put("testAppointments", adminService.getTestAppointments());
        data.put("vehicleDiscs", adminService.getVehicleDiscs());
        data.put("tickets", adminService.getTickets());
        return ResponseEntity.ok(data);
    }
}
