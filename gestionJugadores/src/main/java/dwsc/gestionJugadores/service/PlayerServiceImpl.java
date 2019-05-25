package dwsc.gestionJugadores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dwsc.gestionJugadores.dao.VerificarDatosClient;

@Service
public class PlayerServiceImpl implements PlayerService
{
	@Autowired
	VerificarDatosClient verificarDatosClient;
	
	public boolean checkPlayerData(String dni)
	{
		return verificarDatosClient.checkDNI(dni);
	}
}
