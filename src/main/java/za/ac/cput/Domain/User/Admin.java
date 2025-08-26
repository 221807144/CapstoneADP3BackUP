package za.ac.cput.Domain.User;

import jakarta.persistence.*;
import za.ac.cput.Domain.bookings.Bookings;
import za.ac.cput.Domain.contact.Contact;
import za.ac.cput.Domain.payment.Payment;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "admin_id") // this creates a foreign key in Payment table
    private List<Payment> payments = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    protected Status status;

    private String reason;
//    @OneToOne(cascade = CascadeType.ALL)
//    private User user;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Admin() {
    }

    private Admin(Builder builder) {
        this.userId = builder.userId;
        this.firstName= builder.firstName;
        this.lastName = builder.lastName;
        this.contact = builder.contact;
        this.bookings = builder.bookings;
        this.role = builder.role;
        this.payments = builder.payments != null ? builder.payments : new ArrayList<>();
        this.status = builder.status;
        this.reason = builder.reason;

//        // Set admin on each payment for bi-directional consistency
//        for (Payment p : this.payments) {
//            p.setAdmin(this);
//        }
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "payments=" + payments +
                ", status=" + status +
                ", reason='" + reason + '\'' +

                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact=" + contact +
                ", password='" + password + '\'' +
                ", bookings=" + bookings +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private int userId;
        private String firstName;
        private String lastName;
        private Contact contact;
        private Bookings bookings;
        private Role role;
        private List<Payment> payments;
        private Status status;
        private String reason;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            lastName = lastName;
            return this;
        }

       

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder setBookings(Bookings bookings) {
            this.bookings = bookings;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setPayments(List<Payment> payments) {
            this.payments = payments;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder copy(Admin admin) {
            this.userId = admin.userId;
            this.firstName = admin.firstName;
            this.lastName = admin.lastName;
            this.contact = admin.contact;
            this.bookings = admin.bookings;
            this.role = admin.role;
            this.payments = admin.payments;
            this.status = admin.status;
            this.reason = admin.reason;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
