package codexstester.setup.bridge;

import codexstester.engine.bridge.CodexsTesterCoreBridgeTests;
import com.huntercodexs.demojobs.SpringBatchJobDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBatchJobDemoApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class EnrollmentValidationBridgeTests extends CodexsTesterCoreBridgeTests {

    protected EnrollmentValidationBridgeTests() {
        super("enrollmentvalidation/");
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}