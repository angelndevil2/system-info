package com.tistory.devilnangel.util;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

/**
 * @author k, Created on 16. 1. 24.
 */
public class SigarInstances {
    public final static Sigar SIGAR = new Sigar();
    public final static SigarProxy SIGAR_PROXY = SigarProxyCache.newInstance(SIGAR, 500);
}
