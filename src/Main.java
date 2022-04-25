public class Main {
    public static void main(String[] args) {

        CallCentre callCentre = new CallCentre();
        Thread ATC = new Thread(callCentre::putCall);
        ATC.start();
        ThreadGroup dispatchers = new ThreadGroup("Диспетчеры");
        for (int i = 0; i < 5; i++) {
            Thread dispatcher = new Thread(dispatchers, callCentre::takeCall);
            dispatcher.setName("Диспетчер № " + (i + 1));
            dispatcher.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!ATC.isInterrupted()) {
            ATC.interrupt();
        }
        while (!callCentre.getCalls().isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (dispatchers.activeCount() != 0) {
            dispatchers.interrupt();
        }
    }
}