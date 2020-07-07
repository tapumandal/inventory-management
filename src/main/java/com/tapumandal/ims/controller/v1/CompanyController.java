package com.tapumandal.ims.controller.v1;

import com.tapumandal.ims.entity.Company;
import com.tapumandal.ims.entity.dto.CompanyDto;
import com.tapumandal.ims.service.CompanyService;
import com.tapumandal.ims.util.CommonResponseArray;
import com.tapumandal.ims.util.CommonResponseSingle;
import com.tapumandal.ims.util.ControllerHelper;
import com.tapumandal.ims.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController extends ControllerHelper<Company> {

    @Autowired
    CompanyService companyService;

    @PostMapping(path = "/create")
    public CommonResponseSingle createCompany(@RequestBody @Valid CompanyDto companyDto, HttpServletRequest request) {

        storeUserDetails(request);

        Company company = companyService.create(companyDto);

        if (company != null) {
            return response(true, HttpStatus.CREATED, "New company inserted successfully", company);
        } else if (company == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong please contact", (Company) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Company) null);
    }

    @GetMapping(path = "/{id}")
    public CommonResponseSingle<Company> getCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        Company company = companyService.getById(id);

        if (company != null) {
            return response(true, HttpStatus.FOUND, "Company by id: " + id, company);
        } else if (company == null) {
            return response(false, HttpStatus.NO_CONTENT, "Company not found or deleted", (Company) null);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", (Company) null);
        }
    }

    @GetMapping(path = "/list")
    public CommonResponseArray<Company> getAll(HttpServletRequest request, Pageable pageable) {

        storeUserDetails(request);

        List<Company> companys = companyService.getAll(pageable);

        MyPagenation myPagenation = managePagenation(request, companyService.getPageable(pageable), pageable);

        if (!companys.isEmpty()) {
            return response(true, HttpStatus.FOUND, "All company list", companys, myPagenation);
        } else if (companys.isEmpty()) {
            return response(false, HttpStatus.NO_CONTENT, "No company found", new ArrayList<Company>(), myPagenation);
        } else {
            return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong", new ArrayList<Company>(), myPagenation);
        }

    }


    @PostMapping(path = "/update")
    public CommonResponseSingle updateCompany(@RequestBody CompanyDto companyDto, HttpServletRequest request) {

        storeUserDetails(request);

        Company company = companyService.update(companyDto);

        if (company != null) {
            return response(true, HttpStatus.OK, "New company inserted successfully", company);
        } else if (company == null) {
            return response(false, HttpStatus.BAD_REQUEST, "Something is wrong with data", (Company) null);
        }
        return response(false, HttpStatus.INTERNAL_SERVER_ERROR, "Something is wrong with the application", (Company) null);
    }

    @DeleteMapping(path = "/{id}")
    public CommonResponseSingle<Company> deleteCompany(@PathVariable("id") int id, HttpServletRequest request) {

        storeUserDetails(request);

        if (companyService.deleteById(id)) {
            return response(true, HttpStatus.OK, "Company by id " + id + " is deleted", (Company) null);
        } else{
            return response(false, HttpStatus.NOT_FOUND, "Company not found or deleted", (Company) null);
        }
    }

}