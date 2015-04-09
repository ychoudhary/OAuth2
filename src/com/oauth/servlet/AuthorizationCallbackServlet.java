/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.oauth.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class AuthorizationCallbackServlet extends AbstractOAuthServlet {

    private static final long serialVersionUID = -5013470069334912862L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = null;
           if(req.getParameter("code") != null) {
        	   HttpClient httpclient = new DefaultHttpClient();
               try {
            	   HttpGet httpget = null;
            	   if (req.getRequestURI().indexOf("git") > 0) {
            		   httpget = new HttpGet(client.getAccessTokenUrl(req.getParameter("code")));
					} else if (req.getRequestURI().indexOf("isam") > 0) {
						httpget = new HttpGet(iSAMClient.getAccessTokenUrl(req.getParameter("code")));
					} else {
						resp.sendError(HttpStatus.SC_FORBIDDEN);
					}
						//httpget = new HttpGet(client.getAccessTokenUrl(req.getParameter("code")));
                   ResponseHandler<String> responseHandler = new BasicResponseHandler();
                   String responseBody = httpclient.execute(httpget, responseHandler);
                   log(responseBody);
                   int accessTokenStartIndex = responseBody.indexOf("access_token=") + "access_token=".length();
                   token = responseBody.substring(accessTokenStartIndex,responseBody.indexOf("&",accessTokenStartIndex));
               } catch (ClientProtocolException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   httpclient.getConnectionManager().shutdown();
               }
        	   resp.sendRedirect("userDetails.jsp?token="+token);
           } /*else if(req.getParameter("access_token") != null) {
        	   Writer w = resp.getWriter();
               
               w.write("<html><body><center>");
               w.write("<h3>");
               w.write("User Code [" + req.getParameter("access_token") + "] has successfully logged in!");
               w.write("</h3>");
               w.write("</center></body></html>");
               
               w.flush();
               w.close();   
           } */else {
        	   Writer w = resp.getWriter();
               
               w.write("<html><body><center>");
               w.write("<h3>");
               w.write("UNAUTHORIZED Access!");
               w.write("</h3>");
               w.write("</center></body></html>");
               
               w.flush();
               w.close();
           }
        	
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}