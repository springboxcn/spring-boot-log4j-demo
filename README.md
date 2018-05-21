# spring-boot-log4j-demo

### [log4j2-for-springboot](log4j2-for-springboot.md)

```xml
<Loggers>
    <logger name="D">
        <AppenderRef ref="RollingRandomAccessFile2"/>
    </logger>
    <Root level="info">
        <AppenderRef ref="Console"/>
        <AppenderRef ref="RollingRandomAccessFile"/>
    </Root>
</Loggers>
```

```java
private static final Logger LOGGERD = LogManager.getLogger("D");

private static final Logger LOGGERE = LogManager.getLogger("E");

@GetMapping(value = {"/", "index"})
@ResponseBody
public String index() {
    LOGGERD.debug("d this is debug msg!");
    LOGGERD.info("d this is info msg!");
    LOGGERD.error("d this is error msg!");
    return "hello world";
}

@GetMapping(value = {"home"})
@ResponseBody
public String home() {
    LOGGERE.debug("e this is info msg!");
    LOGGERE.info("e this is info msg!");
    LOGGERE.error("e this is info msg!");
    return "home";
}
```

```java
LOGGERD.info("d this is info msg!");
LOGGERD.error("d this is error msg!");
```

将会执行!

这样可以将日志模块化，某一个模块的日志打印到一个文件中!

https://github.com/apache/logging-log4j2

