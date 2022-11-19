package platform.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findFirst10ByOrderByLocalDateTimeDesc();
}
