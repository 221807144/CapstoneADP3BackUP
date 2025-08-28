package za.ac.cput.Service;

import za.ac.cput.Domain.User.Admin;
import za.ac.cput.Domain.User.Applicant;
import za.ac.cput.Domain.bookings.Bookings;
import za.ac.cput.Domain.bookings.TestAppointment;
import za.ac.cput.Domain.bookings.VehicleDisc;
import za.ac.cput.Domain.payment.Payment;
import za.ac.cput.Domain.payment.Ticket;
import za.ac.cput.Domain.Registrations.Registration;

import java.util.List;

public interface IAdminService extends IService<Admin, Integer> {

    List<Admin> getAllAdmins();

    List<Payment> getPayments();

    List<Bookings> getBookings(); // Bookings uses Long ID

    List<Applicant> getAllApplicants();

    List<Registration> getRegistration();

    List<TestAppointment> getTestAppointments(); // TestAppointment uses Long ID

    List<VehicleDisc> getVehicleDiscs(); // VehicleDisc uses Long ID

    List<Ticket> getTickets();

    boolean deleteAdmin(Integer id);           // Admin ID = Integer
    boolean deleteBooking(Long id);            // Bookings ID = Long
    boolean deleteTestAppointment(Long id);    // TestAppointment ID = Long
    boolean deleteVehicleDisc(Long id);        // VehicleDisc ID = Long
    boolean deletePayment(Integer id);         // Payment ID = Integer
    boolean deleteTicket(Integer id);          // Ticket ID = Integer
}
