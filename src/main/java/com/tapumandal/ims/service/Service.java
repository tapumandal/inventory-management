package com.tapumandal.ims.service;

import com.tapumandal.ims.util.MyPagenation;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface Service<ActiveSEntity> {

    public ActiveSEntity create(ActiveSEntity activeService);

    public ActiveSEntity update(ActiveSEntity activeService);

    public List<ActiveSEntity> getAll(Pageable pageable);

    public ActiveSEntity getById(int id);

    public boolean deleteById(int id);

    public ActiveSEntity getByValue(String kye, String value);

    public List<ActiveSEntity> getAllByValue(String kye, String value);

    public boolean isActive(int id);

    public boolean isDeleted(int id);

    MyPagenation getPageable(Pageable pageable);
}
