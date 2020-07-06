package com.tapumandal.ims.service;

import com.tapumandal.ims.util.MyPagenation;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface Service<ReqEntity, ResEntity> {

    public ResEntity create(ReqEntity reqEntity);

    public ResEntity update(ReqEntity reqEntity);

    public List<ResEntity> getAll(Pageable pageable);

    public ResEntity getById(int id);

    public boolean deleteById(int id);

    public ResEntity getByValue(String key, String value);

    public List<ResEntity> getAllByValue(String kye, String value);

    public boolean isActive(int id);

    public boolean isDeleted(int id);

    MyPagenation getPageable(Pageable pageable);

}
