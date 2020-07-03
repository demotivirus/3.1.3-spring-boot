package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleRepository;
import web.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public Optional<Role> getOne(Long id) {
        return roleRepository.findById(id);
    }

    public void addNew(Role role) {
        roleRepository.save(role);
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void delete(Long Id) {
        roleRepository.deleteById(Id);
    }

}
