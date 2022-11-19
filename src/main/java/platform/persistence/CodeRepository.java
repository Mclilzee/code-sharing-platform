package platform.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.business.Code;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {
}
