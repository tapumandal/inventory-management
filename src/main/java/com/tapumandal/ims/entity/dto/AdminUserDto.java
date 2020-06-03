package com.tapumandal.ims.entity.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
public class AdminUserDto{

    @Valid
    private UserDto userDto;

    @Valid
    private CompanyDto companyDto;

    public AdminUserDto(){};

    public AdminUserDto(UserDto userDto, CompanyDto companyDto) {
        this.userDto = userDto;
        this.companyDto = companyDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }
}
