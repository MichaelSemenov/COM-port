
//Протокол для обработки станциями кадра, пакета, маркера
public class Protocol {
    public Protocol(){
        System.out.println("Инициализация протокола проверки кадра или маркера!");
    }

    public static boolean success(byte[] marker){
        return marker.length != 5;
    }

    public static byte[] markerFromKadr(byte[] marker, String dataInformation){
        byte[] kadr = new byte[ dataInformation.length() + marker.length];
        byte[] bytesDataInformation = dataInformation.getBytes();
        int count = 0;
        for(int i = 0; i < kadr.length; ++i){
            if(i > 2 && i < (kadr.length -2)) kadr[i] = bytesDataInformation[count++];
            else kadr[i] = marker[i];

        }
        return kadr;
    }
    public static byte[] getMarker(int stationInput, int statioOutput, int paritet, int reservation ,int copyInformation){
        return new byte[]{(byte)stationInput,(byte)statioOutput, (byte) copyInformation, (byte)paritet, (byte)reservation};
    }

    public static void reformCopyInformation(byte[] kadr, int copyInformation){
        kadr[kadr.length-1] = (byte)copyInformation;
    }

    public static boolean getParitetionSuccess(int paritetStation, int markerParitet){
        return paritetStation >= markerParitet;
    }
    public static String getInfoCopyInformation(){
        return "Копирование информации принимающей станцией завершено!";
    }
    public static String startMarker(){
        return "Маркер отправлен успешно в кольцо!";
    }
}
