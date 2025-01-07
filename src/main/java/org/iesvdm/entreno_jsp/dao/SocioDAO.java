package org.iesvdm.entreno_jsp.dao;

import org.iesvdm.entreno_jsp.model.Socio;

import java.util.List;
import java.util.Optional;

public interface SocioDAO {

    public void create(Socio socio);

    public List<Socio> getAll();

    public void update(Socio socio);

    public void delete(int id);

    public Optional<Socio> find(int id);

}
