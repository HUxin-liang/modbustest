package modbus4jTest;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TcpMaster {

    static Log log= LogFactory.getLog(TcpMaster.class);

    private static ModbusFactory modbusFactory;

    static {
        if (modbusFactory == null) {
            modbusFactory = new ModbusFactory();
        }
    }
    public static ModbusMaster getMaster() {
        IpParameters params = new IpParameters();
        params.setHost("localhost");
        params.setPort(502);
        params.setEncapsulated(true);
        ModbusMaster master = modbusFactory.createTcpMaster(params, false);// TCP 协议
        try {
            //设置超时时间
            master.setTimeout(1000);
            //设置重连次数
            master.setRetries(3);
            //初始化
            master.init();
        } catch (ModbusInitException e) {
            log.info("TcpMaster:ModbusInitException，初始化失败。");
            e.printStackTrace();
        }
        return master;
    }

}
