package com.example.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.filter.ResourceFilter;

import jakarta.ws.rs.ApplicationPath;

/**
 * エントリポイント<br>
 * Jerseyの設定
 */
@Component
@ApplicationPath("/rs")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ResourceFilter.class);
		packages("com.example.resource");
	}

}