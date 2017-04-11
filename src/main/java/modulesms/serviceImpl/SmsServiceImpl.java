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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmytro Tymoshenko on 05.04.17.
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsRepository smsRepository;

    @Override
    public Sms addSms(String number, String sign, String message) {

        if (parserNumber(number)) {
            Sms sms = new Sms(number, message, sign);
            sms.setSendTime(Timestamp.valueOf(localTime()));
            sms = smsRepository.save(sms);
            return sms;
        }else return null;
    }

    @Override
    public List<Sms> addSmsToAll(List<String> numbers, String sign, String message) {
        List<Sms> smsList = new LinkedList<>();
        numbers.forEach((num)->{
            if (parserNumber(num)) {
                Sms sms = new Sms(num, message, sign);
                sms.setSendTime(Timestamp.valueOf(localTime()));
                smsList.add(sms);
            }
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

    private static boolean parserNumber(String number) {

        Pattern p = Pattern.compile("^\\Q380\\E[0-9]{2}[0-9]{7}$");
        Matcher m = p.matcher(number);
        return m.matches();
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
