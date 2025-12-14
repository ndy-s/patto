package io.github.ndys.patto.ui;

import java.util.function.Supplier;

public final class Spinner {

    public static <T> T run(String message, Supplier<T> task) {
        final boolean[] done = {false};

        Thread t = new Thread(() -> {
            String[] s = {"|", "/", "-", "\\"};
            int i = 0;
            while (!done[0]) {
                System.out.print("\r" + message + " " + s[i++ % s.length]);
                sleep(150);
            }
            System.out.print("\r" + message + " ✅\n");
        });

        t.start();
        try {
            return task.get();
        } finally {
            done[0] = true;
            join(t);
        }
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }

    private static void join(Thread t) {
        try { t.join(); } catch (Exception ignored) {}
    }
}

