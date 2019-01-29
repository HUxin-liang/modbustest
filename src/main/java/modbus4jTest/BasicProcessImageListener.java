package modbus4jTest;

import com.serotonin.modbus4j.ProcessImageListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasicProcessImageListener implements ProcessImageListener {
    static Log log= LogFactory.getLog(BasicProcessImageListener.class);
    @Override
    public void coilWrite(int offset, boolean oldValue, boolean newValue) {
        log.info("监听器：Coil at {" + offset + "} was set from {" + oldValue + "} to {" + newValue+"}.");
    }

    @Override
    public void holdingRegisterWrite(int offset, short oldValue, short newValue) {
        log.info("监听器：HoldRrgister at {" + offset + "} was set from {" + oldValue + "} to {" + newValue+"}.");
    }
}
