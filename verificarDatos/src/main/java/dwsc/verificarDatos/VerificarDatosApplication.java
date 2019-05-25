package dwsc.verificarDatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VerificarDatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerificarDatosApplication.class, args);
	}

}
