import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCentre {

    /*В качестве потокобезопасной коллекции выбираем реализацию ConcurrentLinkedQueue, потому что
     * во-первых - нам необходимо чтобы звонки обрабатывались поочереди, значит это уже одна из реализаций Queue,
     * во- вторых - нам нужно чтобы потоки не блокировались,
     * методом исключения смотрим презентацию и выбираем коллекцию по нашим критериям.
     * */

    private Queue<Call> calls = new ConcurrentLinkedQueue<>();
    private String genericNumber = Long.toString(896483654);
    private int numberEnding1 = 10;
    private int numberEnding2 = 99;

    public Queue<Call> getCalls() {
        return calls;
    }

    public void putCall() {

        while (!Thread.currentThread().isInterrupted()) {
            for (int i = numberEnding1; i < numberEnding2; i++) {
                Call call = new Call(genericNumber + i);
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
                    System.out.println(Thread.currentThread().getName() + " принял звонок с номера " + call.getNumber());
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