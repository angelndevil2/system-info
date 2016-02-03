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
    @Override
    public long getOpenFilesHardLimit() {
        return r_limit_.getOpenFilesMax();
    }

    /**
     * @return max user process hard limit
     */
    @Override
    public long getUserProcessHardLimit() {
        return r_limit_.getProcessesMax();
    }

    /**
     *
     * @return max stack size hard limit, -1 = unlimited
     */
    @Override
    public long getStackSizeHardLimit() {
        return r_limit_.getStackMax();
    }

    /**
     * @return open file soft limit
     */
    @Override
    public long getOpenFilesSoftLimit() {
        return r_limit_.getOpenFilesCur();
    }

    /**
     * @return max user process soft limit
     */
    @Override
    public long getUserProcessSoftLimit() {
        return r_limit_.getProcessesCur();
    }

    /**
     *
     * @return max stack size soft limit, -1 = unlimited
     */
    @Override
    public long getStackSizeSoftLimit() {
        return r_limit_.getStackCur();
    }
}
