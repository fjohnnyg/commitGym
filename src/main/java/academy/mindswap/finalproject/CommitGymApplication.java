package academy.mindswap.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CommitGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitGymApplication.class, args);
	}

}
