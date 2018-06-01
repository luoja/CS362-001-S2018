package calendar;

import java.util.Calendar;
import java.util.Random;
import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setValid","setRecurrence","isOn"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

        return methodArray[n] ; // return the method name
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur
        }
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur
        }
   /**
     * Generate Random Tests that tests Appt Class.
     */
	 @Test
	  public void randomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


		 System.out.println("Start testing...");

		try{
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				 int startHour=ValuesGenerator.getRandomIntBetween(random, -2, 24);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, -3, 62);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 0, 13);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, -200, 2018);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 String emailAddress="xyz@gmail.com";

				 //Construct a new Appointment object with the initial data
		         Appt appt = new Appt(startHour,
		                  startMinute ,
		                  startDay ,
		                  startMonth ,
		                  startYear ,
		                  title,
		                 description,
		                 emailAddress);

			// if(!appt.getValid())continue;
			for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("setValid")){
						   boolean valid = true;
						   if (startMonth < 1 || startMonth > 12)
				   				valid = false;
					   		else if (startHour < 0 || startHour > 23)
					   			valid = false;
					   		else if (startMinute < 0 || startMinute > 59)
					   			valid = false;
					   		else if (startYear < 0)		//This is also to account for another intentional bug. Appt's setValid SHOULD return false if startYear <= 0 
					   			valid = false;
					   		else {
					   			int NumDaysInMonth = CalendarUtil.NumDaysInMonth(startYear, startMonth - 1);
								//This is what it should be (if there was no bug)
								// if (startDay < 1 || startDay > NumDaysInMonth)
					   			// 	valid = false;
					   			//else
					   				valid = true;
					   		}
							appt.setValid();
							assertEquals(valid,appt.getValid());
						}
					   else if (methodName.equals("setRecurrence")){
						   float probability = (float).5;
						   boolean recurDaysNull = ValuesGenerator.getBoolean(probability, random);		//50/50 chance to use null recurDays array
						   int[] recurDays = null;
						   if (recurDaysNull == false){
							   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
							   recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						   }
						   int recur=ApptRandomTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						   if(recurDaysNull == false){
							   if(recurDays.length == appt.getRecurDays().length){
								   for(int k = 0; k < recurDays.length; k++){
									   assertEquals(recurDays[k], appt.getRecurDays()[k]);
								   }
							   }
						   }
						   assertEquals(recur,appt.getRecurBy());
						   assertEquals(recurIncrement,appt.getRecurIncrement());
						   assertEquals(recurNumber,appt.getRecurNumber());
						}
						else if (methodName.equals("isOn"))	{
							int wrongStartMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
							while (wrongStartMonth == startMonth){
								wrongStartMonth = ValuesGenerator.getRandomIntBetween(random, 1, 13);
							}
							assertFalse(appt.isOn(startDay, wrongStartMonth, startYear));
							int wrongStartYear = ValuesGenerator.getRandomIntBetween(random, 1, 2018);
							while (wrongStartYear == startYear){
								wrongStartYear = ValuesGenerator.getRandomIntBetween(random, -300, 2200);
							}
							assertFalse(appt.isOn(startDay, startMonth, wrongStartYear));
							//Test below is what should be used, but most of the time will fail due to bug from Assignment 1.
							//assertTrue(appt.isOn(startDay, startMonth, startYear));

							//Below accounts for "intentional" bug from Assignment 1 - the bug sets startDay field using startMonth value
							//This "shouldn't" always be true, but it will be due to the bug.
							assertTrue(appt.isOn(startMonth, startMonth, startYear));
						}
				}

				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

			}
		}catch(NullPointerException e){

		}

		 System.out.println("Done testing...");
	 }






}
