package implementation.cigaretteinhalationfilters;

import implementation.ICigaretteInhalationFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IrritantGasesFilterTest {
    @Test
    public void IrritantGasesFilter_FilterObjects() {
        List<String> cigaretteSmoke = CigaretteSmoke.createCigaretteSmoke(35, 45, 5, 2);

        ICigaretteInhalationFilter filter = new IrritantGasesFilter();

        List<String> filteredSmoke = filter.filterObjects(cigaretteSmoke);

        Assert.assertEquals("Should have 5 elements.", 5, filteredSmoke.size());
    }
}