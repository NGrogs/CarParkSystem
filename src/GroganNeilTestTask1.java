import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
/**
 * Created by Neil Grogan on 11/02/2019.
 */

public class GroganNeilTestTask1 {

	// TEST CASE 1
	// empty arraylist 
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void emptyArrayList() throws Exception {
        Rate emptyArrayList = new Rate(STAFF, 2.00, 1.00, [()], [()]);
    }
	
	// TEST CASE 2
	// normal period overlaps
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOverlap() throws Exception {
        Rate newRate = new Rate(STAFF, 2.00, 1.00, [(6,8),(7,9)], [(13,15)]);
    }
	
	// TEST CASE 3
	// Both periods overlaps
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void bothPeriodsOverlap() throws Exception {
        Rate newRate = new Rate(STAFF, 2.00, 1.00, [(2,9)], [(7,10)]);
    }
	
	// TEST CASE 4
	// stay after midnight
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void afterMidnight() throws Exception {
        Rate newRate = new Rate(STAFF, 2.00, 1.00, [(9,11)], [(23,1]));
    }
	
	// TEST CASE 5
	// Missing periods
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void missingPeriods() throws Exception {
        Rate newRate = new Rate(STAFF, 2.00, 1.00, [(1,3)], [12,]);
    }
	
	// TEST CASE 6
	// NULL periods
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void nullPeriods() throws Exception {
        Rate newRate = new Rate(STAFF, 3.00, 2.00, [(9,11)], [(NULL,15)]);
    }
	
	// TEST CASE 7
	// Period start > period end
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void periodStartHigher() throws Exception {
        Rate newRate = new Rate(STAFF, 3.00, 2.00, [(3,1)], [(4,5)]);
    }
	
	// TEST CASE 8
	// 25 hour
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void twentyFiveHour() throws Exception {
        Rate newRate = new Rate(STAFF, 3.00, 2.00, [(9,12)], [(13,24)]);
    }
	
	// TEST CASE 9
	// negative hour
	@org.junit.Test(expected = IllegalArgumentException.class)
	public void minusHour() throws Exception {
	   Rate newRate = new Rate(STAFF, 3.00, 1.00, [(-1,3)], [(9,11)]);
	}
	
	// TEST CASE 10
	// test rate < 0
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void belowZeroRate() throws Exception {
        Rate newRate = new Rate(STAFF, -1.00, -0.000001, [(9,11)], [(13,15)]);
    }
	
	// TEST CASE 11
	// reduce rate > normal rate
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void reduceRateHigher() throws Exception {
        Rate newRate = new Rate(STAFF, 2.00, 3.00, [(5,7)], [(9,11)]);
    }
	
	// TEST CASE 12
	// string rate
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void stringRate() throws Exception {
        Rate newRate = new Rate(STAFF, "3", 1, [(5,7)], [(9,11)]);
    }
	
	// TEST CASE 13
	// NULL rate
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void nullRate() throws Exception {
        Rate newRate = new Rate(STAFF, NULL, 1.00, [(5,7)], [(9,11)]);
    }
	
	// TEST CASE 14
	// maxint
	@org.junit.Test
    public void testMaxInt() {
        Rate newRate = new Rate(STAFF, Integer.MAX_VALUE, 1.00, [(5,7)], [(9,11)]);
    }
	
	// TEST CASE 15
	// normal period
	@org.junit.Test
	public void normalPeriodCalculate() {
		Rate newRate = new Rate(Staff, 3.00, 2.00, [(8,12),(15,17)],[(5,7),(13,14),(18,20)]);
		assertEquals(15.00, Rate.calculate((9,12)));
	}
	
	// TEST CASE 16
	// reduced period
	@org.junit.Test
	public void reducedPeriodCalculate(){
		Rate newRate = new Rate(Staff, 3.00, 2.00, [(8,12),(15,17)],[(5,7),(13,14),(18,20)]);
		assertEquals(4.00, Rate.calculate((6,7)));
	}
	
	// TEST CASE 17
	// mixed period
	@org.junit.Test
	public void mixedPeriodCalculate(){
		Rate newRate = new Rate(Staff, 3.00, 2.00, [(8,12),(15,17)],[(5,7),(13,14),(18,20)]);
		assertEquals(13.00, Rate.calculate((11,15)));
	}
	
	// TEST CASE 18
	// free period
	@org.junit.Test
	public void freePeriodCalculate(){
		Rate newRate = new Rate(Staff, 3.00, 2.00, [(8,12),(15,17)],[(5,7),(13,14),(18,20)]);
		assertEquals(0.00, Rate.calculate((2,4)));
	}
	
	// TEST CASE 19
	// mixed free period
	@org.junit.Test
	public void mixedFreePeriodCalculate(){
		Rate newRate = new Rate(Staff, 3.00, 2.00, [(8,12),(15,17)],[(5,7),(13,14),(18,20)]);
		assertEquals(12.00, Rate.calculate((4,9)));
	}
	
	
	
}
