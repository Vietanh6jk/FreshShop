package iuh.dhktpm15.configuration;

import iuh.dhktpm15.entities.NguoiDung;
import iuh.dhktpm15.entities.NguoiDung_Role;
import iuh.dhktpm15.entities.Roles;
import iuh.dhktpm15.repositories.NguoiDungRepository;
import iuh.dhktpm15.repositories.NguoiDung_RoleRepository;
import iuh.dhktpm15.repositories.RoleRepository;
import iuh.dhktpm15.services.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private NguoiDung_RoleRepository nguoiDung_roleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findNguoiDungBySdt(username);

        if (nguoiDung == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(nguoiDung.get().getSdt(),
                nguoiDung.get().getPassword(), getAuthorities(nguoiDung));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Optional<NguoiDung> nguoiDung) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<NguoiDung_Role> nguoiDung_roles = nguoiDung_roleRepository.findNguoiDung_RoleByNguoiDung(nguoiDung.get().getId());
        for(NguoiDung_Role dung_role: nguoiDung_roles){
            authorities.add(new SimpleGrantedAuthority(dung_role.getRoles().getName()));
        }

        return authorities;
    }
}