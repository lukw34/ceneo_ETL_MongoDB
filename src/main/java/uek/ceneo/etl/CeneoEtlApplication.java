package uek.ceneo.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Główna klasa odpowiedzialna za uruchomienie aplikacji. Wiecej szczegolow odnosnie aplikacji w README.md
 */
@SpringBootApplication
public class CeneoEtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeneoEtlApplication.class, args);
	}
}
