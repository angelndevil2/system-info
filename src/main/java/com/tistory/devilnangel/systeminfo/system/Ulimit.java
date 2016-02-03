package com.tistory.devilnangel.systeminfo.system;

import com.tistory.devilnangel.systeminfo.common.IRmiUlimitInfo;
import com.tistory.devilnangel.systeminfo.util.SigarInstances;
import org.hyperic.sigar.ResourceLimit;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * Os's ulimit checker
 *
 * @author k, Created on 16. 1. 23.
 */
public class Ulimit implements IRmiUlimitInfo {

    private transient static final Sigar sigar_ = SigarInstances.SIGAR;
    private transient ResourceLimit r_limit_;

    public Ulimit() throws SigarException {
        r_limit_ = sigar_.getResourceLimit();
    }

    /**
     * @return open file hard limit
     */
    public long getMaxOpenFilesHardLimit() {
        return r_limit_.getOpenFilesMax();
    }

    /**
     * @return max user process hard limit
     */
    public long getMaxUserProcessHardLimit() {
        return r_limit_.getProcessesMax();
    }

    /**
     *
     * @return max stack size hard limit, -1 = unlimited
     */
    public long getMaxStackSizeHardLimit() {
        return r_limit_.getStackMax();
    }

    /**
     * @return open file soft limit
     */
    public long getMaxOpenFilesSoftLimit() {
        return r_limit_.getOpenFilesCur();
    }

    /**
     * @return max user process soft limit
     */
    public long getMaxUserProcessSoftLimit() {
        return r_limit_.getProcessesCur();
    }

    /**
     *
     * @return max stack size soft limit, -1 = unlimited
     */
    public long getMaxStackSizeSoftLimit() {
        return r_limit_.getStackCur();
    }
}
