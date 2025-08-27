package za.ac.cput.Factory.User;

import za.ac.cput.Domain.User.Admin;
import za.ac.cput.Domain.User.User;
import za.ac.cput.Domain.bookings.Bookings;
import za.ac.cput.Domain.contact.Address;
import za.ac.cput.Domain.contact.Contact;
import za.ac.cput.Domain.payment.Payment;
import za.ac.cput.Util.Helper;

import java.time.LocalDate;
import java.util.List;

public class AdminFactory {
    public static Admin createAdmin(String idNumber, String firstName, String lastName, Contact contact, Address address, Bookings bookings, User.Role role,
                                    List<Payment> payments, Admin.Status status, String reason ,String password) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) ||
                contact == null || bookings == null || role == null || payments == null || status == null) {
            return null;
        }

        // FIXED: Only return null if reason is empty OR contact number is invalid OR email is invalid
        if (Helper.isNullOrEmpty(reason) ||
                !Helper.isValidContactNumber(contact.getCellphone()) ||
                !Helper.isValidEmail(contact.getEmail())) {
            return null;
        }
        LocalDate birthDate = Helper.getDateOfBirth(idNumber);

        return new Admin.Builder()
                .setIdNumber(idNumber)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setContact(contact)
                .setAddress(address)
                .setBookings(bookings)
                .setRole(role)
                .setPayments(payments)
                .setStatus(status)
                .setReason(reason)
                .setPassword(password)
                .build();
    }
}
