package modbus4jTest;

import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class newTest {
    static Log log= LogFactory.getLog(newTest.class);
    public static void main(String[] args) {
        TcpMaster tcpMaster=new TcpMaster();
        Slave slave=new Slave();
        try {
            slave.run();
        } catch (Exception e) {
            log.info("主程序：slave错误。");
            e.printStackTrace();
        }
        //TcpMaster tcpMaster=new TcpMaster();
        Modbus4jReadUtils modbus4jReadUtils=new Modbus4jReadUtils();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.info("开始时间："+df.format(new Date()));
        for ( int i=0;i<100;i++) {
            long start=System.currentTimeMillis();
            try {

                // 01测试
                boolean[] v011 = modbus4jReadUtils.readCoilStatus(1,0,1);
                boolean[] v012 = modbus4jReadUtils.readCoilStatus(1,1,1);
                boolean[] v013 = modbus4jReadUtils.readCoilStatus(1, 6,1);
                // 02测试
                boolean[] v021 = modbus4jReadUtils.readCoilStatus(1,0,1);
                boolean[] v022 = modbus4jReadUtils.readCoilStatus(1,1,1);
                boolean[] v023 = modbus4jReadUtils.readCoilStatus(1, 6,1);

                // 03测试
                short[] v031 = modbus4jReadUtils.readHoldingRegister(1, 1, 2);
                short[] v032 = modbus4jReadUtils.readHoldingRegister(1, 3, 2);

                // 04测试
                short[] v041 = modbus4jReadUtils.readInputRegisters(1, 1, 4);
                short[] v042 = modbus4jReadUtils.readInputRegisters(1, 3, 4);
                // 批量读取
                modbus4jReadUtils.batchRead();

            } catch (ModbusInitException e) {
                log.info("============ModbusInitException==========");
                e.printStackTrace();
            } catch (ErrorResponseException e){
                log.info("============ErrorResponseException==========");
                e.printStackTrace();
            } catch (ModbusTransportException e){
                log.info("============ModbusTransportException==========");
                e.printStackTrace();
            }
            long stop=System.currentTimeMillis();
            log.info("===============================第"+i+"次循环,耗时"+(stop-start)+"ns==============================");
        }
        log.info("执行100次，结束时间："+df.format(new Date()));
    }
}
