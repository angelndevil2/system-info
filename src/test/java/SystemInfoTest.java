import com.tistory.devilnangel.util.SystemInfo;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.SigarException;
import org.junit.Test;

/**
 * @author k, Created on 16. 1. 19.
 */
public class SystemInfoTest {

    private SystemInfo sys_info_ = new SystemInfo();

    @Test
    public void getOsName() {
        System.out.println(sys_info_.getOsName());
    }

    @Test
    public void getCpuInfo() throws SigarException {
        System.out.println(sys_info_.getCpuInfo());
    }

    @Test
    public void getMemInfo() throws SigarException {
        System.out.println(sys_info_.getMemInfo());
    }

    @Test
    public void isWindow() {
        System.out.println(sys_info_.isWindow());
    }
}
