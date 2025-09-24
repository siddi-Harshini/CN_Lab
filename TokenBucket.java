import java.util.Scanner;

public class TokenBucket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bucketSize, numPackets, packetSize, tokens = 0, outputRate, tokenRate;

        System.out.print("Enter bucket size: ");
        bucketSize = scanner.nextInt();

        System.out.print("Enter output rate: ");
        outputRate = scanner.nextInt();

        System.out.print("Enter token generation rate: ");
        tokenRate = scanner.nextInt();

        System.out.print("Enter number of packets: ");
        numPackets = scanner.nextInt();

        for (int i = 0; i < numPackets; i++) {
            System.out.print("Enter packet size: ");
            packetSize = scanner.nextInt();

            tokens += tokenRate;
            if (tokens > bucketSize) {
                tokens = bucketSize;
            }

            if (packetSize > tokens) {
                System.out.println("Packet dropped");
            } else {
                System.out.println("Sent " + packetSize + " bytes");
                tokens -= packetSize;
            }
        }

        scanner.close();
    }
}




// output:
// Enter bucket size: 1000
// Enter output rate: 300
// Enter token generation rate: 400
// Enter number of packets: 4
// Enter packet size: 200
// Sent 200 bytes
// Enter packet size: 500
// Sent 500 bytes
// Enter packet size: 800
// Packet dropped
// Enter packet size: 300
// Sent 300 bytes
