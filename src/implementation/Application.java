package implementation;

import implementation.cigaretteinhalationfilters.TarsFilter;
import implementation.helpers.MersenneTwister;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Brain brain = new Brain();
        ILungListener centralNervousSystem = new CentralNervousSystem(brain);
        Lung leftLung = new Lung("Left");
        Lung rightLung = new Lung("Right");

        leftLung.addListener(centralNervousSystem);
        rightLung.addListener(centralNervousSystem);

        MersenneTwister mersenneTwister = new MersenneTwister();

        ICigaretteInhalationFilter cigaretteInhalationFilter = new TarsFilter();
        List<String> tarsSmoke = cigaretteInhalationFilter.filterObjects(createCigaretteSmoke());

        int cigaretteCounter = 0;

        while(!brain.isTotalLungFailure()) {
            for(String tar : tarsSmoke) {
                boolean randomLungSelector = mersenneTwister.nextBoolean();
                if(randomLungSelector) {
                    leftLung.randomlyPlaceValueInLungCell(tar);
                } else {
                    rightLung.randomlyPlaceValueInLungCell(tar);
                }
            }

            if(cigaretteCounter % 10 == 0) {
                System.out.println("Cigarettes Smoked: " + cigaretteCounter);
                System.out.println("Cancer Cells in Left Lung: " + leftLung.countInfectedCells());
                System.out.println("Cancer Cells in Right Lung: " + rightLung.countInfectedCells());
            }

            leftLung.setCancerCellsForPrepositionedCells();
            rightLung.setCancerCellsForPrepositionedCells();

            leftLung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();
            rightLung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();

            cigaretteCounter++;
        }

        System.out.println("Cigarettes Smoked: " + cigaretteCounter);
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