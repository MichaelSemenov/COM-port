package lab123;

import java.util.zip.CRC32;


public class FcsCyclicCode {
    private byte[] fcs;

    public FcsCyclicCode() {
        System.out.println("Реализована работа циклического кода!");
    }

    public long createCyclicOperation(byte[] goodInformation){
        System.out.println("Создание циклического кода на основе существующего!");
/*        int size = goodInformation.length;
        System.out.println("Размер полезной информации: " + size);
        //Обзор полинома: x^k + x + 1 -> максимальное количество
        double size_polinom = Math.log(size)/Math.log(2) + 1;
        int test = (int)size_polinom;
        if((test-size_polinom) == 0){
            System.out.println("Полином длины: " + test);
        } else {
            System.out.println("Полином длинны: " + );
        }
        byte polinom =*/
        String information = new String(goodInformation);
        CRC32 crc = new CRC32();
        crc.update(information.getBytes());
        System.out.println("Ваша полезная информация равняется CRC-32: " + crc.getValue());
        return crc.getValue();
    }
}

  /*  int crc = 0xFFFF;
    for (int i = 0; i < data.length; i++) {
         crc ^= (data[i] & 0xFF);
         for (int j = 0; j < 8; j++) {
         if ((crc & 0x0001) != 0) {
         crc = (crc >> 1) ^ 0xA001;
         } else {
         crc = crc >> 1;
         }
         }
         }
         return crc;*/
