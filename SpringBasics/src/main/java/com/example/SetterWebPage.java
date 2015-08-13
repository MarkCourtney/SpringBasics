package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by courtneym on 12/08/2015.
 */
@Controller
public class SetterWebPage {

    @Autowired
    ConfigurableApplicationContext context;

    // Request mapping value, model attribute and return "String" must all be the same
    // The html page must correspond to these values as well

    // Sets the th:Object type to be a Form
    @RequestMapping(value="/Setter", method=RequestMethod.GET)
    public String messageForm(Model model) {
        model.addAttribute("Setter", new Form());
        return "Setter";
    }

    /*
    @RequestMapping(value="/Setter", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Form form, Model model) {
        System.out.println("Form Data " + form);
        model.addAttribute("Setter", form.getMessage());
        return "result";
    }*/

    @RequestMapping(value="/Setter", method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void sendMessage(@ModelAttribute Form form) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(form.getMessage());
            }
        };
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        System.out.println("Sending a new message: " + form.getMessage());
        jmsTemplate.send("getter-page", messageCreator);
    }
/*
    @RequestMapping(value="/Setter", method=RequestMethod.POST)
    public void sendData(@ModelAttribute Form form, Model model) {
        System.out.println(form);
    }

    //@RequestMapping(method = RequestMethod.POST)
    public void getText() {
        //System.out.println("Bunch of stuff " + value);
        //return value;
    }*/
}
