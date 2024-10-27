package examples.tools;

import org.apache.log4j.Logger;

public class MyLogger {
    static Logger log = Logger.getLogger(MyLogger.class);

    public MyLogger() {
    }

    public void log(String message) {
        log.debug("Im Logger");
        log.info(message);
    }
}
