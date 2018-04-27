package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MainControllerTest.class, SwitchCollageTests.class, LoginTest.class, SignUpTest.class, CollagesNotFoundBugTest.class, CollageHandlerTest.class,CollageTester.class, DeleteTests.class, ImageTransformTest.class, SaveTests.class,TestCollageModification.class})
public class AllTests {

}

