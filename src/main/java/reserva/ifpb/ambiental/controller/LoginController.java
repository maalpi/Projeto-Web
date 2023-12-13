package reserva.ifpb.ambiental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reserva.ifpb.ambiental.usuario.TipoUsuario;
import reserva.ifpb.ambiental.usuario.Usuario;
import reserva.ifpb.ambiental.usuario.UsuarioRepository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/autenticar")
    public String autenticar(@RequestParam String email, String senha, Model model) {
    	Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getTipo().equals(TipoUsuario.ADMINISTRADOR) && usuario.getSenha().equals(senha)) {
            // Autenticação bem-sucedida para administrador
            return "redirect:/cadastro"; // Redireciona para a página de gerenciamento
        } else {
            model.addAttribute("erro", "Credenciais inválidas");
            return "login";
        }
    }
}
