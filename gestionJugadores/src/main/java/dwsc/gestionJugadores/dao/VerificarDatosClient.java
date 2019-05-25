package dwsc.gestionJugadores.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("VERIFICAR-DATOS")
public interface VerificarDatosClient 
{
	@GetMapping("/checkDNI/{dni}")
	public boolean checkDNI(@PathVariable String dni);
}

