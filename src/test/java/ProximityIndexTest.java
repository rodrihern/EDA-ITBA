import ExamenesViejos.P1_2022_1C.ProximityIndex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProximityIndexTest {

    @Test
    public void testCase() {
        ProximityIndex proximityIndex = new ProximityIndex();
        proximityIndex.initialize(new String[] {"Ana", "Carlos", "Juan", "Yolanda"});
        assertEquals("Yolanda", proximityIndex.search("Carlos", 2));
        assertEquals("Carlos", proximityIndex.search("Carlos", 0));
        assertEquals("Ana", proximityIndex.search("Carlos", 3));
        assertEquals("Juan", proximityIndex.search("Ana", 14));
        assertEquals("Juan", proximityIndex.search("Ana", -2));
        assertEquals("Yolanda", proximityIndex.search("Ana", -17));
        assertEquals("Juan", proximityIndex.search("Juan", -4));
        assertEquals(null, proximityIndex.search("XXX", -4));



    }
}
