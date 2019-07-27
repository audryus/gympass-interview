package gympass.interview;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;

import org.junit.Test;

public class MainTest {
	
	@Test
	public void test() throws FileNotFoundException, ParseException {
		URL resource = getClass().getClassLoader().getResource("corrida.txt");
		String args[] = {resource.getPath()};
		Main.main(args);
	}
	
}
