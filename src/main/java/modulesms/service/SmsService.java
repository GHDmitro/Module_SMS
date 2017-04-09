package modulesms.service;

import modulesms.model.Sms;

import java.util.List;

/**
 * Created by Dmytro Tymoshenko on 05.04.17.
 */

public interface SmsService {
    //void addSmsToAll(Sms sms, List<String> numbers);
    //void addSms(Sms sms);
    Sms addSms(String number, String sign, String message);
    List<Sms> addSmsToAll(List<String> numbers, String sign, String message);
}