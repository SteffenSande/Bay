package util;

import javax.faces.context.FacesContext;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.awt.datatransfer.DataFlavor;

public class Session {
    static FacesContext context = FacesContext.getCurrentInstance();
    static HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    public static HttpServletRequest getRequest(){
        return request;
    }

    public static String getUserName(){

        return request.getUserPrincipal().getName();
    }
}
