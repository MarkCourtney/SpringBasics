package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by courtneym on 12/08/2015.
 */
@Controller
public class SetterWebPage {

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping("/Setter")
    //@RequestMapping(method = RequestMethod.POST)
    public void getText(@ModelAttribute("anid") String value) {
        System.out.println("Bunch of stuff " + value);
        //return value;
    }

/*
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("helloWorld");
        model.addObject("msg", "hello world!");

        return model;
    }

}
    /*@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    /*@RequestMapping(value = "/Setter")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World");
        return "Hello World";
    }
    /*@RequestMapping(method = RequestMethod.POST)
    public void getFormData(@ModelAttribute(value = "test") String formValue) {

        System.out.println(formValue);
    }

    /*@RequestMapping("/Setter.html")
    public @ResponseBody String greet() { return "Hello"; }
    */
}
