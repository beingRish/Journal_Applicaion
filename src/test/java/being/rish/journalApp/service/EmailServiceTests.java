package being.rish.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendEmail(
                "carnation_duchess348@slmail.me",
                "Testing Java mail sender",
                "Hi, app kaise hain?"
        );
    }
}
