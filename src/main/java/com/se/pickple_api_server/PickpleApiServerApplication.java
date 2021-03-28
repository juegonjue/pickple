package com.se.pickple_api_server;

import com.se.pickple_api_server.v1.oauth.infra.oauth.NaverOauth;
import com.se.pickple_api_server.v1.oauth.infra.oauth.SocialOauth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class PickpleApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickpleApiServerApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	List<SocialOauth> socialOauthList(){
		List<SocialOauth> socialOauthList = new ArrayList<>();
		socialOauthList.add(new NaverOauth());
		return socialOauthList;
	}
}
