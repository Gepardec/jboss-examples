package at.gepardec.ejbtest.remote_ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;



@Stateless
@Remote(StringConverter.class)
public class Converter implements StringConverter{

	@Override
	public String toLowerCase(String input) {
		return input.toLowerCase();
	}

	@Override
	public String toUpperCase(String input) {
		return input.toUpperCase();
	}

	
}
