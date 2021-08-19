import com.management.PollingBooth;
import com.management.Result;

import java.util.Scanner;

public class ElectionAndResult {
    public static void main(String[] args) {
        PollingBooth pollingBooth = new PollingBooth();
        int i = 0;
        while(i!=1){
            pollingBooth.registerVote();
        Scanner scanner = new Scanner(System.in);
        System.out.println("To Cast Terminate Voting, enter key. Enter another key to continue.\n");
        scanner.reset();
        i = scanner.nextInt();

        //flushing out cmd screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }
        Result result=new Result(pollingBooth);
        result.showStateResult();
        result.stateShortResult();
        result.showCandidateResult("072819793621");
        result.showVoterDetail("021919664820");
        result.showConstituencyResult(1);
    }
}
