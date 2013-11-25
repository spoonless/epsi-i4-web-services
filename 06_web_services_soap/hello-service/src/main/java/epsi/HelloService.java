package epsi;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;
import javax.xml.ws.Holder;

//L'annotation Stateless est nécessaire pour TomEE
@Stateless
@WebService
public class HelloService {

	public String sayHello(@WebParam(name = "who") String name, 
			@WebParam(header=true, name="serverTime", mode=Mode.OUT) Holder<Calendar> serverTime) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("You must specify a name!");
		}
		serverTime.value = Calendar.getInstance();
		return "Hello " + name;
	}

}