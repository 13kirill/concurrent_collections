import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCentre {

    Queue<Call> calls = new ConcurrentLinkedQueue<>();

    public Queue<Call> getCalls() {
        return calls;
    }

    public void putCall() {

        while (!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < 50; i++) {
                Call call = new Call(896483654 + i);
              //  try {
                 //   Thread.sleep(100);
              //  } catch (InterruptedException e) {
              //      e.printStackTrace();
               // }
                calls.add(call);
                System.out.println("ТС сгенерировала звонок " + call.getNumber());
                            }
        }

    }

    public void takeCall() {
        Call call;
        while (!Thread.currentThread().isInterrupted()) {
            while (!calls.isEmpty()) {
                call = calls.poll();
                if (call != null) {
                    System.out.println("Номер принятого звонка: " + call.getNumber());
                //    try {
                //        Thread.sleep(300);
                //    } catch (InterruptedException e) {
                 //       e.printStackTrace();
                    // }
                }
            }
        }
    }
}
