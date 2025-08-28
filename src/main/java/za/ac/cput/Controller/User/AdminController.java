package za.ac.cput.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.User.Admin;
import za.ac.cput.Domain.User.Applicant;
import za.ac.cput.Service.impl.AdminService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admins")
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

    // Delete Admin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!adminService.deleteAdmin(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // Get all data for dashboard
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

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin loginRequest) {
        if (loginRequest.getContact() == null || loginRequest.getContact().getEmail() == null) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        if (loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        return adminService.getAllAdmins().stream()
                .filter(a -> a.getContact() != null &&
                        a.getContact().getEmail().equalsIgnoreCase(loginRequest.getContact().getEmail()))
                .findFirst()
                .map(admin -> {
                    if (loginRequest.getPassword().equals(admin.getPassword())) {
                        Map<String, Object> response = new HashMap<>();
                        response.put("message", "Login successful!");
                        response.put("userId", admin.getUserId());
                        response.put("firstName", admin.getFirstName());
                        response.put("lastName", admin.getLastName());
                        return ResponseEntity.ok(response);
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password.");
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found."));
    }
    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateApplicantStatus(@PathVariable Integer id, @RequestBody Map<String, String> payload) {
        String statusStr = payload.get("status");
        String reason = payload.get("reason");

        if (statusStr == null) return ResponseEntity.badRequest().body("Status is required");

        try {
            Applicant updated = adminService.updateApplicantStatus(id, statusStr, reason);
            if (updated == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
