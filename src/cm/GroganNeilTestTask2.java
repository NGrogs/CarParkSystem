package cm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Created by Neil Grogan on 20/02/2019.
 */

public class GroganNeilTestTask2 {

	// TEST CASE 1
	// empty arraylist 
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void emptyArrayList() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(2.00);
		BigDecimal reducedRate = new BigDecimal(1.00);
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
        Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 2
	// normal period overlaps
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void normalPeriodOverlap() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(2.00);
		BigDecimal reducedRate = new BigDecimal(1.00);
		Period p1 = new Period(6,8);
		Period p2 = new Period(7,9);
		Period p3 = new Period(13,15);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		normal.add(p2);
		
		reduced.add(p3);
		
        Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
       
    }
	
	// TEST CASE 3
	// Both periods overlaps
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void bothPeriodsOverlap() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(2.00);
		BigDecimal reducedRate = new BigDecimal(1.00);
		Period p1 = new Period(2,9);
		Period p2 = new Period(3,10);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 4
	// stay after midnight
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void afterMidnight() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(2.00);
		BigDecimal reducedRate = new BigDecimal(1.00);
		Period p1 = new Period(3,7);
		Period p2 = new Period(23,1);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 5
	// Missing periods
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void missingPeriods() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(2.00);
		BigDecimal reducedRate = new BigDecimal(1.00);	
		
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, null, null);
    }
	
	// TEST CASE 6
	// NULL periods
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void nullPeriods() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(1,2);
		Period p2 = new Period(6,7);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, null, null, reduced, normal);
    }
	
	// TEST CASE 7
	// Period start > period end
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void periodStartHigher() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(3,1);
		Period p2 = new Period(3,5);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 8
	// 25 hour
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void twentyFiveHour() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(9,12);
		Period p2 = new Period(13,24);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 9
	// negative hour
	@org.junit.Test(expected = IllegalArgumentException.class)
	public void minusHour() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(1.00);
		Period p1 = new Period(-1,3);
		Period p2 = new Period(9,11);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
	}
	
	// TEST CASE 10
	// test rate < 0
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void belowZeroRate() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(-1.00);
		BigDecimal reducedRate = new BigDecimal(-.000001);
		Period p1 = new Period(5,7);
		Period p2 = new Period(9,11);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 11
	// reduce rate > normal rate
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void reduceRateHigher() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(2.00);
		BigDecimal reducedRate = new BigDecimal(3.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(9,11);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 12
	// string rate
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void stringRate() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal("3");
		BigDecimal reducedRate = new BigDecimal(1.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(9,11);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
    }
	
	// TEST CASE 13
	// duration method
	@org.junit.Test(expected = IllegalArgumentException.class)
    public void durationMethod() throws Exception {
		Period p1 = new Period(5,7);
		// any hour started counts as full so 2-5 should be 3 hours?
		assertEquals(3, p1.duration());
    }
	
	// TEST CASE 14
	// nullrate
	@org.junit.Test
    public void nullRate() throws Exception {
		CarParkKind cpk = CarParkKind.STAFF;
		Period p1 = new Period(5,7);
		Period p2 = new Period(9,11);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		reduced.add(p2);
		
		Rate newRate = new Rate(cpk, null, null, reduced, normal);
    }
	
	// TEST CASE 15
	// normal period
	@org.junit.Test
	public void normalPeriodCalculate() {
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(13,14);
		Period p3 = new Period(18,20);
		
		Period p4 = new Period(8,12);
		Period p5 = new Period(15,17);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		normal.add(p2);
		normal.add(p3);
		
		reduced.add(p4);
		reduced.add(p5);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
		
		Period pTest = new Period(9,12);
		assertEquals(15.00, newRate.calculate(pTest));
	}
	
	// TEST CASE 16
	// reduced period
	@org.junit.Test
	public void reducedPeriodCalculate(){
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(13,14);
		Period p3 = new Period(18,20);
		
		Period p4 = new Period(8,12);
		Period p5 = new Period(15,17);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		normal.add(p2);
		normal.add(p3);
		
		reduced.add(p4);
		reduced.add(p5);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
		
		Period pTest = new Period(6,7);
		assertEquals(4.00, newRate.calculate(pTest));
	}
	
	// TEST CASE 17
	// mixed period
	@org.junit.Test
	public void mixedPeriodCalculate(){
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(13,14);
		Period p3 = new Period(18,20);
		
		Period p4 = new Period(8,12);
		Period p5 = new Period(15,17);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		normal.add(p2);
		normal.add(p3);
		
		reduced.add(p4);
		reduced.add(p5);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
		
		Period pTest = new Period(11,15);
		assertEquals(13.00, newRate.calculate(pTest));
	}
	
	// TEST CASE 18
	// free period
	@org.junit.Test
	public void freePeriodCalculate(){
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(13,14);
		Period p3 = new Period(18,20);
		
		Period p4 = new Period(8,12);
		Period p5 = new Period(15,17);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		normal.add(p2);
		normal.add(p3);
		
		reduced.add(p4);
		reduced.add(p5);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
		
		Period pTest = new Period(2,4);
		assertEquals(0.00, newRate.calculate(pTest));
	}
	
	// TEST CASE 19
	// mixed free period
	@org.junit.Test
	public void mixedFreePeriodCalculate(){
		CarParkKind cpk = CarParkKind.STAFF;
		BigDecimal normalRate = new BigDecimal(3.00);
		BigDecimal reducedRate = new BigDecimal(2.00);
		Period p1 = new Period(5,7);
		Period p2 = new Period(13,14);
		Period p3 = new Period(18,20);
		
		Period p4 = new Period(8,12);
		Period p5 = new Period(15,17);
		
		ArrayList<Period> normal = new ArrayList<>();
		ArrayList<Period> reduced = new ArrayList<>();
		
		normal.add(p1);
		normal.add(p2);
		normal.add(p3);
		
		reduced.add(p4);
		reduced.add(p5);
		
		Rate newRate = new Rate(cpk, normalRate, reducedRate, reduced, normal);
		
		Period pTest = new Period(4,9);
		assertEquals(12.00, newRate.calculate(pTest));
	}
	
	// TEST CASE 20
	// create a car park status enum
	@org.junit.Test
	public void createCarParkStatus() throws Exception{
		CarParkStatus cps = CarParkStatus.OPEN;
	}
	
}
