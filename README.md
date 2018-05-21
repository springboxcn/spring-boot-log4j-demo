# spring-boot-log4j-demo

### TODO

- [ ] 不能输出日志到文件 


ERROR StatusLogger No log4j2 configuration file found. Using default configuration: logging only errors to the console.

Log4J2LoggingSystem

```java
private String[] getCurrentlySupportedConfigLocations() {
	List<String> supportedConfigLocations = new ArrayList<String>();
	if (isClassAvailable("com.fasterxml.jackson.dataformat.yaml.YAMLParser")) {
		Collections.addAll(supportedConfigLocations, "log4j2.yaml", "log4j2.yml");
	}
	if (isClassAvailable("com.fasterxml.jackson.databind.ObjectMapper")) {
		Collections.addAll(supportedConfigLocations, "log4j2.json", "log4j2.jsn");
	}
	supportedConfigLocations.add("log4j2.xml");
	return supportedConfigLocations
			.toArray(new String[supportedConfigLocations.size()]);
}    
```

看源码是默认支持 `log4j2.xml`

https://blog.csdn.net/vbirdbest/article/details/71751835


ConfigurationFactory

如果已添加的ConfigurationFactory在调用getConfiguration时返回null，则以插件形式发现的任何其他ConfigurationFactory将按其各自的顺序调用。 如果没有配置返回，DefaultConfiguration总是被调用。

SpringBootConfigurationFactory

spring-boot-1.5.4.RELEASE.jar!\META-INF\spring.factories

```properties
# PropertySource Loaders
org.springframework.boot.env.PropertySourceLoader=\
org.springframework.boot.env.PropertiesPropertySourceLoader,\
org.springframework.boot.env.YamlPropertySourceLoader

# Run Listeners
org.springframework.boot.SpringApplicationRunListener=\
org.springframework.boot.context.event.EventPublishingRunListener

# Application Context Initializers
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
org.springframework.boot.context.ContextIdApplicationContextInitializer,\
org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
org.springframework.boot.context.embedded.ServerPortInfoApplicationContextInitializer

# Application Listeners
org.springframework.context.ApplicationListener=\
org.springframework.boot.ClearCachesApplicationListener,\
org.springframework.boot.builder.ParentContextCloserApplicationListener,\
org.springframework.boot.context.FileEncodingApplicationListener,\
org.springframework.boot.context.config.AnsiOutputApplicationListener,\
org.springframework.boot.context.config.ConfigFileApplicationListener,\
org.springframework.boot.context.config.DelegatingApplicationListener,\
org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener,\
org.springframework.boot.logging.ClasspathLoggingApplicationListener,\
org.springframework.boot.logging.LoggingApplicationListener

# Environment Post Processors
org.springframework.boot.env.EnvironmentPostProcessor=\
org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor,\
org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor

# Failure Analyzers
org.springframework.boot.diagnostics.FailureAnalyzer=\
org.springframework.boot.diagnostics.analyzer.BeanCurrentlyInCreationFailureAnalyzer,\
org.springframework.boot.diagnostics.analyzer.BeanNotOfRequiredTypeFailureAnalyzer,\
org.springframework.boot.diagnostics.analyzer.BindFailureAnalyzer,\
org.springframework.boot.diagnostics.analyzer.ConnectorStartFailureAnalyzer,\
org.springframework.boot.diagnostics.analyzer.NoUniqueBeanDefinitionFailureAnalyzer,\
org.springframework.boot.diagnostics.analyzer.PortInUseFailureAnalyzer,\
org.springframework.boot.diagnostics.analyzer.ValidationExceptionFailureAnalyzer

# FailureAnalysisReporters
org.springframework.boot.diagnostics.FailureAnalysisReporter=\
org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter

```

`LoggingSystem` 这里可以看到加载的默认查找顺序,优先级

```java
static {
	Map<String, String> systems = new LinkedHashMap<String, String>();
	systems.put("ch.qos.logback.core.Appender",
			"org.springframework.boot.logging.logback.LogbackLoggingSystem");
	systems.put("org.apache.logging.log4j.core.impl.Log4jContextFactory",
			"org.springframework.boot.logging.log4j2.Log4J2LoggingSystem");
	systems.put("java.util.logging.LogManager",
			"org.springframework.boot.logging.java.JavaLoggingSystem");
	SYSTEMS = Collections.unmodifiableMap(systems);
}

public static LoggingSystem get(ClassLoader classLoader) {
    String loggingSystem = System.getProperty(SYSTEM_PROPERTY);
    if (StringUtils.hasLength(loggingSystem)) {
        if (NONE.equals(loggingSystem)) {
            return new NoOpLoggingSystem();
        }
        return get(classLoader, loggingSystem);
    }
    for (Map.Entry<String, String> entry : SYSTEMS.entrySet()) {
        if (ClassUtils.isPresent(entry.getKey(), classLoader)) {
            return get(classLoader, entry.getValue());
        }
    }
    throw new IllegalStateException("No suitable logging system located");
}	    
```
