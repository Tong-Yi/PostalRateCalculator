import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import static com.example.yito.postalratecalculator.MainActivity.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dylan Chan on 2/18/2016.
 */

public class MainActivityTest {
    //isStandard(int depth, int width, int height, int weight)
    @Test
    public void checkStandardDepth(){
        boolean checkStandard = isStandard(10,10,10,10);
        assertFalse(checkStandard);
    }

    @Test
    public void checkStandardWidth(){
        boolean checkStandard = isStandard(5,300,10,10);
        boolean checkStandard2 = isStandard(5,100,10,10);
        assertFalse(checkStandard);
        assertFalse(checkStandard2);
    }
    @Test
    public void CheckStandardHeight(){
        boolean checkStandard = isStandard(5,200,10,10);
        boolean checkStandard2 = isStandard(5,200,200,10);
        assertFalse(checkStandard);
        assertFalse(checkStandard2);
    }

    @Test
    public void CheckStandardWeight(){
        boolean checkStandard = isStandard(5,200,100,600);
        assertFalse(checkStandard);

    }

    @Test
    public void checkNonStandardDepth(){
        boolean checkNonStandard = isNonStandard(25, 200, 100, 600);
        assertFalse(checkNonStandard);
    }

    @Test
    public void checkNonStandardWidth(){
        boolean checkNonStandard = isNonStandard(5,100,10,10);
        boolean checkNonStandard2 = isNonStandard(5, 400, 10, 10);
        assertFalse(checkNonStandard);
        assertFalse(checkNonStandard2);
    }

    @Test
    public void checkNonStandardHeight(){
        boolean checkNonStandard = isNonStandard(5, 140, 80, 10);
        boolean checkNonStandard2 = isNonStandard(5, 140, 300, 10);
        assertFalse(checkNonStandard);
        assertFalse(checkNonStandard2);
    }

    @Test
    public void checkNonStandardWeight(){
        boolean checkNonStandard = isNonStandard(5, 140, 100, 600);
        assertFalse(checkNonStandard);

    }

    @Test
    public void checkStandard() {
        assertTrue(isStandard(4, 150, 100, 20));
    }

    @Test
    public void checkNonStandard() {
        assertTrue(isNonStandard(10, 160, 100, 100));
    }

    @Test
    public void package1() {
        boolean checkStandard = isStandard(4, 150, 100, 20);
        assertTrue(checkStandard);
        boolean checkNonStandard = isNonStandard(4, 150, 100, 20);
        assertEquals(100, checkPrice("Canada", checkStandard, checkNonStandard, 20));
    }

    @Test
    public void package2() {
        boolean checkStandard = isStandard(4, 150, 100, 40);
        assertTrue(checkStandard);
        boolean checkNonStandard = isNonStandard(4, 150, 100, 40);
        assertEquals(120, checkPrice("Canada", checkStandard, checkNonStandard, 40));
    }

    @Test
    public void package3() {
        boolean checkStandard = isStandard(10, 300, 220, 90);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 90);
        assertTrue(checkNonStandard);
        assertEquals(180, checkPrice("Canada", checkStandard, checkNonStandard, 90));
    }

    @Test
    public void package4() {
        boolean checkStandard = isStandard(10, 300, 220, 150);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 150);
        assertTrue(checkNonStandard);
        assertEquals(295, checkPrice("Canada", checkStandard, checkNonStandard, 150));
    }

    @Test
    public void package5() {
        boolean checkStandard = isStandard(10, 300, 220, 250);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 250);
        assertTrue(checkNonStandard);
        assertEquals(410, checkPrice("Canada", checkStandard, checkNonStandard, 250));
    }

    @Test
    public void package6() {
        boolean checkStandard = isStandard(10, 300, 220, 350);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 350);
        assertTrue(checkNonStandard);
        assertEquals(470, checkPrice("Canada", checkStandard, checkNonStandard, 350));
    }

    @Test
    public void package7() {
        boolean checkStandard = isStandard(10, 300, 220, 450);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 450);
        assertTrue(checkNonStandard);
        assertEquals(505, checkPrice("Canada", checkStandard, checkNonStandard, 450));
    }

    @Test
    public void package8() {
        boolean checkStandard = isStandard(4, 150, 100, 20);
        assertTrue(checkStandard);
        boolean checkNonStandard = isNonStandard(4, 150, 100, 20);
        assertEquals(120, checkPrice("United States", checkStandard, checkNonStandard, 20));
    }


    @Test
    public void package9() {
        boolean checkStandard = isStandard(4, 150, 100, 40);
        assertTrue(checkStandard);
        boolean checkNonStandard = isNonStandard(4, 150, 100, 40);
        assertEquals(180, checkPrice("United States", checkStandard, checkNonStandard, 40));
    }

    @Test
    public void package10(){
        boolean checkStandard = isStandard(10, 300, 220, 50);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 50);
        assertTrue(checkNonStandard);
        assertEquals(295, checkPrice("United States", checkStandard, checkNonStandard, 50));
    }

    @Test
    public void package11(){
        boolean checkStandard = isStandard(10, 300, 220, 150);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 150);
        assertTrue(checkNonStandard);
        assertEquals(515, checkPrice("United States", checkStandard, checkNonStandard, 150));
    }

    @Test
    public void package12(){
        boolean checkStandard = isStandard(10, 300, 220, 400);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 400);
        assertTrue(checkNonStandard);
        assertEquals(1030, checkPrice("United States", checkStandard, checkNonStandard, 400));
    }

    @Test
    public void package13(){
        boolean checkStandard = isStandard(4, 150, 100, 20);
        assertTrue(checkStandard);
        boolean checkNonStandard = isNonStandard(4, 150, 100, 20);
        assertEquals(250, checkPrice("International", checkStandard, checkNonStandard, 20));
    }

    @Test
    public void package14(){
        boolean checkStandard = isStandard(4, 150, 100, 40);
        assertTrue(checkStandard);
        boolean checkNonStandard = isNonStandard(4, 150, 100, 40);
        assertEquals(360, checkPrice("International", checkStandard, checkNonStandard, 40));
    }

    @Test
    public void package15(){
        boolean checkStandard = isStandard(10, 300, 220, 90);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 90);
        assertTrue(checkNonStandard);
        assertEquals(590, checkPrice("International", checkStandard, checkNonStandard, 90));
    }

    @Test
    public void package16(){
        boolean checkStandard = isStandard(10, 300, 220, 150);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 150);
        assertTrue(checkNonStandard);
        assertEquals(1030, checkPrice("International", checkStandard, checkNonStandard, 150));
    }

    @Test
    public void package17(){
        boolean checkStandard = isStandard(10, 300, 220, 250);
        assertFalse(checkStandard);
        boolean checkNonStandard = isNonStandard(10, 300, 220, 250);
        assertTrue(checkNonStandard);
        assertEquals(2060, checkPrice("International", checkStandard, checkNonStandard, 250));
    }
}