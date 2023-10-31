package lab4;

import lab123.Main;
import lab123.ReadInfo;
import lab123.WrapperPacket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.lang.Thread.sleep;

public class SyncSendingMessage {

    static InputStream inputStream1;
    static OutputStream outputStream2;

    static{
        //Инициализация статических параметров
        inputStream1 = Main.informationComPortProgramm.getVirtualPort_1().getInputStream();
        outputStream2 = Main.informationComPortProgramm.getVirtualPort().getOutputStream();
    }

    public static synchronized void sendMessage(WrapperPacket packet) throws IOException, InterruptedException {
        System.out.println("Началась потоковая передача данных!");
        byte[] date = packet.getMainPacketInformation();
        outputStream2.write(date);
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream1.read(buffer);
        System.out.println("Количество переданных байт: " + bytesRead);
        System.out.println("Через порт был передан пакет данных!");
        //Чтение переданных пакетов
        ReadInfo readInfo = new ReadInfo();
        readInfo.giveInfo(new String(buffer, 0, bytesRead));
        sleep(5000);
        SendingMultiPackage.sync.notify();
    }
}
