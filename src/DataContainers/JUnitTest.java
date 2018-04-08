package DataContainers;

public class JUnitTest {
	private String test;
	public JUnitTest(String test) {
		this.test = test;
	}
	public String printMessage() {
		System.out.println(test);
		return test;
	}
}
