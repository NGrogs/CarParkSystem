package cm;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CM on 01/02/2018.
 */
public class Rate {
    private CarParkKind kind;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyReducedRate;
    private ArrayList<Period> reduced = new ArrayList<>();
    private ArrayList<Period> normal = new ArrayList<>();

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> reducedPeriods
            , ArrayList<Period> normalPeriods) {
        if (reducedPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        if (normalRate == null || reducedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(reducedRate) <= 0) {
            throw new IllegalArgumentException("The normal rate cannot be less or equal to the reduced rate");
        }
        if (!isValidPeriods(reducedPeriods) || !isValidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("The periods are not valid individually");
        }
        if (!isValidPeriods(reducedPeriods, normalPeriods)) {
            throw new IllegalArgumentException("The periods overlaps");
        }
        this.kind = kind;
        this.hourlyNormalRate = normalRate;
        this.hourlyReducedRate = reducedRate;
        this.reduced = reducedPeriods;
        this.normal = normalPeriods;
    }

    /**
     * Checks if two collections of periods are valid together
     * @param periods1
     * @param periods2
     * @return true if the two collections of periods are valid together
     */
    private boolean isValidPeriods(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        Boolean isValid = true;
        int i = 0;
        while (i < periods1.size() && isValid) {
            isValid = isValidPeriod(periods1.get(i), periods2);
            i++;
        }
        return isValid;
    }

    /**
     * checks if a collection of periods is valid
     * @param list the collection of periods to check
     * @return true if the periods do not overlap
     */
    private Boolean isValidPeriods(ArrayList<Period> list) {
        Boolean isValid = true;
        if (list.size() >= 2) {
            Period secondPeriod;
            int i = 0;
            int lastIndex = list.size()-1;
            while (i < lastIndex && isValid) {
                isValid = isValidPeriod(list.get(i), ((List<Period>)list).subList(i + 1, lastIndex+1));
                i++;
            }
        }
        return isValid;
    }

    /**
     * checks if a period is a valid addition to a collection of periods
     * @param period the Period addition
     * @param list the collection of periods to check
     * @return true if the period does not overlap in the collecton of periods
     */
    private Boolean isValidPeriod(Period period, List<Period> list) {
        Boolean isValid = true;
        int i = 0;
        while (i < list.size() && isValid) {
            isValid = !period.overlaps(list.get(i));
            i++;
        }
        return isValid;
    }
    public BigDecimal calculate(Period periodStay) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);
        BigDecimal toPay = BigDecimal.ZERO;
        
        //* new specification *//
        // max 16 
        if(this.kind == CarParkKind.STAFF)
        {
        	toPay = this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)).add(
                    this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        	//if more than 16
        	if(toPay.compareTo(BigDecimal.valueOf(16.00)) == 1)
        	{
        		toPay = BigDecimal.valueOf(16.00);
        	}
        	
        }
        // 25% off above 5.50
        if(this.kind == CarParkKind.STUDENT)
        {
        	toPay = this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)).add(
                    this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        	//if above 5.50
        	if(toPay.compareTo(BigDecimal.valueOf(5.50)) > 0)
        	{
        		// get extra amount
        		BigDecimal reducedAmount = toPay.subtract(BigDecimal.valueOf(5.50));
        		//get 75% of the extra
        		reducedAmount = reducedAmount.multiply(BigDecimal.valueOf(0.75));
        		
        		//add reduced amount to the 5.50
        		toPay = BigDecimal.valueOf(5.50);
        		toPay = toPay.add(reducedAmount);
        	}
        }
        //minimum of 3
        if(this.kind == CarParkKind.MANAGEMENT)
        {
        	toPay = this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)).add(
                    this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        	//if less than 3
        	if(toPay.compareTo(BigDecimal.valueOf(3.00)) == -1)
        	{
        		toPay = BigDecimal.valueOf(3.00);
        	}
        }
        //first 8 free - 50% off remaining
        if(this.kind == CarParkKind.VISITOR)
        {
        	
        	toPay = this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)).add(
                    this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        	
        	// if more than 8 - otherwise free
        	if(toPay.compareTo(BigDecimal.valueOf(8.00)) > 0) 
        	{
        		// get extra amount
        		BigDecimal reducedAmount = toPay.subtract(BigDecimal.valueOf(8.00));
        		
        		//get 50% of reduced amount
        		reducedAmount = reducedAmount.multiply(BigDecimal.valueOf(0.50));
        		
        		// let the pay = the discounted remainder
        		toPay = reducedAmount.stripTrailingZeros();	
        		
        	}
        	else
        	{
        		//less than 8 is free
        		toPay = BigDecimal.ZERO;
        	}
        	
        }
        
        return toPay;
    }

}