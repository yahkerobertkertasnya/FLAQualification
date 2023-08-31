package helper;

import restaurant.CurrentRestaurant;

import java.util.Scanner;

public class GameInput implements Runnable {

    private boolean isStopped;
    private Thread thread;
    public GameInput() {

        thread = new Thread(this);
        this.thread.start();
        this.isStopped = true;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            if(this.isStopped) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }

            scanner.nextLine();
            CurrentRestaurant.getInstance().pauseGame();

        } while(true);
    }
}
