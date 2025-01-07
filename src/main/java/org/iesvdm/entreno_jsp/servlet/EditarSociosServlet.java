package org.iesvdm.entreno_jsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.entreno_jsp.dao.SocioDAO;
import org.iesvdm.entreno_jsp.dao.SocioDAOImpl;
import org.iesvdm.entreno_jsp.model.Socio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet (name = "EditarSociosServlet", value = "/EditarSociosServlet")
public class EditarSociosServlet extends HttpServlet {
    SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarSocio.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        Optional<Socio> optionalSocio = UtilServlet.validaEditar(request);

        if(optionalSocio.isPresent()){
            socioDAO.update(optionalSocio.get());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSocios.jsp");
            List<Socio> listado = socioDAO.getAll();
            request.setAttribute("listado", listado);
        }
        else{
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarSocio.jsp");
            request.setAttribute("error", "Error de validación.");
        }
        dispatcher.forward(request, response);
    }
}
