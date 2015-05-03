package br.com.sidlar.bruno.infrastructure.security;

import br.com.sidlar.bruno.domain.Usuario;
import br.com.sidlar.bruno.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoggedUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public LoggedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user =
                usuarioRepository.buscaPorEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(String.format("Usuário com email '%s' não foi encontrado.", email))
                        );
        return new LoggedUser(user);
    }
}