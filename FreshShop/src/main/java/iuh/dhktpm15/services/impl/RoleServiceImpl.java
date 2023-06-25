package iuh.dhktpm15.services.impl;

;
import iuh.dhktpm15.entities.Roles;
import iuh.dhktpm15.repositories.RoleRepository;
import iuh.dhktpm15.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Roles findById(long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
