package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by courtneym on 12/08/2015.
 */
@Controller
public class GetterWebPage {

    @Autowired
    ConfigurableApplicationContext context;

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    String message = "Nothing sent from queue";

    @JmsListener(destination = "getter-page", containerFactory = "webPageListener")
    public void getMessage(String message) {
        System.out.println("This message, when called " + message);
        this.message = message;
    }

    @RequestMapping("/Getter")
    public void displayFromQueue(Model model) {
        System.out.println("Getter page called");
        model.addAttribute("name", message);
    }
}