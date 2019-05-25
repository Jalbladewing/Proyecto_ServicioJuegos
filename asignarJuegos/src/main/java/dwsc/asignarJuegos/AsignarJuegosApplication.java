package dwsc.asignarJuegos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AsignarJuegosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsignarJuegosApplication.class, args);
	}

}
