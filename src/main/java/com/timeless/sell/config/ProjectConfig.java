package com.timeless.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author lijiayin
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    /**
     * 项目url
     */
    private String sell;
}
