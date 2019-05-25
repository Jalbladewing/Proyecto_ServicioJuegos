package dwsc.verificarDatos.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DniController 
{
	@RequestMapping("/checkDNI/{dni}")
	public boolean checkDNI(@PathVariable String dni)
	{
		int numeroDni;
		
		if(dni.length() != 9) return false; //Comprueba que tenga 9 caracteres.
		if(!Character.isLetter(dni.charAt(8))) return false; //Comprueba que el último caracter sea una letra.
		
		try 
		{
			numeroDni = Integer.parseInt(dni.substring(0,8));
			if(!dni.substring(8).toUpperCase().equals(getLetraDNI(numeroDni))) return false; //Comprueba que la letra sea la correcta
			
		}catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	private String getLetraDNI(int numeroDni)
	{
		String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

		return asignacionLetra[numeroDni%23];
	}
}
