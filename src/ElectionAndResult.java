import com.management.PollingBooth;
import com.management.Result;

import java.util.Scanner;

public class ElectionAndResult {
    public static void main(String[] args) {
        PollingBooth pollingBooth = new PollingBooth();
        int i = 1;
        while(i!=144){
            pollingBooth.registerVote();
        Scanner scanner = new Scanner(System.in);
        System.out.println("To Terminate Vote casting, enter termination key. Enter any other key to continue.\n");
        scanner.reset();
        i = scanner.nextInt();

        //flushing out cmd screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }
        pollingBooth.automateVoting();
        Result result=new Result(pollingBooth);
        result.showStateResult();
    }
}
