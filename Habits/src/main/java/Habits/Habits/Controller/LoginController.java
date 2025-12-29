package Habits.Habits.Controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
	@GetMapping("/")
    public void inicio(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html"); // envia usuário direto para login
    }
}
