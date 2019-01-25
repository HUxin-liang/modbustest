package testUtils;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

public class test2 {
    public static void main(String[] args){
        int registerId=100;
        int modbusId=1;
        ModBus4JTCPClient modbusTcp = new ModBus4JTCPClient();

        ModbusMaster master = null;	//192.168.1.108
        try {
            master = modbusTcp.getMaster("localhost", 502);
        } catch (ModbusInitException e) {
            System.out.println("初始化失败");
            e.printStackTrace();
        }


        try {
            modbusTcp.readInputRegisters(master, registerId, 1, modbusId, "u");
        } catch (ModbusTransportException e) {
            System.out.println("transport错误");
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            System.out.println("response错误");
            e.printStackTrace();
        } catch (ModbusInitException e) {
            System.out.println("modbus初始化错误");
            e.printStackTrace();
        }
    }
}
