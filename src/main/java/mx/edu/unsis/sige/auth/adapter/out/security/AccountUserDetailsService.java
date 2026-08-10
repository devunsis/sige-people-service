package mx.edu.unsis.sige.auth.adapter.out.security;

import mx.edu.unsis.sige.auth.adapter.out.persistence.entity.AccountEntity;
import mx.edu.unsis.sige.auth.adapter.out.persistence.entity.RoleEntity;
import mx.edu.unsis.sige.auth.adapter.out.persistence.repository.AccountRepository;
import mx.edu.unsis.sige.shared.infrastructure.security.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity cuenta = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        Set<String> roles = cuenta.getRoles().stream()
                .map(RoleEntity::getCode)
                .collect(Collectors.toSet());

        return new UserPrincipal(
                cuenta.getId(),
                cuenta.getUsername(),
                cuenta.getEmail(),
                cuenta.getPasswordHash(),
                cuenta.isActive(),
                roles,
                Set.of() // pendientes permisos
        );
    }
}