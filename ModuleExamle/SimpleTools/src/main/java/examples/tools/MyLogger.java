package examples.tools;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MyLogger {
    static Logger log = LogManager.getLogger(MyLogger.class);

    public MyLogger() {
    }

    public void log(String message) {
        log.debug("Im Logger");
        log.info(message);
    }
}
