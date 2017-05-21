package org.obprado;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = new Server(port);

        ResourceHandler resourceHandler = htmlResourceHandler();
        setHandler(server, resourceHandler);

        server.start();
    }

    private static void setHandler(Server server, ResourceHandler resourceHandler) {
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resourceHandler, new DefaultHandler() });
        server.setHandler(handlers);
    }

    private static ResourceHandler htmlResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{ "index.html" });
        resourceHandler.setResourceBase("src/main/resources/");
        return resourceHandler;
    }

}
