import java.sql.SQLOutput;

public class Packet {

    private byte[] information;

    public Packet(){
        System.out.println("Создан пакет создания данных");
    }

    public void createPacket(String str){
        System.out.println("===================================================================");
        int counter = 4 + str.getBytes().length;
        information = new byte[counter];
        information[0] = (byte) ('z' + 19);
        System.out.println("Флаговый байт отправки: " + information[0]);
        information[1] = (byte) 0;
        System.out.println("Адресный байт отправки: " + information[1]);
        information[2] = (byte) 0;
        System.out.println("Адресный байт приема: " + information[2]);
        information[information.length - 1] = (byte) 0;
        System.out.println("Адресный байт FCS: " + information[information.length - 1]);
        byte[] usefulInformation = str.getBytes();
        for(int i = 0; i < usefulInformation.length; ++i){
            information[i + 3] = usefulInformation[i];
            System.out.println("Байт символа перед пакетов: " + usefulInformation[i]);
            System.out.println("Байт символа хранящимся в пакете: " + information[i+3]);
        }
        System.out.println("Формирование пакета успешно произошло");
        System.out.println("===================================================================");
    }


    public byte[] getByteInformation(){
        return information;
    }

    public void createByteStuffing(){
        char ch = (char) information[0];
        for(int i = 3; i < information.length - 1; ++i){
            if((char)information[i] == ch){
                information[i] = 0;
                System.out.println("Проведена шифрования символа");
            }
        }
    }

}
