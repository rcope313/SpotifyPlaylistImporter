package config;

import auth.HttpServerAuthorization;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Step2PostExchangeConfiguration {

    public String grantType;
    public String code;
    public String redirectUri;
    @JsonIgnore
    public String clientId;
    @JsonIgnore
    public String clientSecret;

    public Step2PostExchangeConfiguration (String code) {
        this.grantType = "authorization_code";
        this.code = code;
        this.redirectUri = new HttpServerAuthorization().config.getRedirectUri();
        this.clientId = "58f5ea655fc64389ae8f53047aa14201";
        this.clientSecret = "fe103f0d879048da899baea87e5402a9";
    }

    @JsonIgnore
    public String getHeaderParameter () {
        return "Authorization: Basic " + clientId + ":" + clientSecret;
    }

    public static String getRequestBodyParameter (Step2PostExchangeConfiguration object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);

    }


}
