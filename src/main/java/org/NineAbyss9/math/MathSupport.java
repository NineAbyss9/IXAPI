
package org.NineAbyss9.math;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**Class to solve math classes.*/
public class MathSupport {
    private static final Random random = new Random();
    public static float rand = random.nextFloat();

    public static synchronized int nextInt(int from, int to)
    {
        return random.nextInt(from, to);
    }

    public static synchronized double nextDouble()
    {
        return random.nextDouble();
    }

    public static synchronized float nextFloat()
    {
        return random.nextFloat();
    }

    public static synchronized boolean nextBool()
    {
        return random.nextBoolean();
    }

    public static ThreadLocalRandom threadLocalRandom()
    {
        return ThreadLocalRandom.current();
    }

    public static boolean quickNextBool()
    {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static int quickNextInt()
    {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int quickNextInt(int from, int to)
    {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public static float quickNextFloat()
    {
        return ThreadLocalRandom.current().nextFloat();
    }

    public static float quickNextFloat(float from, float to)
    {
        return ThreadLocalRandom.current().nextFloat(from, to);
    }

    public static double quickNextDouble()
    {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static double quickNextDouble(double from, double to)
    {
        return ThreadLocalRandom.current().nextDouble(from, to);
    }

    public static class Cos {
    }

    public static class Lerp {
        private float value;
        private double doubleValue;
        public Lerp() {
        }

        public Lerp(float delta, float start, float end) {
            run(delta, start, end);
        }

        public Lerp(double delta, double start, double end) {
            run(delta, start, end);
        }

        public void run(float delta, float start, float end) {
            value = start + delta * (end - start);
        }

        public void run(double delta, double start, double end) {
            doubleValue = start + delta * (end - start);
        }

        public float floatValue() {
            return value;
        }

        public double doubleValue() {
            return doubleValue;
        }
    }
}
