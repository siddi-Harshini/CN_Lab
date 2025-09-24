import java.util.Random;
import java.util.Scanner;

public class LeakyBucket {
    static final int BUCKET_SIZE = 512;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter output rate: ");
        int outputRate = scanner.nextInt();

        for (int i = 0; i < 5; i++) {
            int packetSize = rand.nextInt(1000);
            System.out.println("\nPacket no " + i + ", Packet size = " + packetSize);

            if (packetSize > BUCKET_SIZE) {
                System.out.println("Bucket overflow");
            } else {
                int remaining = packetSize;
                while (remaining > outputRate) {
                    System.out.println(outputRate + " bytes outputted.");
                    remaining -= outputRate;
                }
                if (remaining > 0) {
                    System.out.println("Last " + remaining + " bytes sent");
                }
                System.out.println("Bucket output successful");
            }
        }

        scanner.close();
    }
}





// Output:
// Enter output rate: 200

// Packet no 0, Packet size = 173
// Last 173 bytes sent
// Bucket output successful

// Packet no 1, Packet size = 553
// Bucket overflow

// Packet no 2, Packet size = 91
// Last 91 bytes sent
// Bucket output successful

// Packet no 3, Packet size = 964
// Bucket overflow

// Packet no 4, Packet size = 740
// Bucket overflow