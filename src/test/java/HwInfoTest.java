import com.tistory.devilnangel.util.HwInfo;
import org.hyperic.sigar.SigarException;
import org.junit.Test;

/**
 * @author k, Created on 16. 1. 19.
 */
public class HwInfoTest {

    private HwInfo hw_info_ = new HwInfo();

    @Test
    public void getOsName() {
        System.out.println(hw_info_.getOsName());
    }

    @Test
    public void getCpuInfo() throws SigarException {
        System.out.println(hw_info_.getCpuInfo());
    }

    @Test
    public void getMemInfo() throws SigarException {
        System.out.println(hw_info_.getMemInfo());
    }
}
