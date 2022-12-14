package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Profile;
import ru.kata.spring.boot_security.demo.repo.ProfileRepo;
import ru.kata.spring.boot_security.demo.repo.RoleRepo;

import java.util.List;

@Service
public class ProfileService implements UserDetailsService {
    private ProfileRepo profileRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileService(ProfileRepo profileRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.profileRepo = profileRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepo.getProfilesByLogin(username);
    }

    public Profile getUserByName(String name){
        return profileRepo.getProfilesByLogin(name);
    }

    public List<Profile> getAllUsers() {
        return profileRepo.findAll();
    }

    public Profile getUserById(Long id) {
        return profileRepo.getProfileById(id);
    }

    public void saveProfile(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        System.out.println(profile.getPassword());
        profileRepo.save(profile);
    }

    public void updateProfile(Profile profile) {
        profileRepo.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepo.deleteById(id);
    }
}
