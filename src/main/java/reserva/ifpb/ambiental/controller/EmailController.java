package reserva.ifpb.ambiental.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import reserva.ifpb.ambiental.email.DadosDoFormulario;
import reserva.ifpb.ambiental.email.EmailService;

@Controller
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> enviarEmail(@RequestBody DadosDoFormulario formulario) {
        String para = "ambientalpicui@gmail.com"; // Substitua pelo seu e-mail do site
        String assunto = "Nova mensagem do formulário do site";
        
        emailService.enviarEmail(para, assunto, "De: " + formulario.email() + "\n\n" + formulario.mensagem());
        return ResponseEntity.ok("E-mail enviado com sucesso!"); // Página de confirmação
    }
}