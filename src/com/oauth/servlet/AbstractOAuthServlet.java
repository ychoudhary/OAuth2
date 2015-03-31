/**
 * 
 */

package com.oauth.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.oauth.domain.GitHubAuthClient;
import com.oauth.domain.ISAMAuthClient;

/**
 * @author Yashpal Choudhary (<a href="mailto:yash.choudhary@gmail.com">yash.choudhary@gmail.com</a>)
 * @since 
 */
public abstract class AbstractOAuthServlet extends HttpServlet {

    private static final long serialVersionUID = -5703676053950254598L;
    protected static GitHubAuthClient client;
    protected static ISAMAuthClient iSAMClient;

    //protected Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String clientId = config.getServletContext().getInitParameter("clientId");
        String clientSecret = config.getServletContext().getInitParameter("clientSecret");
        String callbackUrl = config.getServletContext().getInitParameter("callbackUrl");

        client = new GitHubAuthClient(clientId, clientSecret, callbackUrl);
        iSAMClient = new ISAMAuthClient();
    }

}
