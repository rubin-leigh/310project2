package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CollageHandlerTest.class, ImageTransformTest.class, MainControllerTest.class, LoginTest.class, TestBCrypt.class})
public class AllTests {

}

