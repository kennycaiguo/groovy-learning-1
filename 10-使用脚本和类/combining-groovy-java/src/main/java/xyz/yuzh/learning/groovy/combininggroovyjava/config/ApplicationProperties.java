package xyz.yuzh.learning.groovy.combininggroovyjava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Harry Zhang
 * @since 2020/1/15 9:34 AM
 */
@ConfigurationProperties("groovy")
public class ApplicationProperties {

    private List<String> scriptPath;

    public List<String> getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(List<String> scriptPath) {
        this.scriptPath = scriptPath;
    }
}
