package pl.lafk.fun.conc.parking;

import pl.lafk.fun.conc.NamedThreadFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Good for entry-level multithreading.
 * <OL>
 *     <LI>how many threads will run?</LI>
 *     <LI>what changes when instead of <code>run</code> you use <code>start</code>?</LI>
 *     <LI>observe this in monitoring tools, like JVisualvm or jstack</LI>
 *     <LI>notice how fast threads are</LI>
 *     <LI>add sleep (3 seconds for main thread)</LI>
 *     <LI>name both threads and observe how it changes monitoring</LI>
 *     <LI>use <code>synchronized</code> to protect mutexes in this code</LI>
 *     <LI>get rid of sleeps and observe changes</LI>
 * </OL>
 * @author LAFK_pl, Tomasz.Borek@gmail.com
 */
public class Parking<S extends String> {

    private int size;
    private int entriesNr;
    private List<S> cars = new ArrayList<>(size);

    private Parking(int size, int entriesNr) {
        this.size = size;
        this.entriesNr = entriesNr;
    }

    void parkACar(S car) {
        System.out.format("P1: %d cars, parking a %s... ", cars.size(), car);
        if(cars.size() == size) {
            System.out.format("P.E1: Sorry, we're full\n");
            return;
        }
        cars.add(car);
        System.out.format("P2: parked a %s... %d spots taken. ", car, cars.size());
    }

    S returnACar(String car) {
        System.out.format("R1: %d cars, returning a %s... ", cars.size(), car);
        if(cars.isEmpty()) {
            System.out.format("R.E1: Sorry, we're empty\n");
            return (S) "Empty parking lot!";
        }
        if(!cars.contains(car)) {
            System.out.format("R.E2: Are you certain it was here?\n");
            return (S) "No such car in this parking lot!";
        }
        System.out.format("R2: returned a %s... %d spots taken", car, cars.size()-1);
        return cars.remove(cars.indexOf(car));
    }

    public static void main(String[] args) throws InterruptedException {
        Parking<String> p = new Parking<>(30, 3);
        final Thread park35 = NamedThreadFactory.create(new ParkCars(p, 35), "parking 35 cars");

        final Thread return7 = NamedThreadFactory.create(new ReturnCars(p, 7), "returning 7 cars");
        park35.run();
        return7.run();
    }

    private static class ParkCars implements Runnable {
        private Parking<String> p;
        private final int howManyCars;

        ParkCars(Parking<String> p, int howManyCars) {
            this.p = p;
            this.howManyCars = howManyCars;
        }

        @Override
        public void run() {
            int i = 0;
            while (i<howManyCars) {
                p.parkACar("sedan");
                i++;
            }
        }
    }

    private static class ReturnCars implements Runnable {
        private final Parking<String> p;
        private final int howManyCars;

        ReturnCars(Parking<String> p, int howManyCars) {
            this.p = p;
            this.howManyCars = howManyCars;
        }

        @Override
        public void run() {
            for(int i = 0; i < howManyCars; i++) {
                p.returnACar("sedan");
            }
        }
    }
}