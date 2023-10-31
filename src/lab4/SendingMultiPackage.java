package lab4;

import lab123.WrapperPacket;

import java.io.IOException;

public class SendingMultiPackage extends Thread{
     static Object sync = new Object();
     static int counter = 0;
    private String str;
    public SendingMultiPackage(String str) {
        this.str = str;
    }
    @Override
    public void run(){
        WrapperPacket packet = new WrapperPacket();
        packet.createMainInformation(str);
        packet.encryptionWithByteStuffing();
        try {
            if(counter != 0){
                sync.wait();
                SendingMultiPackage.counter++;
            }else{
                SendingMultiPackage.counter ++;
            }
            SyncSendingMessage.sendMessage(packet);
            System.out.println("Поток завершил свою работу!");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
