package com.tapumandal.ims.util;

import com.tapumandal.ims.entity.CommonResponseArray;
import com.tapumandal.ims.entity.CommonResponseSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;

public class ControllerHelper<Entity> {

    @Autowired
    private   CommonResponseArray commonResponseArray;

    @Autowired
    private   CommonResponseSingle commonResponseSingle;

    protected CommonResponseSingle response(boolean action, HttpStatus status, String message, Entity data){

        commonResponseSingle.setAction(action);
        commonResponseSingle.setStatus(status);
        commonResponseSingle.setMessage(message);
        commonResponseSingle.setData(data);

        return commonResponseSingle;
    }

    protected  CommonResponseArray<Entity> response(boolean action, HttpStatus status, String message, List<Entity> data){

        commonResponseArray.setAction(action);
        commonResponseArray.setStatus(status);
        commonResponseArray.setMessage(message);
        commonResponseArray.setData(data);

        return commonResponseArray;
    }
}
