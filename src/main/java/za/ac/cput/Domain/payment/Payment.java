package za.ac.cput.Domain.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import za.ac.cput.Domain.User.Admin;

import java.time.LocalDate;

/*
Thando Tinto
221482210
*/

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String paymentDetails;
    private double paymentAmount;
    private LocalDate paymentDate;
    private String cardName;
    private long cardNumber;
    private LocalDate cardDate;
    private short cvs;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference
    private Admin admin;
    public Payment() {
    }
    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.paymentType = builder.paymentType;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDetails = builder.paymentDetails;
        this.paymentAmount = builder.paymentAmount;
        this.paymentDate = builder.paymentDate;
        this.cardName = builder.cardName;
        this.cardNumber = builder.cardNumber;
        this.cardDate = builder.cardDate;
        this.cvs = builder.cvs;
        this.admin = builder.admin;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalDate getCardDate() {
        return cardDate;
    }

    public String getCardName() {
        return cardName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public short getCvs() {
        return cvs;
    }

    public Admin getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "paymentId=" + paymentId +
                ", paymentType=" + paymentType +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDetails='" + paymentDetails + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                ", cardName='" + cardName + '\'' +
                ", cardNumber=" + cardNumber +
                ", cardDate=" + cardDate +
                ", cvs=" + cvs +
                ", admin=" + admin +
                '}';
    }

    public static class Builder {
        private int paymentId;
        private PaymentType paymentType;
        private PaymentMethod paymentMethod;
        private String paymentDetails;
        private double paymentAmount;
        private LocalDate paymentDate;
        private String cardName;
        private long cardNumber;
        private LocalDate cardDate;
        private short cvs;
        private Admin admin;

        public Builder setPaymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            this.paymentDetails = paymentType.getPaymentDetails();
            return this;
        }

        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

//        public Builder setPaymentDetails(String paymentDetails) {
//            this.paymentDetails = paymentDetails;
//            return this;
//        }

        public Builder setPaymentAmount(double paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setCardDate(LocalDate cardDate) {
            this.cardDate = cardDate;
            return this;
        }

        public Builder setCardName(String cardName) {
            this.cardName = cardName;
            return this;
        }

        public Builder setCardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setCvs(short cvs) {
            this.cvs = cvs;
            return this;
        }
        public Builder setAdmin(Admin admin) {
            this.admin = admin;
            return this;
        }

        public Builder copy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.paymentType = payment.paymentType;
            this.paymentMethod = payment.paymentMethod;
            this.paymentDetails = payment.paymentDetails;
            this.paymentAmount = payment.paymentAmount;
            this.paymentDate = payment.paymentDate;
            this.cardName = payment.cardName;
            this.cardNumber = payment.cardNumber;
            this.cvs = payment.cvs;
            this.cardDate = payment.cardDate;
            this.admin = payment.admin;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    public enum PaymentType {
        Ticket("Payment for ticket."),
        Booking("Payment for booking."),
        Disc("Payment for vehicle disc."),;

        private final String PaymentDetails;

        PaymentType(String paymentDetails) {
            PaymentDetails = paymentDetails;
        }

        public String getPaymentDetails() {
            return PaymentDetails;
        }

    }
    public enum PaymentMethod{
        Card,
        Cash();
    }
}



