package com.tapumandal.ims.util;

import com.tapumandal.ims.service.ProductService;
import com.tapumandal.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ControllerHelper<Entity> {

    @Autowired
    private   CommonResponseArray commonResponseArray;

    @Autowired
    private   CommonResponseSingle commonResponseSingle;

    @Autowired
    ApplicationPreferences applicationPreferences;

    @Autowired
    JwtUtil jwt;


    protected CommonResponseSingle response(boolean action, HttpStatus status, String message, Entity data){

        commonResponseSingle.setAction(action);
        commonResponseSingle.setStatus(status);
        commonResponseSingle.setMessage(message);
        commonResponseSingle.setData(data);

        return commonResponseSingle;
    }

    protected  CommonResponseArray<Entity> response(boolean action, HttpStatus status, String message, List<Entity> data, MyPagenation pagenation){

        commonResponseArray.setAction(action);
        commonResponseArray.setStatus(status);
        commonResponseArray.setMessage(message);
        commonResponseArray.setData(data);
        commonResponseArray.setMyPagenation(pagenation);

        return commonResponseArray;
    }

    protected MyPagenation managePagenation(HttpServletRequest request, MyPagenation pagenation, Pageable pageable) {

        int totalPage = (int) Math.ceil((float)pagenation.getTotalElement()/(float)pageable.getPageSize());



        String url = request.getRequestURI();
        String nextPage = null;
        String prePage = null;

        int pageNum = pageable.getPageNumber();
        if(pageNum<1){
            pageNum = 1;
        }

        if(pageNum < totalPage) {
            nextPage = url + "?page=" + (pageNum + 1) + "?size=" + pageable.getPageSize();
        }
        if(pageNum > 1) {
            prePage = url + "?page=" + (pageNum - 1) + "?size=" + pageable.getPageSize();
        }

        pagenation.setCurrentPage(pageNum);
        pagenation.setPageSize(pageable.getPageSize());
        pagenation.setTotalPage(totalPage);
        pagenation.setNextPageUrl(nextPage);
        pagenation.setPreviousPageUrl(prePage);

        return pagenation;
    }

    public void storeUserDetails(HttpServletRequest request){

        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        String username = getUserNameJWT(token);
        applicationPreferences.saveUserByUsername(username);
    }

    private String getUserNameJWT(String token) {
        return jwt.extractUsername(token);
    }

}
