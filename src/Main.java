import java.io.IOException;

//Work with COM-port Java
public class Main {
    public static void main(String[] args) throws IOException {
        CreateComPort comPort = new CreateComPort();
        comPort.createComPort();
        comPort.firstSendInformation();
        comPort.secondSendInformation();
        comPort.closePort();
    }
}