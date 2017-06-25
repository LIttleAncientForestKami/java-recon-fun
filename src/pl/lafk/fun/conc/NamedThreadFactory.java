package pl.lafk.fun.conc;

/**
 * Simplest thread factory. Just codifies thread creation and it's naming.
 *
 * @author LAFK_pl, Tomasz.Borek@gmail.com
 */
public class NamedThreadFactory {

    public static Thread create(Runnable r, String name) {
        Thread newThread = new Thread(r);
        newThread.setName(name);
        return newThread;
    }
}
