package modulesms.repository;

import modulesms.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by macbookair on 05.04.17.
 */
@Repository //
public interface SmsRepository extends JpaRepository<Sms, Long> {


}
