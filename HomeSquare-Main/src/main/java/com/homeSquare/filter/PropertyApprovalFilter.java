package com.homeSquare.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PropertyApprovalFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        // Check if the request is for reading property data
        if (path.startsWith("/api/properties/") && path.endsWith("/approve")) {
            // Extract propertyId from the request path
            String[] pathParts = path.split("/");
            if (pathParts.length >= 4) {
                Long propertyId = Long.parseLong(pathParts[3]);

                // Check if the property is approved before allowing the request to proceed
                if (!isPropertyApproved(propertyId)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Property not approved");
                    return;
                }
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    private boolean isPropertyApproved(Long propertyId) {
        // You can implement logic to check if the property with the given ID is approved
        // This might involve querying the database or using some other criteria
        // For simplicity, let's assume all properties are approved in this example
        return true;
    }
}

