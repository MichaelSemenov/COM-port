import java.io.IOException;
//COM4 <=> COM10
//COM5 <=> COM9
//Work with COM-port Java
public class Main {
    public static void main(String[] args) throws IOException {
        CreateComPort comPort = new CreateComPort();
        //Первая лабораторная работа
        comPort.createComPort();
//        comPort.firstSendInformation();
//        comPort.secondSendInformation();
        //Вторая лабораторная работа
        comPort.workWithPacket();
        comPort.closePort();
    }
}