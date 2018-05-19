package edu.maskleo.log4j.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private static final Logger LOGGERD = LogManager.getLogger("D");

    private static final Logger LOGGERE = LogManager.getLogger("E");

    @GetMapping(value = {"/", "index"})
    @ResponseBody
    public String index() {
        LOGGERD.debug("d this is debug msg!");
        LOGGERD.info("d this is info msg!");
        LOGGERD.error("d this is error msg!");
        return "hello world";
    }

    @GetMapping(value = {"home"})
    @ResponseBody
    public String home() {
        LOGGERE.debug("e this is info msg!");
        LOGGERE.info("e this is info msg!");
        LOGGERE.error("e this is info msg!");
        return "home";
    }
}
