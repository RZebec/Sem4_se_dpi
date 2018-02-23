package implementation;

import implementation.cancerstates.S0;
import implementation.cancerstates.S1;
import implementation.cancerstates.S2;
import implementation.cancerstates.S3;
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