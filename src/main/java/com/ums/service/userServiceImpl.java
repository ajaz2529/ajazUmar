package com.ums.service;

import com.ums.entity.AppUser;
import com.ums.payload.UserDTO;
import com.ums.payload.loginDTO;
import com.ums.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@RequiredArgsConstructor

@Service
public class userServiceImpl implements userService{

    private AppUserRepository userRepository;
    private JWTservice jwTservice;

    public userServiceImpl(AppUserRepository userRepository, JWTservice jwTservice) {
        this.userRepository = userRepository;
        this.jwTservice = jwTservice;
    }

    @Override
    public void addUser(UserDTO user) {
        AppUser appuser = new AppUser();
        appuser.setId(user.getId());
        appuser.setName(user.getName());
        appuser.setUsername(user.getUsername());
        appuser.setEmailId(user.getEmailId());
//        appuser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //another way to encrypt password more securely is
        appuser.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(5)));
        appuser.setUserRole(user.getUserRole());
        userRepository.save(appuser);
    }

    @Override
    public String VerifyLogin(loginDTO logindto) {
        Optional<AppUser> OpUser = userRepository.findByUsername(logindto.getUsername());
        if(OpUser.isPresent()){
            AppUser appUser = OpUser.get();
            if(BCrypt.checkpw(logindto.getPassword(), appUser.getPassword())){
                return jwTservice.generateToken(appUser);
            }
        }

        return null;
    }

}
