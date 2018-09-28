package mx.com.telcel.di.sds.gsac.sipp.complan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class MainController {
	
	@Value("${titulo}")
	private String titulo;
	
	@Value("${mensaje}")
	private String mensaje;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
		model.addAttribute("titulo", titulo);
		model.addAttribute("mensaje", mensaje);
        System.out.println("Ejecutando Main Controller");
        return "index";
    }
	
//	@RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String hello(Model model) {
//        model.addAttribute("message", "Spring MVC Thymeleaf Hello World Example!!");
//        System.out.println("Archivo Properties: " + msg);
//        return "hello";
//    }
}
