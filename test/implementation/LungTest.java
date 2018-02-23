package implementation;

import implementation.cancerstates.S0;
import implementation.cancerstates.S1;
import implementation.cancerstates.S2;
import implementation.cancerstates.S3;
import org.junit.Assert;
import org.junit.Test;

public class LungTest {

    @Test
    public void Lung_PromoteCancerStateIfTheNumberOfInfectedCellsIsReached_StateS1() {
        class TestLung extends Lung {
            public TestLung(String lungPosition) {
                super(lungPosition);
            }

            @Override
            public long countInfectedCells() {
                return 25000;
            }
        }

        Lung lung = new TestLung("Left");

        lung.setCancerState(new S0());

        lung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();

        Assert.assertTrue("Should switch State to S1!", lung.getCancerState() instanceof S1);
    }

    @Test
    public void Lung_PromoteCancerStateIfTheNumberOfInfectedCellsIsReached_StateS2() {
        class TestLung extends Lung {
            public TestLung(String lungPosition) {
                super(lungPosition);
            }

            @Override
            public long countInfectedCells() {
                return 50000;
            }
        }

        Lung lung = new TestLung("Left");

        lung.setCancerState(new S1());

        lung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();

        Assert.assertTrue("Should switch State to S2!", lung.getCancerState() instanceof S2);
    }

    @Test
    public void Lung_PromoteCancerStateIfTheNumberOfInfectedCellsIsReached_StateS3() {
        class TestLung extends Lung {
            public TestLung(String lungPosition) {
                super(lungPosition);
            }

            @Override
            public long countInfectedCells() {
                return 100000;
            }
        }

        Lung lung = new TestLung("Left");

        lung.setCancerState(new S2());

        lung.promoteCancerStateIfTheNumberOfInfectedCellsIsReached();

        Assert.assertTrue("Should switch State to S3!", lung.getCancerState() instanceof S3);
    }

    @Test
    public void Lung_Add_Remove_Listener() {
        ILungListener lungListener = new CentralNervousSystem(new Brain());

        Lung lung = new Lung("Left");

        lung.addListener(lungListener);

        Assert.assertTrue("Should have a listener!", lung.getListeners().contains(lungListener));

        lung.removeListener(lungListener);

        Assert.assertTrue("Should have removed the listener", !lung.getListeners().contains(lungListener));
    }

    @Test
    public void Lung_CountInfectedCells_StateS0() {
        Lung lung = new Lung("Left");

        lung.setCancerState(new S0());

        for(int i=0; i<7000; i++)
            lung.randomlyPlaceValueInLungCell("T");

        lung.setCancerCellsForPrepositionedCells();

        final long expectedCount = (long) (0.10 * 7000.0);
        long infectedCellCount = lung.countInfectedCells();

        Assert.assertEquals("Should have aprox. 700 infected cells", expectedCount, infectedCellCount, 300);
    }

    @Test
    public void Lung_CountInfectedCells_StateS1() {
        Lung lung = new Lung("Left");

        lung.setCancerState(new S1());

        for(int i=0; i<7000; i++)
            lung.randomlyPlaceValueInLungCell("T");

        lung.setCancerCellsForPrepositionedCells();

        final long expectedCount = (long) (0.15 * 7000.0);
        long infectedCellCount = lung.countInfectedCells();

        Assert.assertEquals("Should have aprox. 1050 infected cells", expectedCount, infectedCellCount, 300);
    }

    @Test
    public void Lung_CountInfectedCells_StateS2() {
        Lung lung = new Lung("Left");

        lung.setCancerState(new S2());

        for(int i=0; i<7000; i++)
            lung.randomlyPlaceValueInLungCell("T");

        lung.setCancerCellsForPrepositionedCells();

        final long expectedCount = (long) (0.30 * 7000.0);
        long infectedCellCount = lung.countInfectedCells();

        Assert.assertEquals("Should have aprox. 2100 infected cells", expectedCount, infectedCellCount, 300);
    }

    @Test
    public void Lung_NeighbouringCellsAreCorrectlySet() {
        Lung lung = new Lung("Left");

        LungCell lungCell = lung.getLungCellByPosition(300, 300);

        Assert.assertTrue("Should contain left neighbouring cell!", lungCell.getNeighbouringCells().contains(lung.getLungCellByPosition(300, 299)));
        Assert.assertTrue("Should contain right neighbouring cell!", lungCell.getNeighbouringCells().contains(lung.getLungCellByPosition(300, 301)));
        Assert.assertTrue("Should contain upper neighbouring cell!", lungCell.getNeighbouringCells().contains(lung.getLungCellByPosition(299, 300)));
        Assert.assertTrue("Should contain lower neighbouring cell!", lungCell.getNeighbouringCells().contains(lung.getLungCellByPosition(301, 300)));
    }
}
