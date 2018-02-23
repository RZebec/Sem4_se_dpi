package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TarsFilterTest {

    @Test
    public void TarsFilter_FilterObjects() {
        List<String> cigaretteSmoke = CigaretteSmoke.createCigaretteSmoke(65, 30, 500, 2);

        ICigaretteInhalationFilter filter = new TarsFilter();

        List<String> filteredSmoke = filter.filterObjects(cigaretteSmoke);

        Assert.assertEquals("Should have 30 elements.", 30, filteredSmoke.size());
    }
}