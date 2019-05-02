import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;


public class DataPacote extends Pacote {

    private int seqN;
    private byte[] data;
    //public static final int SIZE = UDPFriendly.DATA_SIZE;

    public DataPacote(int seqN, byte[] data, InetAddress addr, int port) throws IOException {
        super(I); this.seqN = seqN; this.data = data;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(type);
        outputStream.write(intToByteArray(seqN));
        outputStream.write(data);
        byte[] buffer = outputStream.toByteArray();

        pacote = new DatagramPacket(buffer, buffer.length, addr, port);
    }

    public int getSeqN() { return seqN; }
    public byte[] getDados() { return data; }

    public static int readSeqN(DatagramPacket packet) {
        byte[] buffer = packet.getData();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        byte[] intBuffer = new byte[4];
        inputStream.skip(1);
        inputStream.read(intBuffer,0,4);
        return byteArrayToInt(intBuffer);
    }
    /*
    public static byte[] readData(DatagramPacket packet) {
        byte[] buffer = packet.getData();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        //byte[] dataBu ffer = new byte[SIZE];
        inputStream.skip(5);
        inputStream.read(dataBuffer,0,SIZE);
        return dataBuffer;
    }
    */
}