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