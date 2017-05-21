package org.obprado;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = new Server(port);

        ResourceHandler resourceHandler = htmlResourceHandler();
        setHandler(server, resourceHandler, nextQuoteServletHandler());

        server.start();
    }

    private static ServletContextHandler nextQuoteServletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new NextQuoteHttpServlet()), "/quotes/*");
        return servletHandler;
    }

    private static class NextQuoteHttpServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.getWriter().write(String.format(
                    "{\n" +
                            "  \"id\": %s,\n" +
                            "  \"sentence\": \"Espana es el mejor pais para hacerse rico\",\n" +
                            "  \"party\": \"PSOE\",\n" +
                            "  \"explanation\": \"Carlos Solchaga Catalán (Tafalla, 28 de mayo de 1944) es un economista, político español y exdirigente del Partido Socialista de Navarra y del Partido Socialista Obrero Español. Fue ministro de Economía de España.\"\n" +
                            "}",
                    //TODO extract all fields from a file/database instead of hardcoding them
                    new Util().extractId(request.getRequestURI())));
        }

    }

    private static void setHandler(Server server, ResourceHandler resourceHandler, ServletContextHandler nextQuoteServletHandler) {
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resourceHandler,nextQuoteServletHandler, new DefaultHandler() });
        server.setHandler(handlers);
    }

    private static ResourceHandler htmlResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{ "index.html" });
        resourceHandler.setResourceBase("./src/main/resources/");
        return resourceHandler;
    }

}
