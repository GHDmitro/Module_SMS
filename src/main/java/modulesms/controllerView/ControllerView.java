package modulesms.controllerView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dmytro Tymoshenko on 06.04.17.
 */

@Controller
public class ControllerView {

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String home(){
        return "home";
    }

}
