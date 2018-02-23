package implementation.cigaretteinhalationfilters;

import java.util.ArrayList;
import java.util.List;

public class CigaretteSmoke {
    public static List<String> createCigaretteSmoke(int nicotine, int tars, int irritantGases, int carbonMonoxide) {
        List<String> cigaretteSmoke = new ArrayList<>();
        for(int i=0; i<nicotine; i++) {
            cigaretteSmoke.add("N");
        }
        for(int i=0; i<irritantGases; i++) {
            cigaretteSmoke.add("G");
        }
        for(int i=0; i<tars; i++) {
            cigaretteSmoke.add("T");
        }
        for(int i=0; i<carbonMonoxide; i++) {
            cigaretteSmoke.add("C");
        }
        return cigaretteSmoke;
    }
}
