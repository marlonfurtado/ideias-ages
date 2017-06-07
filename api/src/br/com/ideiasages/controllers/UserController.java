package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.asList;

@Path("users")
public class UserController {
    private UserBO userBO = new UserBO();
    private UserDAO userDAO = new UserDAO();

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @GET
    @Path("/")
    @Produces("application/json; charset=UTF-8")
    public ArrayList<User> list(@QueryParam("role") String role) throws PersistenciaException, SQLException, NegocioException {
        ArrayList<String> roles = new ArrayList<>(asList("analyst", "idealizer"));
        ArrayList<User> users = new ArrayList<>();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        //somente usuários que não são idealizadores podem listar algum tipo de usuário
        if (!loggedUser.getRole().equals("idealizer")) {
            role = role.toLowerCase();

            //somente admin pode filtrar, avaliadores só podem listar idealizadores
            if (loggedUser.getRole().equals("administrator")) {
                if (role.equals("analyst")) {
                    roles.remove("idealizer");
                } else if (role.equals("idealizer")) {
                    roles.remove("analyst");
                }
            } else {
                roles.remove("analyst");
            }

            userDAO.getUsersByRoles(roles);
        }

        return users;
    }
}
