package com.usc.productDemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usc.productDemo.http.Response;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SecurityUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static void sendResponse(HttpServletResponse response, int status, String message, Exception exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(new Response(exception == null, status, message)));
        response.setStatus(status);
        writer.flush();
        writer.close();
    }
}