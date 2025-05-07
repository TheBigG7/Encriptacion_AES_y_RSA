package guia.seguridad.middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Set;

@Component // Componente de Spring para inyección
public class UrlFilter implements Filter {

    private final Set<String> allowedIps = Set.of("127.0.0.1", "0:0:0:0:0:0:0:1");


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();
        String clientIp = req.getRemoteAddr();

        System.out.println("Solicitud entrante: " + path + " desde IP: " + clientIp);

        if (path.startsWith("/api/crypto") && !allowedIps.contains(clientIp)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado desde IP: " + clientIp);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
