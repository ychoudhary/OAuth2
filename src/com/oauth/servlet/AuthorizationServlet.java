/**
 * 
 */

package com.oauth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Misagh Moayyed (<a href="mailto:mmoayyed@unicon.net">mmoayyed@unicon.net</a>)
 */
public class AuthorizationServlet extends AbstractOAuthServlet {

    private static final long serialVersionUID = -183843216976018337L;
	

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
			resp.sendRedirect(client.getAuthServerUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
