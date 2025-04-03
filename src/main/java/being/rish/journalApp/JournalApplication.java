package being.rish.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Rest API: Representational state transfer Application programming interfaces
// HTTP verb -> GET(See), PUT(Modify), POST(Create), DELETE(Delete)
// GET 172.17.18.19:8080/ntflx/plans(Http verb + IP + Port + end-point => Rest API)
@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

}
