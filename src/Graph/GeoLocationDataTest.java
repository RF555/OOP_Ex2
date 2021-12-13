package Graph;

import api.GeoLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationDataTest {

    GeoLocation gl0 = new GeoLocationData(0, 0, 0);
    GeoLocation gl1 = new GeoLocationData(1, 1, 0);
    GeoLocation gl2 = new GeoLocationData(2, 2, 0);


    @Test
    void xTest() {
        assertEquals(gl0.x(), 0);
        assertEquals(gl1.x(), 1);
        assertEquals(gl2.x(), 2);
    }

    @Test
    void yTest() {
        assertEquals(gl0.y(), 0);
        assertEquals(gl1.y(), 1);
        assertEquals(gl2.y(), 2);
    }

    @Test
    void zTest() {
        assertEquals(gl0.z(), 0);
        assertEquals(gl1.z(), 0);
        assertEquals(gl2.z(), 0);
    }
}