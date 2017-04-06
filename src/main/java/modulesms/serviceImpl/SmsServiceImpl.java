package modulesms.serviceImpl;

import modulesms.model.Sms;
import modulesms.repository.SmsRepository;
import modulesms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by macbookair on 05.04.17.
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsRepository smsRepository;

    @Override
    public void addSms(Sms sms) {
        smsRepository.save(sms);
    }
}
