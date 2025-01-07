package org.iesvdm.entreno_jsp.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.entreno_jsp.model.Socio;

import java.util.Objects;
import java.util.Optional;

public class UtilServlet {
    public static Optional<Socio> validaGrabar(HttpServletRequest request){
        String nombre = null;
        int estatura = -1;
        int edad = -1;
        String localidad = null;
        try{
            nombre = request.getParameter("nombre");
            estatura = Integer.parseInt(request.getParameter("estatura"));
            edad = Integer.parseInt(request.getParameter("edad"));
            localidad = request.getParameter("localidad");

            Objects.requireNonNull(nombre);
            if(nombre.isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");

            Objects.requireNonNull(localidad);
            if(localidad.isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            return Optional.of(new Socio(-1, nombre, estatura, edad, localidad));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<Socio> validaEditar(HttpServletRequest request){
        String nombre = null;
        int estatura = -1;
        int edad = -1;
        String localidad = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try{

            nombre = request.getParameter("nombre");
            estatura = Integer.parseInt(request.getParameter("estatura"));
            edad = Integer.parseInt(request.getParameter("edad"));
            localidad = request.getParameter("localidad");

            Objects.requireNonNull(nombre);
            if(nombre.isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");

            Objects.requireNonNull(localidad);
            if(localidad.isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            return Optional.of(new Socio(id, nombre, estatura, edad, localidad));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
