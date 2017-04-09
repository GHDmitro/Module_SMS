package modulesms.controllerRest;

import modulesms.model.Sms;
import modulesms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmytro Tymoshenko on 06.04.17.
 */

@RestController
public class ControllerRest {

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/sendSmsOne", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sms> sendSms(@RequestParam(name = "number", required = true) String number,
                                       @RequestParam(name = "sign", required = true) String sign,
                                       @RequestParam(name = "message", required = true) String message) {

        if (parserNumber(number)){
            Sms sms = smsService.addSms(number, sign, message);
            if (sms == null){
                return new ResponseEntity<Sms>(HttpStatus.NO_CONTENT);
            }else return new ResponseEntity<Sms>(HttpStatus.CREATED);
        }else return new ResponseEntity<Sms>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/sendSmsAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sms>> sendSmsAll(@RequestParam(name = "numberList", required = true) List<String> numbers,
                                                @RequestParam(name = "sign", required = true) String sign,
                                                @RequestParam(name = "message", required = true) String message) {
        final boolean[] b = {true};
        numbers.forEach((n)->{
            b[0] = parserNumber(n);
        });

        if (b[0]){
            List<Sms> smsList = smsService.addSmsToAll(numbers, sign, message);
            if (smsList == null){
                return new ResponseEntity<List<Sms>>(HttpStatus.NO_CONTENT);
            }else return new ResponseEntity<List<Sms>>(HttpStatus.CREATED);
        }else return new ResponseEntity<List<Sms>>(HttpStatus.BAD_REQUEST);

    }

    private static boolean parserNumber(String number) {

        Pattern p = Pattern.compile("^\\Q380\\E[0-9]{2}[0-9]{7}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }


}
