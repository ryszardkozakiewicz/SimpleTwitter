package controllers.servlets;

import controllers.servlets.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(ServletUtils.LOGIN_COOKIE) || cookie.getName().equals(ServletUtils.PASSWORD_COOKIE)) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }


}
