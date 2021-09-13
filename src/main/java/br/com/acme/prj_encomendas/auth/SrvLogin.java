/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acme.prj_encomendas.auth;

import br.com.acme.prj_encomendas.dao.AutenticacaoDao;
import br.com.acme.prj_encomendas.util.Navegacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frasilva
 */
public class SrvLogin extends HttpServlet {

    @Inject
    private AutenticacaoDao autenticacaoDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btn = request.getParameter("btn-login");
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String ajax = request.getParameter("ajax");

        if (Boolean.valueOf(ajax)) {
            try {
                Boolean existe = autenticacaoDao.usuarioExiste(usuario.toUpperCase());
                if (!existe) {
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"show\":true,\"message\":\"Usu\u00e1rio n\u00e3o existe\"}");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SrvLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (btn != null) {
            try {
                Auth autenticar = autenticacaoDao.autenticar(new Auth(usuario, senha));
                if (autenticar != null) {
                    request.getSession().setAttribute("usuarioLogado", usuario);
                    response.sendRedirect(request.getContextPath());
                } else {
                    request.setAttribute("show", "true");
                    request.setAttribute("message", "Usuário não cadastrado!");
                    RequestDispatcher rd = request.getRequestDispatcher(Navegacao.LOGIN);
                    rd.forward(request, response);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SrvLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
