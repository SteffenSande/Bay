package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by knutandersstokke on 02 02.10.2018.
 */
@WebService
public class Hello {
    private String message = new String("Hello, ");

    public void Hello() {
    }

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
