package lab123;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ReadInfo {
    private String usefullInformation;
    public ReadInfo(){
        System.out.println("Создан класс для прочтения пакета");
    }

    public void giveInfo(String test){
        byte[] informationForFcs = new byte[test.substring(3, (test.length() -1)).length()];
        System.out.println("===============================================");
        System.out.println("Длина строки при получении: " + test.length());
        usefullInformation = test;
        byte[] information = usefullInformation.getBytes();
        char ch = (char) information[0];
        System.out.println("Поиск дешифровки на данный символ: " + ch + " или " + information[0] + " или " + test.charAt(0));
        for(int i = 3; i < information.length ; ++i){
            if((char)information[i] == 0){
                information[i] = information[0];
                System.out.println("Проведена дешифровка данных");
            }
        }
        long test_1 = 0;
      /*  ByteBuffer byteBuffer = ByteBuffer.wrap(informationForFcs);
        long test_1 = byteBuffer.getLong();*/
/*        long test_2 = information[information.length - 1];
        if(test_1 == test_2){
            System.out.println("Контрольное значение прошло успешно проверку!");
        } else System.out.println("Контрольное значение прошло успешно проверку");*/
        //Вырезаем полезную информацию
        for(int i = 3; i < information.length - 1; ++i){
            char str = (char)information[i];
            System.out.print(str);
        }
        System.out.println("\n===============================================");
    }



}
