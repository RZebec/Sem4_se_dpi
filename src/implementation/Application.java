package implementation;

import implementation.cigaretteinhalationfilters.TarsFilter;
import implementation.helpers.MersenneTwister;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Brain brain = new Brain();
        ILungListener centralNervousSystem = new CentralNervousSystem(brain);
        Lung leftLung = new Lung();
        Lung rightLung = new Lung();

        leftLung.addListener(centralNervousSystem);
        rightLung.addListener(centralNervousSystem);

        MersenneTwister mersenneTwister = new MersenneTwister();

        while(!brain.totalLungFailure) {
            ICigaretteInhalationFilter cigaretteInhalationFilter = new TarsFilter();
            List<String> tarsSmoke = cigaretteInhalationFilter.filterObjects(createCigaretteSmoke());

            for(String tar : tarsSmoke) {
                boolean randomLungSelector = mersenneTwister.nextBoolean();
                if(randomLungSelector) {
                    leftLung.randomlyPlaceValueInLungCell(tar);
                } else {
                    rightLung.randomlyPlaceValueInLungCell(tar);
                }
            }

            leftLung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();
            rightLung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();
        }
    }

    private static List<String> createCigaretteSmoke() {
        List<String> cigaretteSmoke = new ArrayList<>();
        for(int i=0; i<20; i++) {
            cigaretteSmoke.add("N");
        }
        for(int i=0; i<240; i++) {
            cigaretteSmoke.add("G");
        }
        for(int i=0; i<700; i++) {
            cigaretteSmoke.add("T");
        }
        for(int i=0; i<40; i++) {
            cigaretteSmoke.add("C");
        }
        return cigaretteSmoke;
    }
}