package com.example.pottery.filter;

import com.example.pottery.db2.service.HistoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
@RequiredArgsConstructor
public class IPAddressFilter implements Filter {
    private HistoryService historyService;
    public IPAddressFilter(HistoryService historyService){
        this.historyService = historyService;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("run to do filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getParameter("url");
        String ipAddress = httpRequest.getRemoteAddr();
        System.out.println(uri);

        // Call your service method to save IP address and URI
        if (uri != null){
            historyService.saveHistory(ipAddress, uri);
        }

        // Proceed with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
    public static String getURL(HttpServletRequest req) {

        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp
        String servletPath = req.getServletPath();   // /servlet/MyServlet
        String pathInfo = req.getPathInfo();         // /a/b;c=123
        String queryString = req.getQueryString();          // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
