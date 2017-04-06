package modulesms.controllerRest;

import modulesms.model.Sms;
import modulesms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by macbookair on 06.04.17.
 */

@RestController("/")
public class ControllerRest {

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sms> sendSms(@RequestParam(name = "number", required = true) String number,
                                       @RequestParam(name = "message", required = true) String message) {

        if (parserNumber(number)){



        }

        return null;

    }


    private static boolean parserNumber(String number) {

        Pattern p = Pattern.compile("^\\Q+380\\E[0-9]{2}[0-9]{7}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    public static void main(String[] args) {
        String s = "+38095106dd999";
        boolean k = parserNumber(s);

        if (k){
            System.out.println("yes");
        }
    }

}
