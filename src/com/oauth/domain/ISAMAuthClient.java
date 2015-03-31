package com.oauth.domain;

public class ISAMAuthClient {

	private static String ISAM_ACCESS_URL = "https://github.com/login/oauth/";
	private static String clientId = "";
	private static String clientSecret = "";
	private static String callbackUrl = "";

	public String getAuthServerUrl() {
		return serverUrl("authorize");
	}

	public String getAccessTokenUrl(String code) {
		return serverUrl("access_token") + "&code="+code+"&client_secret="+clientSecret;
	}

	private String serverUrl(String endPoint) {
		return ISAM_ACCESS_URL + endPoint + "?client_id=" + this.clientId + "&redirect_uri=" + this.callbackUrl;
	}

}
