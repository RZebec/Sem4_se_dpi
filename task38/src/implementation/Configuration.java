package implementation;

import implementation.helpers.MersenneTwister;

/**
 * The Configuration for the smoking simulation.
 */
public enum Configuration {
    instance;
    /**
     * The instance of the Mersenne Twister used in the simulation.
     */
    public MersenneTwister mersenneTwister = new MersenneTwister();
}