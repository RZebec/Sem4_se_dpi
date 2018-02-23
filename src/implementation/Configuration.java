package implementation;

import implementation.helpers.MersenneTwister;

public enum Configuration {
    instance;

    public MersenneTwister mersenneTwister = new MersenneTwister();
}