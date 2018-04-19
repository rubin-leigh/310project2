package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MainControllerTest.class, LoginTest.class, TestBCrypt.class, SignUpTest.class, CollagesNotFoundBugTest.class})
public class AllTests {

}

