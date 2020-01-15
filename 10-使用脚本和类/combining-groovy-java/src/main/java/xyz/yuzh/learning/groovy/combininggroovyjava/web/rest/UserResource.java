package xyz.yuzh.learning.groovy.combininggroovyjava.web.rest;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yuzh.learning.groovy.combininggroovyjava.config.ApplicationProperties;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Harry Zhang
 * @since 2020/1/14 10:08 PM
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final ApplicationProperties applicationProperties;
    private final GroovyScriptEngine groovyScriptEngine;
    private final DataSource dataSource;

    public UserResource(ApplicationProperties applicationProperties, DataSource dataSource) throws IOException {
        this.applicationProperties = applicationProperties;
        groovyScriptEngine = new GroovyScriptEngine(applicationProperties.getScriptPath().toArray(new String[]{}));
        this.dataSource = dataSource;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId) {
        log.info("userId: {}", userId);
        return ResponseEntity.ok("Harry Zhang");
    }

    @GetMapping
    public ResponseEntity<Object> getAll() throws ResourceException, ScriptException {
        Binding binding = new Binding();
        binding.setProperty("dataSource", dataSource);
        Object result = groovyScriptEngine.run("BusinessHandler.groovy", binding);
        return ResponseEntity.ok(result);
    }

}
