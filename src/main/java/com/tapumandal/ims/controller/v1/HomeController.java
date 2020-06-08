package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.*;
import com.tapumandal.ims.entity.dto.*;
import com.tapumandal.ims.repository.UserRepository;
import com.tapumandal.ims.repository.implementation.UserRepositoryImpl;
import com.tapumandal.ims.service.MyUserDetailsService;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.service.CompanyService;
import com.tapumandal.ims.util.CommonResponseSingle;
import com.tapumandal.ims.util.ControllerHelper;
import com.tapumandal.ims.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class HomeController extends ControllerHelper {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myuserDetailsService;
    @Autowired
    UserDetails userDetails;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserRepositoryImpl userRepo;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserDto adminUserDto;
    @Autowired
    UserDto userDto;
    @Autowired
    CompanyDto companyDto;
    @Autowired
    User user;
    @Autowired
    CompanyService comapnyService;

    @Autowired
    Company company;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<Jwt> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

        userDetails = myuserDetailsService.loadUserByUsername(authenticationRequest.getUsername().toString());

        return ResponseEntity.ok(new Jwt(jwtUtil.generateToken(userDetails)));
    }
    
    @GetMapping("/")
    public String home() {
        return ("<h1>This is the Home Page. </h1> <span>Site is under construction.<span>");
    }

    @PostMapping(path = "/registration")
    public CommonResponseSingle userRegistration(@RequestBody @Valid UserDto userDto, HttpServletRequest request){
//        companyDto = userDto.getCompanyDto();

        if(!userService.isUserExist(userDto.getEmail())){
            User user = userService.createUser(new User(userDto));
            if(user != null){
//                comapnyService.create(new Company(companyDto));
                return response(true, HttpStatus.CREATED, "User & Company registration successful", user);
            }else{
                return response(true, HttpStatus.BAD_REQUEST, "Something is wrong. Try again or contact with service provider.", (User) null);
            }

        }else{
            return response(false, HttpStatus.NOT_ACCEPTABLE, "User already exist", (User) null);
        }
    }

    
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}