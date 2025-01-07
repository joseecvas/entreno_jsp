package org.iesvdm.entreno_jsp.dao;

import org.iesvdm.entreno_jsp.model.Socio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SocioDAOImpl extends AbstractDAOImpl implements SocioDAO {


    @Override
    public void create(Socio socio) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys;

        try{
            conn = connectDB();
            ps= conn.prepareStatement("INSERT INTO socio (nombre, estatura, edad, localidad) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, socio.getNombre());
            ps.setInt(idx++, socio.getEstatura());
            ps.setInt(idx++, socio.getEdad());
            ps.setString(idx, socio.getLocalidad());
            int rows = ps.executeUpdate();
            if(rows == 0){
                System.out.println("Insert de Socio; 0 filas actualizadas.");
            }
            rsGenKeys = ps.getGeneratedKeys();
            if(rsGenKeys.next()){
                socio.setSocioID(rsGenKeys.getInt(1));
            }

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<Socio> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Socio> listSocio = new ArrayList<>();

        try{
            conn = connectDB();
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM socio");
            while(rs.next())
            {
                Socio socio = new Socio();

                socio.setSocioID(rs.getInt("socioID"));
                socio.setNombre(rs.getString("nombre"));
                socio.setEstatura(rs.getInt("estatura"));
                socio.setEdad(rs.getInt("edad"));
                socio.setLocalidad(rs.getString("localidad"));
                listSocio.add(socio);
            }
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listSocio;
    }

    @Override
    public void update(Socio socio) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = connectDB();

            ps= conn.prepareStatement("UPDATE socio SET nombre = ?, estatura = ?, edad = ?, localidad = ? WHERE socioID =  ? ");
            int idx = 1;

            ps.setString(idx++, socio.getNombre());
            ps.setInt(idx++, socio.getEstatura());
            ps.setInt(idx++, socio.getEdad());
            ps.setString(idx++, socio.getLocalidad());
            ps.setInt(idx, socio.getSocioID());

            int rows = ps.executeUpdate();
            if(rows == 0){
                System.out.println("Update de Socio; 0 filas actualizadas.");
            }

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = connectDB();

            ps= conn.prepareStatement("DELETE FROM socio WHERE socioID =  ? ");
            int idx = 1;
            ps.setInt(idx, id);
            int rows = ps.executeUpdate();
            if(rows == 0){
                System.out.println("Delete de Socio; 0 registros eliminados.");
            }

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public Optional<Socio> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Socio> listSocio = new ArrayList<>();

        try{
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM socio where socioID = ?");
            int idx = 1;
            ps.setInt(idx, id);
            rs = ps.executeQuery();

            if(rs.next())
            {
                Socio socio = new Socio();

                socio.setSocioID(rs.getInt("socioID"));
                socio.setNombre(rs.getString("nombre"));
                socio.setEstatura(rs.getInt("estatura"));
                socio.setEdad(rs.getInt("edad"));
                socio.setLocalidad(rs.getString("localidad"));
                return Optional.of(socio);
            }
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
        return Optional.empty();
    }
}
