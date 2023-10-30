package lab123;

import lab123.Packet;

public class WrapperPacket {
    //В данной лабораторной работе используется один пакет, так как по варианту
    //Задания полезная информация передается безграничная
    private Packet mainInformation;
    public WrapperPacket(){
        System.out.println("Создание главного перенаправляющего пакета!");
    }

    public void createMainInformation(String str){
        mainInformation = new Packet();
        mainInformation.createPacket(str);
    }

    public void encryptionWithByteStuffing(){
        mainInformation.createByteStuffing();
    }



    public byte[] getMainPacketInformation(){
        return this.mainInformation.getByteInformation();
    }

}
