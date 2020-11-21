package org.drombler.mediacenter;

import org.drombler.commons.spring.boot.context.properties.AbstractApplicationConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Florian
 */

@ConfigurationProperties(prefix = "mediacenter")
public class MediaCenterAppConfigurationProperties extends AbstractApplicationConfigurationProperties {

}
