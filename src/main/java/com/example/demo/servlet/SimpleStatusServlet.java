// src/main/java/com/example/demo/servlet/SimpleStatusServlet.java
package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/status")
public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("OK");
    }
}
