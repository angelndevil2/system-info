package com.tistory.devilnangel.systeminfo.common;

import javax.management.*;
import javax.management.openmbean.TabularData;
import javax.naming.NamingException;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author k, Created on 15. 5. 12.
 */
public class LocalRuntimeMXBeanConnection extends MBeanConnection {

    private ObjectName object_name_;

    public LocalRuntimeMXBeanConnection() throws IOException, NamingException, MalformedObjectNameException {

        object_name_ = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME);

    }

    public String getVmVersion() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String) getAttribute(object_name_, "VmVersion");
    }

    public Long getVmUptime() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (Long) getAttribute(object_name_, "Uptime");
    }

    public String getVmName() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "VmName");

    }

    public String getVmVendor() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String) getAttribute(object_name_, "VmVendor");
    }

    public String getBootClassPath() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String) getAttribute(object_name_, "BootClassPath");

    }

    public String getClassPath()
            throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String) getAttribute(object_name_, "ClassPath");
    }

    @SuppressWarnings("unchecked")
    public String[] getInputArguments() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String[]) getAttribute(object_name_, "InputArguments");
    }

    public String getLibraryPath() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "LibraryPath");
    }

    public String getManagementSpecVersion() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "ManagementSpecVersion");
    }

    public String getRunningVmName() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "Name");
    }

    public String getVmSpecName() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "SpecName");
    }

    public String getVmSpecVendor() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "SpecVendor");
    }


    public String getVmSpecVersion() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (String)getAttribute(object_name_, "SpecVersion");
    }

    public Long getVmStartTime() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

         return (Long)getAttribute(object_name_, "StartTime");

    }

    public TabularData getSystemProperties() throws
            AttributeNotFoundException,
            MBeanException,
            ReflectionException,
            InstanceNotFoundException,
            IOException {

        return (TabularData)getAttribute(object_name_, "SystemProperties");
    }

    @Override
    public MBeanServerConnection connectServer() {
        return ManagementFactory.getPlatformMBeanServer();
    }
}
