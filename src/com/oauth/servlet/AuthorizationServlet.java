/**
 * 
 */

package com.oauth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

/**
 * @author Yashpal Choudhary (<a href="mailto:yash.choudhary@gmail.com">yash.choudhary@gmail.com</a>)
 */
public class AuthorizationServlet extends AbstractOAuthServlet {

    private static final long serialVersionUID = -183843216976018337L;
	

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if (req.getParameter("git") != null) {
				resp.sendRedirect(client.getAuthServerUrl());
			} else if (req.getParameter("isam") != null) {
				resp.sendRedirect(iSAMClient.getAuthServerUrl());
			} else {
				resp.sendError(HttpStatus.SC_FORBIDDEN);
			}
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
