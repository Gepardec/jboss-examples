package at.gepardec.ejbtest.remote_ejb;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.Remote;
import javax.ejb.Stateless;


@Resources( {
@Resource(name="java:/services/StringConverter", lookup="java:module/ConverterBean"),
})
@Stateless
@Remote(StringConverter.class)
public class ConverterBean implements StringConverter{

	@Override
	public String toLowerCase(String input) {
		return input.toLowerCase();
	}

	@Override
	public String toUpperCase(String input) {
		return input.toUpperCase();
	}

	
}
