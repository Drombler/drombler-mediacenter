package org.drombler.photocenter.xmpp;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * https://docs.spring.io/spring-integration/reference/html/#xmpp
 * <p>
 * https://www.baeldung.com/xmpp-smack-chat-client
 *
 * @author Florian
 */
@Configuration
public class XmppConfig {

    @Bean
    public XMPPTCPConnectionConfiguration getXMPPTCPConnectionConfiguration() throws XmppStringprepException {
        return XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("baeldung", "baeldung")
                .setXmppDomain("jabb3r.org")
                .setHost("jabb3r.org")
                .build();
    }

    @Bean
    public XMPPConnection getXMPPConnection(XMPPTCPConnectionConfiguration config) throws SmackException, XMPPException, IOException, InterruptedException {
        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
        connection.connect(); //Establishes a connection to the server
        connection.login(); //Logs in
        return connection;
    }
}
