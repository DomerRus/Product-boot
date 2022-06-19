package ru.itmo.productboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.productboot.model.Organization;
import ru.itmo.productboot.model.enums.OrganizationType;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    Optional<Organization> findByNameAndFullNameAndAndEmployeesCountAndType(String name,
                                                                            String fullName,
                                                                            Long emplCount,
                                                                            OrganizationType organizationType);
}
