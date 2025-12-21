package com.example.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.example.model.ErrorResponse;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ResourceFilter implements ContainerRequestFilter {

	private static final String API_KEY_HEADER = "X-AUTH-TOKEN";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		ErrorResponse response = new ErrorResponse();
		try {
			String path = requestContext.getUriInfo().getPath();
			if (!path.contains("/rs")) {
				System.out.println("path:" + path);
				return;
			}
			String apiKey = "token";
			String headerApiKey = requestContext.getHeaderString(API_KEY_HEADER);
			if (StringUtils.isEmpty(headerApiKey)) {
				throw new Exception();
			}
			if (apiKey.equals(headerApiKey)) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setErrorCode("ER001");
			response.setErrorMessage("認証に失敗しました。");
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(response)
					.type(MediaType.APPLICATION_JSON).build());
		}

	}

}
