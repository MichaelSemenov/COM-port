import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import jdk.jshell.SourceCodeAnalysis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class CreateComPort {
    static int counter = 0;
    //Виртуальный порт для первого компьютера для передачи
    SerialPort virtualPort;

    //Виртуальный порт для первого компьютера для приема
    SerialPort virtualPort1;

    //Виртуальный порт для второго компьютера для приема с первого порта
    SerialPort virtualPort_1;

    //Виртуальный порт для второго компьютера для отправки данных на второй порт первого компьютера
    SerialPort virtualPort1_1;
    public CreateComPort() {
    }
    public void createComPort(){
        virtualPort = SerialPort.getCommPort("COM"+4);
        System.out.println("Сгенерирован COM-порт для передачи данных первого компьютера под номером: " +
                virtualPort.getSystemPortName().substring(3, virtualPort.getSystemPortName().toString().length()));
        if(virtualPort.openPort()){
            System.out.println("Порт успешно открыт!");
        }
        virtualPort1 = SerialPort.getCommPort("COM"+
                (Integer.parseInt((virtualPort.getSystemPortName().substring(3, virtualPort.getSystemPortName().toString().length())))+1));
        System.out.println("Сгенерирован COM-порт для приема данных первого компьютера под номером: " +
                virtualPort1.getSystemPortName().substring(3, virtualPort1.getSystemPortName().toString().length()));
        if(virtualPort1.openPort()){
            System.out.println("Порт успешно открыт!");
        }
        virtualPort_1 = SerialPort.getCommPort("COM"+10);
        System.out.println("Сгенерирован COM-порт для передачи данных первого компьютера под номером: " +
                virtualPort_1.getSystemPortName().substring(3, virtualPort_1.getSystemPortName().toString().length()));
        if(virtualPort_1.openPort()){
            System.out.println("Порт успешно открыт!");
        }
        virtualPort1_1 = SerialPort.getCommPort("COM"+
                (Integer.parseInt((virtualPort_1.getSystemPortName().substring(3, virtualPort_1.getSystemPortName().toString().length())))-1));
        System.out.println("Сгенерирован COM-порт для передачи данных первого компьютера под номером: " +
                virtualPort1_1.getSystemPortName().substring(3, virtualPort1_1.getSystemPortName().toString().length()));
        if(virtualPort1_1.openPort()){
            System.out.println("Порт успешно открыт!");
        }
        //Значения паритетности - SerialPort.NO_PARITY - без проверки на четность/нечетность
        //- SerialPort.EVEN_PARITY - проверка на четность
        //- SerialPort.ODD_PARITY - проверка на нечетность
        //- SerialPort.MARK_PARITY - всегда установлен в 1
        //- SerialPort.SPACE_PARITY - всегда установлен в 0
        virtualPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        virtualPort1.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        virtualPort_1.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        virtualPort1_1.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
    }


    public void changeSomeInformation(){
        System.out.println("Изменение скорости COM-порта:" + "[1] - COM9\n"
        +"[2] - COM10\n" + "[3] - COM4\n" + "[4] - COM5\n Измерение передачи -> [бит/c]");
        Scanner scanner = new Scanner(System.in);
        int change = scanner.nextInt();
        int speed;
        switch (change){
            case 1: {
                System.out.println("Изменение COM9");
                speed = scanner.nextInt();
                virtualPort1_1.setComPortParameters(speed, 8, 1, SerialPort.NO_PARITY);

            } break;
            case 2: {
                System.out.println("Изменение COM10");
                speed = scanner.nextInt();
                virtualPort_1.setComPortParameters(speed, 8, 1, SerialPort.NO_PARITY);
            }
            break;
            case 3: {
                System.out.println("Изменение COM4");
                speed = scanner.nextInt();
                virtualPort.setComPortParameters(speed, 8, 1, SerialPort.NO_PARITY);

            } break;

            case 4: {
                System.out.println("Изменение COM5");
                speed = scanner.nextInt();
                virtualPort1.setComPortParameters(speed, 8, 1, SerialPort.NO_PARITY);

            }break;
            default: {
                System.out.println("Неверное действие для иззменения параметра COM-порта!");
            }
        }
        System.out.println("Изменение скорости COM-порта произошла успешно!");
    }

    public void firstSendInformation() throws IOException {
        String message = inputMessage();
        InputStream inputStream1 = virtualPort_1.getInputStream();
        OutputStream outputStream2 = virtualPort.getOutputStream();
        byte[] date = message.getBytes();
        outputStream2.write(date);
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream1.read(buffer);
        System.out.println("Пересланные данные из COM-порта первого компьютера в другой COM-порт компьютера: " + new String(buffer, 0, bytesRead));
    }

    public void secondSendInformation(){
        String message = inputMessage();
        InputStream inputStream1 = virtualPort1.getInputStream();
        OutputStream outputStream2 = virtualPort1_1.getOutputStream();
        byte[] date = message.getBytes();
        try {
            outputStream2.write(date);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        try {
            bytesRead = inputStream1.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пересланные данные из COM-порта первого компьютера в другой COM-порт компьютера: " + new String(buffer, 0, bytesRead));
    }


    public void closePort(){
        virtualPort.closePort();
        virtualPort1.closePort();
        //Реализовать инициализацию
        virtualPort_1.closePort();
        virtualPort1_1.closePort();
        System.out.println("COM-порты успешно закрыты!");
    }

    public String inputMessage(){
        System.out.print("Введите строку для побайтовой передачи данных: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
