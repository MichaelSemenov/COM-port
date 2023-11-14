import java.nio.ByteBuffer;
import java.sql.SQLOutput;

public class Packet {

    private byte[] information;

    public Packet(){
        System.out.println("Создан пакет создания данных");
    }

    public void createPacket(String str){
        ByteBuffer buffer;
        System.out.println("===================================================================");
        int counter = 4 + str.getBytes().length;
        information = new byte[counter];
        information[0] = (byte)('z' + 19);
        System.out.println("Флаговый байт отправки: " + information[0]);
        information[1] = (byte) 0;
        System.out.println("Адресный байт отправки: " + information[1]);
        information[2] = (byte) 0;
        System.out.println("Адресный байт приема: " + information[2]);
        information[information.length - 1] = (byte) 0;
        System.out.println("Адресный байт FCS: " + information[information.length - 1]);
//        FcsCyclicCode test = new FcsCyclicCode();
//        long fcs = test.createCyclicOperation(str.getBytes());
//        byte [] b_fcs = new byte[1];
        System.out.println("Ошибка возникает тут!");
/*        buffer = ByteBuffer.wrap(b_fcs);
        buffer.putLong(fcs);
        buffer.rewind();*/
        System.out.print("Контрольные данные хранящие в FCS: ");
       // information[information.length - 1] = buffer.get();
        System.out.println(information[information.length - 1]);
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
        System.out.println("=================================================");
        System.out.println("Включен байт-стаффинг");
        char ch = (char)information[0];
        System.out.println("Изменение символа: " + ch + " или " + information[0]);
        for(int i = 3; i < information.length - 1; ++i){
            if((char)information[i] == ch){
                information[i] = 0;
                System.out.println("Проведена шифрования символа");
            }
        }
        System.out.println("=================================================");
    }

}
