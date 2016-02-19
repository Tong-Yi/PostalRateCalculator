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
    public void checkStandard(){
        assertTrue(isStandard(4, 150, 100, 20));
    }

    @Test
    public void checkNonStandard() {
        assertTrue(isNonStandard(10,160,100,100));
    }


}
