# spring-boot-log4j-demo

### [log4j2-for-springboot](log4j2-for-springboot.md)

[github/logging-log4j2](https://github.com/apache/logging-log4j2)

[apache/log4j/2.x/manual/appenders](https://logging.apache.org/log4j/2.x/manual/appenders.html)

[Log4j2写日志的艺术](https://blog.csdn.net/scherrer/article/details/73744392)

[Log4j是如何取得行号的](http://flyfoxs.iteye.com/blog/2089996)

https://blog.csdn.net/relative660/article/details/4263977

http://logging.apache.org/log4j/2.x/


https://www.cnblogs.com/sa-dan/p/6837225.html
https://logback.qos.ch/manual/layouts.html#conversionWord

https://stackoverflow.com/questions/38272287/log4j2-abbreviate-shorten-package-names


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

## 依赖

### Maven

```xml
<dependencies>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.11.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.11.0</version>
  </dependency>
</dependencies>
```

### Gradle

```groovy
dependencies {
  compile group: 'org.apache.logging.log4j', name: 'log4j-api', version: '2.11.0'
  compile group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.11.0'
}
```
