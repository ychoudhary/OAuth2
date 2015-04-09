package com.oauth.domain;

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
		return serverUrl("authorize");
	}

	public String getAccessTokenUrl(String code) {
		return serverUrl("token") + "&code="+code+"&client_secret="+clientSecret;
	}

	private String serverUrl(String endPoint) {
		return ISAM_ACCESS_URL + endPoint + "?client_id=" + this.clientId + "&redirect_uri=" + this.callbackUrl;
	}

}
