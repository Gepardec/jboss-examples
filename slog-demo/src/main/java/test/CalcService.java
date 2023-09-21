package test;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import org.gepardec.slog.SLogged;
import org.gepardec.slog.SLogger;
import org.gepardec.slog.level.SLogWarn;

import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class CalcService {
    private static AtomicInteger instanceNo = new AtomicInteger(0);

    private final int iNo = instanceNo.incrementAndGet();

    public int getiNo() {
        return iNo;
    }

    public String getName() {
        return "calculation service";
    }

    @Inject
    private SLogger log;

    @SLogged
    public CalcResponse calc(CalcRequest request) {
        CalcLogMessage logObj = log.add(new CalcLogMessage()); // proxy the log object

        logObj.setRequest(request);

        Operation operation = request.getOperation();
        int operand1 = request.getOperand1();
        int operand2 = request.getOperand2();

        int result = getOperation(operation).performCalculation(operand1, operand2);
        CalcResponse response = new CalcResponse(result);
        logObj.setResponse(response);
        return response;
    }

    @SLogged
    public CalcResponse calcWithError(CalcRequest request) {
        CalcLogMessage logObj = log.add(new CalcLogMessage()); // proxy the log object

        logObj.setRequest(request);

        log.add(new RuntimeException("very serious error"));
        return new CalcResponse(-1);
    }

    private CalcOperation getOperation(Operation op) {
        return new SumOperation();
    }

    @SLogWarn
    public static class CalcLogMessage {
        private CalcRequest request;
        private CalcResponse response;
        private int instanceNo;

        public CalcRequest getRequest() {
            return request;
        }

        public void setRequest(CalcRequest request) {
            this.request = request;
        }

        public CalcResponse getResponse() {
            return response;
        }

        public void setResponse(CalcResponse response) {
            this.response = response;
        }

        public int getInstanceNo() {
            return instanceNo;
        }

        public void setInstanceNo(int instanceNo) {
            this.instanceNo = instanceNo;
        }
    }
}
