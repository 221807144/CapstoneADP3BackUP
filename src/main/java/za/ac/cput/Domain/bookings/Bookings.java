package za.ac.cput.Domain.bookings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import za.ac.cput.Domain.User.User;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Enumerated(EnumType.STRING)
    private Booktype booktype;

    private LocalDate bookingDate;

    @OneToOne
    @JoinColumn(name = "vehicle_disc_id")
    private VehicleDisc vehicleDisc;

    @OneToOne
    @JoinColumn(name = "test_test_id")
    private TestAppointment test;
    @OneToMany(mappedBy = "bookings")
    @JsonBackReference
    private List<User> user;

    public enum Booktype {
        VEHICLE_DISC,
        TEST
    }

    public Bookings() {}

    public Bookings(Builder builder) {
        this.bookingId = builder.bookingId;
        this.booktype = builder.booktype;
        this.bookingDate = builder.bookingDate;
        this.vehicleDisc = builder.vehicleDisc;
        this.test = builder.test;
        this.user= builder.user;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Booktype getBooktype() {
        return booktype;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public VehicleDisc getVehicleDisc() {
        return vehicleDisc;
    }

    public TestAppointment getTest() {
        return test;
    }
    public List<User> getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "bookingId=" + bookingId +
                ", booktype=" + booktype +
                ", bookingDate=" + bookingDate +
                ", vehicleDisc=" + vehicleDisc +
                ", test=" + test +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private Long bookingId;
        private Booktype booktype;
        private LocalDate bookingDate;
        private VehicleDisc vehicleDisc;
        private TestAppointment test;
        private List<User> user;

        public Builder setBookingId(Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder setBooktype(Booktype booktype) {
            this.booktype = booktype;
            return this;
        }

        public Builder setBookingDate(LocalDate bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder setVehicleDisc(VehicleDisc vehicleDisc) {
            this.vehicleDisc = vehicleDisc;
            return this;
        }

        public Builder setTest(TestAppointment test) {
            this.test = test;
            return this;
        }
        public Builder setUser(List<User> user) {
            this.user = user;
            return this;
        }

        public Builder copy(Bookings bookings) {
            this.bookingId = bookings.bookingId;
            this.booktype = bookings.booktype;
            this.bookingDate = bookings.bookingDate;
            this.vehicleDisc = bookings.vehicleDisc;
            this.test = bookings.test;
            this.user = bookings.user;

            return this;
        }

        public Bookings build() {
            return new Bookings(this);
        }
    }
}
