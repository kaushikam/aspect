import ch.qos.logback.classic.Level

def properties = new Properties()
new File(getClass().getClassLoader().getResource("application.properties").toURI()).withInputStream {
    stream -> properties.load(stream)
}
def config = new ConfigSlurper().parse(properties)
def logFile = config.logging.file
def rootLogLevel = Level.toLevel(config.logging.level.root)
def applicationLogLevel = Level.toLevel(config.logging.level.com.kaushikam.aspect)

appender("FILE", RollingFileAppender) {
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${logFile}.%d{dd-MM-yyyy}.%i.log.zip"
        maxHistory = 30
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "50MB"
        }
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%date{dd MMM yyyy; HH:mm:ss.SSS} %-5level %logger{36} - %msg%n"
    }
}

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%date{dd MMM yyyy; HH:mm:ss.SSS} %-5level %logger{36} - %msg%n"
    }
}

logger("com.kaushikam.aspect", applicationLogLevel, ["FILE", "STDOUT"], false)
root(rootLogLevel, ["FILE", "STDOUT"])