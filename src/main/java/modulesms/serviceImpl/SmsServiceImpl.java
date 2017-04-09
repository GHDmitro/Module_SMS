package modulesms.serviceImpl;

import modulesms.model.Sms;
import modulesms.repository.SmsRepository;
import modulesms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dmytro Tymoshenko on 05.04.17.
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsRepository smsRepository;

    @Override
    public Sms addSms(String number, String sign, String message) {
        Sms sms = new Sms(number,message,sign);
        sms.setSendTime(Timestamp.valueOf(localTime()));
        return smsRepository.save(sms);
    }

    @Override
    public List<Sms> addSmsToAll(List<String> numbers, String sign, String message) {
        List<Sms> smsList = new LinkedList<>();
        numbers.forEach((num)->{
            Sms sms = new Sms(num,message,sign);
            sms.setSendTime(Timestamp.valueOf(localTime()));
            smsList.add(sms);
        });
        return smsRepository.save(smsList);
    }


    private LocalDateTime localTime(){

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String str = ldt.format(DATE_TME_FORMATTER);
        ldt = LocalDateTime.parse(str, DATE_TME_FORMATTER);
        return  ldt;
    }



//    @Override
//    public void addSms(Sms sms) {
//        smsRepository.save(sms);
//    }
//
//    @Override
//    public void addSmsToAll(Sms sms, List<String> numbers) {
//        List<Sms> smsList = new LinkedList<>();
//
//        numbers.forEach((n)->{
//            Sms sms1 = new Sms()
//        });
//    }
}
