package implementation;

import implementation.organs.cancerstates.*;
import implementation.organs.Brain;
import implementation.organs.CentralNervousSystem;
import org.junit.Assert;
import org.junit.Test;

public class BrainCentralNervousSystemTest {

    @Test
    public void BrainCentralNervousSystem_TotalLungFailure() {
        Brain brain = new Brain();
        CentralNervousSystem centralNervousSystem = new CentralNervousSystem(brain);

        ICancerState cancerState = new S0();
        centralNervousSystem.lungStatusChange(cancerState);
        Assert.assertFalse("Should be false!", centralNervousSystem.isOneLungHasAlreadyReachedS3());
        Assert.assertFalse("Should be false!", brain.isTotalLungFailure());

        cancerState = new S1();
        centralNervousSystem.lungStatusChange(cancerState);
        Assert.assertFalse("Should be false!", centralNervousSystem.isOneLungHasAlreadyReachedS3());
        Assert.assertFalse("Should be false!", brain.isTotalLungFailure());

        cancerState = new S2();
        centralNervousSystem.lungStatusChange(cancerState);
        Assert.assertFalse("Should be false!", centralNervousSystem.isOneLungHasAlreadyReachedS3());
        Assert.assertFalse("Should be false!", brain.isTotalLungFailure());

        cancerState = new S3();
        centralNervousSystem.lungStatusChange(cancerState);
        Assert.assertTrue("Should be true!", centralNervousSystem.isOneLungHasAlreadyReachedS3());
        Assert.assertFalse("Should be false!", brain.isTotalLungFailure());

        centralNervousSystem.lungStatusChange(cancerState);
        Assert.assertTrue("Should be true!", brain.isTotalLungFailure());
    }
}