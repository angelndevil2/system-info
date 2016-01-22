package com.tistory.devilnangel.common;

import javax.management.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Set;

/**
 * Local MBean handler
 *
 * @author k, Created on 15. 5. 1.
 */
public abstract class MBeanConnection {

    private MBeanServerConnection conn_;

    public MBeanConnection() throws IOException, NamingException {
        reConnect();
    }

    public MBeanServerConnection connectServer(String lookupName) throws NamingException {

        InitialContext ctx = new InitialContext();
        conn_ = (MBeanServer)ctx.lookup(lookupName);
        return conn_;
    }

    /**
     * 특정 서버별 관심 MBeanServer 에 연결하기.
     * @return {@link MBeanServerConnection}
     */
    protected abstract MBeanServerConnection connectServer() throws NamingException, IOException;

    public final boolean reConnect() throws IOException, NamingException {

        String builder = System.getProperty("javax.management.builder.initial");
        if (builder != null) {

            System.setProperty("javax.management.builder.initial", "");

            conn_ = connectServer();

            System.setProperty("javax.management.builder.initial", builder);

        } else {

            conn_ = connectServer();
        }

        return conn_ != null;
    }

    public Set<ObjectInstance> queryMBeans(ObjectName objectName, QueryExp query) throws IOException {
        return conn_.queryMBeans(objectName, query);
    }

    public Set<ObjectInstance> queryMBeans(String objectName, QueryExp query)
            throws MalformedObjectNameException, IOException {
        return conn_.queryMBeans(new ObjectName(objectName), query);
    }


    public Set<ObjectName> queryNames(ObjectName objectName, QueryExp query) throws IOException {
        return conn_.queryNames(objectName, query);
    }

    public Object getAttribute(ObjectName objectName, String attr)
            throws AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        return conn_.getAttribute(objectName, attr);
    }

    public MBeanInfo getMBeanInfo(ObjectName on)
            throws IntrospectionException, ReflectionException, InstanceNotFoundException, IOException {
        return conn_.getMBeanInfo(on);
    }

    public Object invoke(ObjectName on, String op, Object[] params, String[] signature)
            throws ReflectionException, MBeanException, InstanceNotFoundException, IOException {

        return conn_.invoke(on, op, params, signature);
    }

    /**
     * @param on
     * @param listener
     * @param filter
     * @param handback
     * @throws IOException
     * @throws InstanceNotFoundException
     */
    public void addNotificationListener(ObjectName on, NotificationListener listener, NotificationFilter filter, Object handback)
            throws IOException, InstanceNotFoundException {

        if (!isConnected()) throw new IOException(SystemMessages.NULL_MBEAN_SERVER_CONNECTION);

        conn_.addNotificationListener(on, listener, filter, handback);
    }

    public boolean isConnected() {
        return conn_ != null;
    }
}
