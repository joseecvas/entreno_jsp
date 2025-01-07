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
import java.util.List;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "GrabarSociosServlet", value = "/GrabarSociosServlet")
public class GrabarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/jsp/listadoSocios.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        Optional<Socio> optionalSocio = UtilServlet.validaGrabar(request);
        if(optionalSocio.isPresent()){
            Socio socio = optionalSocio.get();
            this.socioDAO.create(socio);
            List<Socio> listado = this.socioDAO.getAll();
            request.setAttribute("listado", listado);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSocios.jsp");
        }
        else{
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioGrabarSocio.jsp");
            request.setAttribute("error", "Error de validación.");
        }
        dispatcher.forward(request, response);
    }
}
