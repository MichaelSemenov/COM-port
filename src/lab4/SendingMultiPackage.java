package lab4;

import lab123.WrapperPacket;

import java.io.IOException;

public class SendingMultiPackage extends Thread{
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
            SyncSendingMessage.sendMessage(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}