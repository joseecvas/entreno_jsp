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

@WebServlet (name = "BorrarSociosServlet", value = "/BorrarSociosServlet")
public class BorrarSociosServlet extends HttpServlet {
SocioDAO socioDAO = new SocioDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSocios.jsp");
        socioDAO.delete(Integer.parseInt(request.getParameter("id")));

        List<Socio> listado = this.socioDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);
    }
}
