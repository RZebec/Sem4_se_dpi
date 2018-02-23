package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarbonMonoxideFilterTest {

    @Test
    public void CarbonMonoxideFilter_FilterObjects() {
        List<String> cigaretteSmoke = CigaretteSmoke.createCigaretteSmoke(70, 120, 30, 60);

        ICigaretteInhalationFilter filter = new CarbonMonoxideFilter();

        List<String> filteredSmoke = filter.filterObjects(cigaretteSmoke);

        Assert.assertEquals("Should have 60 elements.", 60, filteredSmoke.size());
    }
}