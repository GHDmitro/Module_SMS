package modulesms.transactions;

import modulesms.ModuleSmsApplicationTests;
import modulesms.model.Sms;
import modulesms.service.SmsService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

//import javax.transaction.Transactional;

/**
 * Created by Dmytro Tymoshenko on 07.04.17.
 */

@Transactional
public class ModuleTransactionsTest extends ModuleSmsApplicationTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SmsService smsService;
//    @Autowired
//    private SmsRepository smsRepository;
//    @After
//    public void deleteAll(){
//        smsRepository.deleteAll();
//    }

    @Test
    public void smsOneTest(){
        logger.info("> in smsOneTest :");

        String number = "380951063000";

        String sign = "progKiev";

        String message = "Привет студент";

        Sms sms = smsService.addSms(number,sign,message);

        assertNotNull(sms);
        assertNotNull(sms.getId());
        assertEquals("failed -> message is not equals;", message, sms.getMessage());
        assertEquals("failed -> number is not equals;", number, sms.getNumber());
        assertEquals("failed -> sign is not equals;", sign, sms.getSign());

        logger.info("< after smsOneTest; ");
    }

    private List<String> getAllNumbers(){

        List<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add("38095447333"+i);
        }
        return list;
    }

    //@Ignore
    @Test
    public void smsAllTest(){
        logger.info("> in smsAllTest :");

        List<String> numbers = getAllNumbers();

        String sign = "progKiev";

        String message = "Привет наш уважаемый студент";

        List<Sms> smsList = smsService.addSmsToAll(numbers, sign, message);

        assertNotNull(smsList);
        assertTrue("failed -> size of smsList is 0;",smsList.size() > 0);
        assertEquals("failed -> amount of numbers is not equals to smsList;",numbers.size(), smsList.size());
        logger.info("< after smsAllTest; ");
    }
}





























