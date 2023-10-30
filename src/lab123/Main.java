package lab123;

import lab123.CreateComPort;

import java.io.IOException;
//COM4 <=> COM10
//COM5 <=> COM9
//Work with COM-port Java
public class Main {

    public static CreateComPort informationComPortProgramm;
    public static void main(String[] args) throws IOException, InterruptedException {
        CreateComPort comPort = new CreateComPort();
        informationComPortProgramm = comPort;
        //Первая лабораторная работа
        comPort.createComPort();
//        comPort.firstSendInformation();
//        comPort.secondSendInformation();
        //Вторая лабораторная работа
        // comPort.workWithPacket();
        comPort.syncSending();
        comPort.closePort();
    }
}