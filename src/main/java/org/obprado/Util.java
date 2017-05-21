package org.obprado;

public class Util {


    public String extractId(String uri) {
        return uri.substring(uri.lastIndexOf("/") + 1);
    }
}
