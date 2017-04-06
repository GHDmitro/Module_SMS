package modulesms.service;

import java.util.List;

/**
 * Created by macbookair on 05.04.17.
 */
public interface SmsService {
    //void addSmsToAll(Sms sms, List<String> numbers);
    //void addSms(Sms sms);

    void addSms(String number, String sign, String message);
    void addSmsToAll(List<String> numbers, String sign, String message);



}