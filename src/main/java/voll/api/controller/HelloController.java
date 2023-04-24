package voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this is a controller with only answer with xml or json.
@RestController
//This is to tell the route of html that we are using. When we access to the http://localhost:8080/hello here excecutes this class
@RequestMapping("/hello")
public class HelloController {
    
    //here we say what we want to do with the Requested Mapping in the html
    @GetMapping
    public String helloWorld() {
        return "<h1>Hello word! This is my first Spring Boot App</h1>";
    }
}
