package timeTest;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test2{

    static Log log= LogFactory.getLog(test.class);

    //1.工厂
    static ModbusFactory modbusFactory;
    static {
        if (modbusFactory==null)
        {
            modbusFactory=new ModbusFactory();
        }
    }

    //2.获取TCPMaster
    public static ModbusMaster getMaster() throws ModbusInitException {
        IpParameters params=new IpParameters();
        params.setHost("localhost");
        params.setPort(502);

        ModbusMaster tcpMaster =modbusFactory.createTcpMaster(params,false);
        tcpMaster.init();
        //log.info("TcpMaster初始化成功！");
        return tcpMaster;
    }

    //3.读数据
    //3.1 读取[01 Coil Status 0x]类型 开关数据
    public  static  Boolean readCoilStatus(int slaveId,int offset) throws ModbusInitException, ErrorResponseException, ModbusTransportException {
        BaseLocator<Boolean> locator=BaseLocator.coilStatus(slaveId, offset);
        //ModbusInitException---getMaster();
        //ErrorResponseException, ModbusTransportException---getValue();
        Boolean value=getMaster().getValue(locator);
        log.info("TcpMaster读取coil status数据，从{slaveID:"+ String.valueOf(slaveId)+"，offset："+String.valueOf(offset)+"},值为：{"+value+"}");

        return value;
    }
    //3.2读取[02 Input Status 1x]类型 开关数据
    public  static  Boolean readInputStatus(int slaveId,int offset) throws ModbusInitException, ErrorResponseException, ModbusTransportException {
        BaseLocator<Boolean> locator = BaseLocator.inputStatus(slaveId, offset);
        //ModbusInitException---getMaster();
        //ErrorResponseException, ModbusTransportException---getValue();
        Boolean value = getMaster().getValue(locator);
        log.info("TcpMaster读取input status数据，从{slaveID:"+ String.valueOf(slaveId)+"，offset："+String.valueOf(offset)+"},值为：{"+value+"}");
        return value;
    }
    //3.3读取[03 Holding Register类型 2x]模拟量数据
    public static Number readHoldingRegister(int slaveId, int offset, int dataType)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
        //ModbusInitException---getMaster();
        //ErrorResponseException, ModbusTransportException---getValue();
        Number value = getMaster().getValue(loc);
        log.info("TcpMaster读取Holding Register数据,数据类型：{"+dataType+"}，从{slaveID:"+ String.valueOf(slaveId)+"，offset："+String.valueOf(offset)+"},值为：{"+value+"}");
        return value;
    }
    //3.4 读取[04 Input Registers 3x]类型 模拟量数据
    public static Number readInputRegisters(int slaveId, int offset, int dataType)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        // 04 Input Registers类型数据读取
        BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
        //ModbusInitException---getMaster();
        //ErrorResponseException, ModbusTransportException---getValue();
        Number value = getMaster().getValue(loc);
        log.info("TcpMaster读取Holding Register数据,数据类型：{"+dataType+"}，从{slaveID:"+ String.valueOf(slaveId)+"，offset："+String.valueOf(offset)+"},值为：{"+value+"}");
        return value;
    }
    //3.5批量读取使用方法
    public static void batchRead() throws ModbusInitException, ErrorResponseException, ModbusTransportException {
        BatchRead<Integer> batch=new BatchRead<>();
        batch.addLocator(0, BaseLocator.holdingRegister(1, 1, DataType.FOUR_BYTE_FLOAT));
        batch.addLocator(1, BaseLocator.inputStatus(1, 0));
        //ModbusInitException---getMaster()
        ModbusMaster master=getMaster();
        batch.setContiguousRequests(false);
        //ErrorResponseException, ModbusTransportException---send(batch);
        BatchResults<Integer> results=master.send(batch);
        log.info("批量读取数据，数据为："+results);
    }
    /**
     * 测试
     */
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
        log.info("开始时间："+df.format(new Date()));
        for ( int i=0;i<100;i++) {
            long start=System.currentTimeMillis();
            try {
                // 01测试
                Boolean v011 = readCoilStatus(1, 0);
                Boolean v012 = readCoilStatus(1, 1);
                Boolean v013 = readCoilStatus(1, 6);
                //            System.out.println("v011:" + v011);
                //            System.out.println("v012:" + v012);
                //            System.out.println("v013:" + v013);
//                // 02测试
//                Boolean v021 = readInputStatus(1, 0);
//                Boolean v022 = readInputStatus(1, 1);
//                Boolean v023 = readInputStatus(1, 2);
//                //            System.out.println("v021:" + v021);
//                //            System.out.println("v022:" + v022);
//                //            System.out.println("v023:" + v023);
//
//                // 03测试
//                Number v031 = readHoldingRegister(1, 1, DataType.FOUR_BYTE_FLOAT);// 注意,float
//                Number v032 = readHoldingRegister(1, 3, DataType.FOUR_BYTE_FLOAT);// 同上
//                //            System.out.println("v031:" + v031);
//                //            System.out.println("v032:" + v032);
//
//                // 04测试
//                Number v041 = readInputRegisters(1, 1, DataType.FOUR_BYTE_FLOAT);//
//                Number v042 = readInputRegisters(1, 3, DataType.FOUR_BYTE_FLOAT);//
//                //            System.out.println("v041:" + v041);
//                //            System.out.println("v042:" + v042);
//                // 批量读取
//                batchRead();

            } catch (Exception e) {
                e.printStackTrace();
            }
            long stop=System.currentTimeMillis();
            log.info("===============================第"+i+"次循环,耗时"+(stop-start)+"ms==============================");
        }
        log.info("执行100次，结束时间："+df.format(new Date()));
    }

}
