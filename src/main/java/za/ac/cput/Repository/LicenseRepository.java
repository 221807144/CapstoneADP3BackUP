package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.Domain.License;

public interface LicenseRepository extends JpaRepository<License, Integer> {
}
