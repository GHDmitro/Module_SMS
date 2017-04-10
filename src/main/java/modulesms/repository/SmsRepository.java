package modulesms.repository;

import modulesms.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmytro Tymoshenko on 05.04.17.
 */
//@Repository //
public interface SmsRepository extends JpaRepository<Sms, Long> {


}
