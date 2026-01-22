package com.SpringBoot.miniFlyway.interfaces;

import java.util.List;

public interface BasicService<Dto, Id> {

    public List<Dto> getAll();

    public Dto getById(Id id);

    public Dto save(Dto dto);

    public Dto update(Dto dto);

    public void deleteById(Id id);
}
