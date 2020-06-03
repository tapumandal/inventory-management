package com.tapumandal.ims.service;

import com.tapumandal.ims.entity.Product;

import java.util.List;


public interface Service<ActiveSEntity> {

    public List<ActiveSEntity> create(ActiveSEntity activeService);

    public ActiveSEntity update(ActiveSEntity activeService);

    public List<ActiveSEntity> getAll();

    public ActiveSEntity getById(int id);

    public ActiveSEntity getByValue(String kye, String value);

    public List<ActiveSEntity> getAllByValue(String kye, String value);

    public boolean isActive(int id);

    public boolean isDeleted(int id);
}
