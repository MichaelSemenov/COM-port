import java.lang.reflect.Array;
import java.util.Arrays;

public class ReadInfo {
    private String usefullInformation;
    public ReadInfo(){
        System.out.println("Создан класс для прочтения пакета");
    }

    public void giveInfo(String test){
        usefullInformation = test;
        byte[] information = usefullInformation.getBytes();
        char ch = (char) information[0];
        for(int i = 3; i < information.length ; ++i){
            if((char)information[i] == 0){
                information[i] = information[0];
                System.out.println("Проведена дешифровка данных");
            }
        }
        //Вырезаем полезную информацию
        for(int i = 3; i < information.length - 1; ++i){
            System.out.print(information[i]);
        }

    }



}
