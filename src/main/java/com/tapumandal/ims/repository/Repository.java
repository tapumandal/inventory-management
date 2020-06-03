package com.tapumandal.ims.repository;

import org.hibernate.Session;

import java.util.List;

public interface Repository<Entity> {

    public Session getSession();

    public Entity create(Entity o);

    public Entity update(Entity o);

    public List<Entity> getAll();

    public Entity getById(int id);

    public List<Entity> getByKeyAndValue(String kye, String value);

    public boolean delete(int id);
}
