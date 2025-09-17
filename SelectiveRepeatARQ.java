import java.util.Scanner;

public class SelectiveRepeatARQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selective Repeat ARQ Simulation (Simple)");

        System.out.print("Enter total number of frames: ");
        int totalFrames = sc.nextInt();

        System.out.print("Enter window size: ");
        int windowSize = sc.nextInt();

        boolean[] acked = new boolean[totalFrames];
        int base = 0, nextSeq = 0;

        while (base < totalFrames) {
            // Send frames in window
            for (int i = 0; i < windowSize && nextSeq < totalFrames; i++) {
                System.out.println("Sent frame: " + nextSeq);
                nextSeq++;
            }

            // Simulate ACKs
            for (int i = base; i < base + windowSize && i < totalFrames; i++) {
                if (!acked[i]) {
                    System.out.println("ACK received for frame: " + i);
                    acked[i] = true;
                }
            }

            // Slide window
            while (base < totalFrames && acked[base]) {
                base++;
            }
        }

        System.out.println("All frames sent and acknowledged.");
    }
}



// OUTPUT:
// Selective Repeat ARQ Simulation (Simple)
// Enter total number of frames: 2
// Enter window size: 3
// Sent frame: 0
// Sent frame: 1
// ACK received for frame: 0
// ACK received for frame: 1
// All frames sent and acknowledged.
