package mx.edu.unsis.sige.auth.adapter.out.security;

import mx.edu.unsis.sige.auth.adapter.out.persistence.entity.AccountEntity;
import mx.edu.unsis.sige.auth.adapter.out.persistence.repository.AccountRepository;
import mx.edu.unsis.sige.shared.infrastructure.security.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AccountEntity cuenta = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new UserPrincipal(
                cuenta.getId(),
                cuenta.getUsername(),
                cuenta.getEmail(),
                cuenta.getPasswordHash(),
                cuenta.isActive(),
                cuenta.getRoles().stream().map(rol -> rol.getCode()).collect(Collectors.toSet()),
                Set.of() // permisos: pendiente hasta implementar PermissionRepository
        );
    }
}