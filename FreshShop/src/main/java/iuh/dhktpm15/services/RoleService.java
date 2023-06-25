package iuh.dhktpm15.services;



import iuh.dhktpm15.entities.Roles;

import java.util.List;

public interface RoleService {
    public List<Roles> findAll();

    public Roles findById(long id);
}
