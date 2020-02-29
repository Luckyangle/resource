
import com.giant.resource.ResourceApplication;
import org.junit.runner.RunWith;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Blade单元测试
 *
 * @author Chill
 */
@RunWith(BladeSpringRunner.class)
@SpringBootTest(classes = ResourceApplication.class)
@BladeBootTest(appName = "blade-resource", profile = "test", enableLoader = true)
public class BladeDemoTest {


}
