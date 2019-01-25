=====================================================================
unitTest

数据流程

透过ModbusFactory创建对应的Master对象。
封装需要发送的指令，比如ReadHoldingRegistersRequest，这是一个读寄存器指令，指定寄存器地址和长度即可。

Master对象将这个ReadHoldingRegistersRequest转化为OutgoingRequestMessage对象，然后传输给MessageControl。
透过驱动层，MessageControl将这个OutgoingRequestMessage写入对应的通讯硬件外设（串口、网口等），并等待返回数据IncomingResponseMessage。
如果没有等到，就返回null，并提醒超时。
如果等到了有效返回，则MessageControl利用MessageParser将IncomingResponseMessage转化为对应ModbusResponse返回给上层。

--https://www.jianshu.com/p/2a52b02081e3
=====================================================================
=====================================================================
testUtils
--https://www.jianshu.com/p/edebe37503c3
=====================================================================
=====================================================================
data
--用于Modbus4jUtils
--用于Modbus4WritejUtils


