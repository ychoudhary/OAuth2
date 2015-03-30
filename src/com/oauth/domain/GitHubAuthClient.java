package com.oauth.domain;

public class GitHubAuthClient {

	public static String GIT_ACCESS_URL = "https://github.com/login/oauth/";
	private String clientId;
	private String clientSecret;
	private String callbackUrl;

	public GitHubAuthClient(String clientId, String clientSecret,
			String callbackUrl) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.callbackUrl = callbackUrl;
	}

	public String getAuthServerUrl() {
		return serverUrl("authorize");
	}

	public String getAccessTokenUrl(String code) {
		return serverUrl("access_token") + "&code="+code+"&client_secret="+clientSecret;
	}

	private String serverUrl(String endPoint) {
		return GIT_ACCESS_URL + endPoint + "?client_id=" + this.clientId + "&redirect_uri=" + this.callbackUrl;
	}

}
