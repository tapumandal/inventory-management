package com.tapumandal.ims.service.implementation;

import com.google.gson.Gson;
import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.User;
import com.tapumandal.ims.entity.dto.UserDto;
import com.tapumandal.ims.repository.UserRepository;
import com.tapumandal.ims.service.CompanyService;
import com.tapumandal.ims.service.UserService;
import com.tapumandal.ims.util.ApplicationPreferences;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    ApplicationPreferences applicationPreferences;

    private User user;

    public UserServiceImpl(){}

    public UserServiceImpl(User user){
        this.user = user;
    }


    @Override
    public User createUser(UserDto userDto) {

        if(userDto.getCompany() != null){
            return createAdminAccount(userDto);
        }else{
            return createUserAccount(userDto);
        }
    }

    private User createAdminAccount(UserDto userDto) {
        Company company = new Company();
        company.setId( companyService.create( userDto.getCompany() ).getId() );
        User u = new User(userDto);
        u.setCompany(company);
        u.setRole("ADMIN");

        u = this.checkUsernameType(u);


        Optional<User> user;
//        try{
        int userId = userRepository.create(u);

        applicationPreferences.saveUserByUsername(u.getUsername());

        user = Optional.ofNullable(userRepository.getById(userId));
//        }catch (Exception e){
//            return null;
//        }

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    private User createUserAccount(UserDto userDto) {
        User u = new User(userDto);
        Company company = new Company();
        company.setId(ApplicationPreferences.getUser().getCompany().getId());
        u.setCompany(company);

        Optional<User> user;
//        try{
        int userId = userRepository.create(u);

        user = Optional.ofNullable(userRepository.getById(userId));
//        }catch (Exception e){
//            return null;
//        }

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }


    @Override
    public User create(UserDto u) {
        return null;
    }

    @Override
    public User update(UserDto userDto) {

        User u = new User(userDto);

        Optional<User> user;
        try{
            int userId = userRepository.update(u);
            user = Optional.ofNullable(userRepository.getById(userId));
        }catch (Exception e){
            return null;
        }

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }

    }

    @Override
    public List<User> getAll(Pageable pageable) {
        Optional<List<User>> products = Optional.ofNullable(userRepository.getAll(pageable));

        if(products.isPresent()){
            return products.get();
        }else{
            return null;
        }
    }


    @Override
    public User getById(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.getById(id));

        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }

    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.getUserByUserName(username));
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return userRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public User getByValue(String key, String value) {
        User user = userRepository.getByKeyAndValue(key, value).get(0);

        return user;
    }

    @Override
    public List<User> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.getById(id));
        if(user.isPresent()){
            if(user.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return user.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return userRepository.getPageable(pageable);
    }

    public boolean isUserExist(String userName){
        return userRepository.isUserExist(userName);
    }

    protected User checkUsernameType(User u){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(u.getUsername());
        if(mat.matches()){
            u.setUsernameType("EMAIL");
        }else{
            u.setUsername(u.getUsername().replace("+88", ""));
            u.setUsername(u.getUsername().replace("88", ""));

//            Pattern pattern2 = Pattern.compile("\\d{11}$");
//            Matcher mat2 = pattern2.matcher(u.getUsername());
//            if(mat.matches()) {
            u.setUsernameType("MOBILE");
//            }
        }
        return u;
    }
}
