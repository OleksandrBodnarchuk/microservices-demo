package pl.alex.organizationservice.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.alex.organizationservice.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  Optional<Organization> findByCode(String code);
}
