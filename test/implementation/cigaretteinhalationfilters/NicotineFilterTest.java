package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NicotineFilterTest {

    @Test
    public void NicotineFilter_FilterObjects() {
        List<String> cigaretteSmoke = CigaretteSmoke.createCigaretteSmoke(15, 100, 50, 250);

        ICigaretteInhalationFilter filter = new NicotineFilter();

        List<String> filteredSmoke = filter.filterObjects(cigaretteSmoke);

        Assert.assertEquals("Should have 15 elements.", 15, filteredSmoke.size());
    }
}