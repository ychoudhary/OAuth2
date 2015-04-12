package com.oauth.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ISAMAuthClient {

	private static String ISAM_ACCESS_URL = "https://csolabnh004.dev.att.com/mga/sps/oauth/oauth20/";
	private String clientId;
	private String clientSecret;
	private String callbackUrl;

	public ISAMAuthClient(String clientIdISAM, String clientSecretISAM,
			String callbackUrlISAM) {
		this.clientId = clientIdISAM;
		this.clientSecret = clientSecretISAM;
		this.callbackUrl = callbackUrlISAM;
	}

	public String getAuthServerUrl() {
		return ISAM_ACCESS_URL + "authorize?response_type=code&scope=profile&client_id=" + this.clientId + "&redirect_uri=" + this.callbackUrl;
	}

	public String getAccessTokenUrl() {
		return ISAM_ACCESS_URL + "token";
	}
	
	public ArrayList<NameValuePair> getPostParams(String authCode){
		
		ArrayList<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("code", authCode));
		postParams.add(new BasicNameValuePair("response_type", "code"));
		postParams.add(new BasicNameValuePair("scope", "profile"));
		postParams.add(new BasicNameValuePair("client_id", this.clientId));
		postParams.add(new BasicNameValuePair("redirect_uri", this.callbackUrl));
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_secret", this.clientSecret));
		postParams.add(new BasicNameValuePair("oauth_mode", "oauth_mode_access"));
		return postParams;
		
	}
	
	

	

}
