package dwsc.interfazAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterfazAdminController 
{
	//PÁGINA PRINCIPAL (CREAR NOTICIAS)
	@RequestMapping("/")
	public String home()
	{
		return "redirect:" + "http://localhost:8080/ProductorConsumidor";
	}
}
