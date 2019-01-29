package modbus4jTest;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.exception.ModbusInitException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Slave {

    static Log log= LogFactory.getLog(Slave.class);

    public void  run(String... args) throws Exception{
        createSlave();
    }

    private void createSlave() {
        ModbusFactory modbusFactory=new ModbusFactory();
        final ModbusSlaveSet slave=modbusFactory.createTcpSlave(true);
        slave.addProcessImage(Register.getModscanProcessImage(1));
        slave.getProcessImage(1);
        try {
            slave.start();
        } catch (ModbusInitException e) {
            log.info("slave:ModbusInitException：初始化失败。");
            e.printStackTrace();
        }
    }
}
