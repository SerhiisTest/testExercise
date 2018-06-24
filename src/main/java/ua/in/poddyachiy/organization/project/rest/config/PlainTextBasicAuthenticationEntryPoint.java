package ua.in.poddyachiy.organization.project.rest.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author spid
 * @since
 */
@Component
public class PlainTextBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence
            (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {

        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Baeldung");
        super.afterPropertiesSet();
    }
}
