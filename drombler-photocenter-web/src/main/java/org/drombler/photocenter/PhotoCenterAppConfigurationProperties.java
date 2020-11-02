package org.drombler.photocenter;

import org.drombler.commons.spring.boot.context.properties.AbstractApplicationConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Florian
 */

@ConfigurationProperties(prefix = "photocenter")
public class PhotoCenterAppConfigurationProperties extends AbstractApplicationConfigurationProperties {

}
