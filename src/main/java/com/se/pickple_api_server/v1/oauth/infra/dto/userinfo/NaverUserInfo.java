package com.se.pickple_api_server.v1.oauth.infra.dto.userinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverUserInfo implements OauthUserInfo{

    String id;
    String name;
    String email;
    String mobile;

    @JsonProperty("response")
    private void unpack(Map<String, String> response) {
        id = response.get("id");
        name = response.get("name");
        email = response.get("email");
        //mobile = response.get("mobile");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getEmail() {return email;}

    //@Override
    //public String getMobile() {return mobile;}

    @Override
    public String getName() {return name;}

}
