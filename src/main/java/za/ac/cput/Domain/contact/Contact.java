package za.ac.cput.Domain.contact;

import jakarta.persistence.*;

/*Masibuve Sikhulume
221807144
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
    @Column(unique = true, nullable = false)
    private String cellphone;
    @Column(unique = true, nullable = false)
    private String email;

    public Contact() {
    }

    private Contact(Builder builder) {
        this.contactId = builder.contactId;
        this.cellphone = builder.cellphone;
        this.email = builder.email;
    }

    public int getContactId() {
        return contactId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {
        private int contactId;
        private String cellphone;
        private String email;

        public Builder setContactId(int contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder setCellphone(String cellphone) {
            this.cellphone = cellphone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
