public class Main {
    public static void main(String[] args) {

        int generateCallsTime = 5000;
        int answerCallTime = 100;
        int dispatcherAmount = 5;

        CallCentre callCentre = new CallCentre();
        Thread ATC = new Thread(callCentre::putCall);
        ATC.start();
        ThreadGroup dispatchers = new ThreadGroup("Диспетчеры");
        for (int i = 0; i < dispatcherAmount; i++) {
            Thread dispatcher = new Thread(dispatchers, callCentre::takeCall);
            dispatcher.setName("Диспетчер № " + (++i));
            dispatcher.start();
        }
        try {
            Thread.sleep(generateCallsTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!ATC.isInterrupted()) {
            ATC.interrupt();
        }
        while (!callCentre.getCalls().isEmpty()) {
            try {
                Thread.sleep(answerCallTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (dispatchers.activeCount() != 0) {
            dispatchers.interrupt();
        }
    }
}