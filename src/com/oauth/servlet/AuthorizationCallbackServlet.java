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
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizationCallbackServlet extends AbstractOAuthServlet {

    private static final long serialVersionUID = -5013470069334912862L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	System.err.println(req.getQueryString());
        	System.err.println(req.getRequestURI());
            String token = null;
            String responseBody = null;
           if(req.getParameter("code") != null) {
        	   HttpClient httpclient = new DefaultHttpClient();
        	   String authCode= req.getParameter("code");
        	   ResponseHandler<String> responseHandler = new BasicResponseHandler();
               try {
            	   if (req.getRequestURI().indexOf("git") > 0) {
            		   HttpGet httpget = new HttpGet(client.getAccessTokenUrl(authCode));
            		   
                       responseBody = httpclient.execute(httpget, responseHandler);
                       int accessTokenStartIndex = responseBody.indexOf("access_token=") + "access_token=".length();
                       token = responseBody.substring(accessTokenStartIndex,responseBody.indexOf("&",accessTokenStartIndex));
                       
					} else if (req.getRequestURI().indexOf("isam") > 0) {
						System.err.println(iSAMClient.getAccessTokenUrl());
						HttpPost httpPost = new HttpPost(iSAMClient.getAccessTokenUrl());
						httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
						
						System.err.println("Post Params--------");
						for (Iterator<NameValuePair> postParamIter = iSAMClient.getPostParams(authCode).iterator(); postParamIter.hasNext();) {
							NameValuePair postParam = postParamIter.next();
							System.err.println(postParam.getName() +"="+postParam.getValue());
						}
						httpPost.setEntity(new UrlEncodedFormEntity(iSAMClient.getPostParams(authCode)));
						System.err.println("Post Params--------");
						responseBody = httpclient.execute(httpPost,responseHandler);
						token = parseJsonString(responseBody);
					} else {
						resp.sendError(HttpStatus.SC_FORBIDDEN);
					}
                   System.err.println(responseBody);
                   req.setAttribute("Response", responseBody);
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
    
    public static void main(String args[]) throws JSONException{
    	String jsonData = "{\"access_token\":\"0GI1fqgVMT9Hbp89iExn\",\"scope\":\"profile\",\"expires_in\":3599,\"token_type\":\"bearer\",\"refresh_token\":\"lUANXQVCAlJr1P93YW2Sc513dWn0szvQ5aBGvAzi\"}";
    	System.err.println(parseJsonString(jsonData));
    }
    
    private static String parseJsonString(String jsonData) throws JSONException{
    	final JSONObject obj = new JSONObject(jsonData);
    	return obj.getString("access_token");
    }

}