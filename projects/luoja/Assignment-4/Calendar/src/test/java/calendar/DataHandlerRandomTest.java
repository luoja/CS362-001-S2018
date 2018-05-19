package calendar;

import java.util.Calendar;
import java.util.Random;
import java.io.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;


import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 60 seconds */
	private static final int NUM_TESTS=100;

    /**
     * Generate Random Tests that tests DataHandler Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
  		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

  		 System.out.println("Start testing...");

  		try{
 		  for (int iteration = 0; elapsed < TestTimeout; iteration++) {
  			 long randomseed =System.currentTimeMillis(); //10
   //		System.out.println(" Seed:"+randomseed );
  			 Random random = new Random(randomseed);

			 //Did not use random name since i think ValuesGenerator's getString has a chance to return NULL
			 //Also, name of file shouldn't matter for testing.
			 float probability = (float).5;
			 boolean autoSaveBool = ValuesGenerator.getBoolean(probability, random);		//50/50 chance for true/false autosave
			 DataHandler dh = new DataHandler("testName1",autoSaveBool);

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
 					appt.setValid();

 			 int startHour2=ValuesGenerator.getRandomIntBetween(random, 0, 23);
 			 int startMinute2=ValuesGenerator.getRandomIntBetween(random, 0, 59);
 			 int startDay2=ValuesGenerator.getRandomIntBetween(random, 1, 31);
 			 int startMonth2=ValuesGenerator.getRandomIntBetween(random, 1, 12);
 			 int startYear2=ValuesGenerator.getRandomIntBetween(random, 1, 2018);

 			 //Construct a new Appointment object with the initial data
 			 Appt appt2 = new Appt(startHour2,
 			 		 startMinute2 ,
 			 		 startDay2 ,
 			 		 startMonth2 ,
 			 		 startYear2 ,
 			 		 title,
 			 		description,
 			 		emailAddress);
 					appt2.setValid();

 			 int startHour3=ValuesGenerator.getRandomIntBetween(random, 0, 23);
 			 int startMinute3=ValuesGenerator.getRandomIntBetween(random, 0, 59);
 			 int startDay3=ValuesGenerator.getRandomIntBetween(random, 1, 31);
 			 int startMonth3=ValuesGenerator.getRandomIntBetween(random, 1, 12);
 			 int startYear3=ValuesGenerator.getRandomIntBetween(random, 1, 2018);

 			 //Construct a new Appointment object with the initial data
 			 Appt appt3 = new Appt(startHour3,
 			 		 startMinute3 ,
 			 		 startDay3 ,
 			 		 startMonth3 ,
 			 		 startYear3 ,
 			 		 title,
 			 		description,
 			 		emailAddress);
 					appt3.setValid();


 //		 for (int i = 0; i < NUM_TESTS; i++) {
 		//Delete Appt test
		//Delete appt that isnt saved

		assertFalse(dh.deleteAppt(appt));
		dh.saveAppt(appt);
		if(appt.getValid() == false){				//(Attempt to) delete invalid appt
			assertFalse(dh.deleteAppt(appt));
		}
		else{										//Delete valid appt
			assertTrue(dh.deleteAppt(appt));
		}

		//getApptRange test
		//Setting recurrence for 3 appts
		float probability1 = (float).5;
		boolean recurDaysNull = ValuesGenerator.getBoolean(probability1, random);		//50/50 chance to use null recurDays array
		int[] recurDays = null;
		if (recurDaysNull == false){
			int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
		}
		int recur=ApptRandomTest.RandomSelectRecur(random);
		int recurIncrement = ValuesGenerator.RandInt(random);
		int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
		appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

		float probability2 = (float).5;
		boolean recurDaysNull2 = ValuesGenerator.getBoolean(probability2, random);		//50/50 chance to use null recurDays array
		int[] recurDays2 = null;
		if (recurDaysNull2 == false){
			int sizeArray2=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			recurDays2=ValuesGenerator.generateRandomArray(random, sizeArray2);
		}
		int recur2=ApptRandomTest.RandomSelectRecur(random);
		int recurIncrement2 = ValuesGenerator.RandInt(random);
		int recurNumber2=ApptRandomTest.RandomSelectRecurForEverNever(random);
		appt2.setRecurrence(recurDays2, recur2, recurIncrement2, recurNumber2);

		float probability3 = (float).5;
		boolean recurDaysNull3 = ValuesGenerator.getBoolean(probability3, random);		//50/50 chance to use null recurDays array
		int[] recurDays3 = null;
		if (recurDaysNull3 == false){
			int sizeArray3=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			recurDays3=ValuesGenerator.generateRandomArray(random, sizeArray3);
		}
		int recur3=ApptRandomTest.RandomSelectRecur(random);
		int recurIncrement3 = ValuesGenerator.RandInt(random);
		int recurNumber3=ApptRandomTest.RandomSelectRecurForEverNever(random);
		appt.setRecurrence(recurDays3, recur3, recurIncrement3, recurNumber3);

		//2 Georgian Calendars for getApptRange input
		int year = ValuesGenerator.getRandomIntBetween(random, 1, 2018);
		int month = ValuesGenerator.getRandomIntBetween(random, 0, 11);
		int day = ValuesGenerator.getRandomIntBetween(random, 1, 31);
		GregorianCalendar calendar1 = new GregorianCalendar(year, month, day);

		int year2 = ValuesGenerator.getRandomIntBetween(random, 1, 2018);
		int month2 = ValuesGenerator.getRandomIntBetween(random, 0, 11);
		int day2 = ValuesGenerator.getRandomIntBetween(random, 1, 31);
		GregorianCalendar calendar2 = new GregorianCalendar(year2, month2, day2);

		//Calendars representing appointment dates
		GregorianCalendar apptCal1 = new GregorianCalendar(startYear, startMonth, startDay);
		GregorianCalendar apptCal2 = new GregorianCalendar(startYear2, startMonth2, startDay2);
		GregorianCalendar apptCal3 = new GregorianCalendar(startYear3, startMonth3, startDay3);


		try{
			dh.saveAppt(appt);
			// Test w/ no occurences/recurrence in range
			// if((apptCal1.before(calendar1))){
			// 	if(recurDaysNull == false){
			// 		if((recurDays.length == 0) || recurNumber == 0){
			// 			LinkedList<CalDay> caldays0 = new LinkedList<CalDay>();
			// 			final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
			// 			System.setOut(new PrintStream(outContent));
			// 			caldays0 = (LinkedList<CalDay>) dh.getApptRange(calendar1, calendar2);
			// 			assertEquals("", outContent.toString());
			// 		}
			// 	}
			// }
			dh.saveAppt(appt2);
			dh.saveAppt(appt3);

			LinkedList<CalDay> caldays = new LinkedList<CalDay>();
			caldays = (LinkedList<CalDay>) dh.getApptRange(calendar1,calendar2);

			//This prints way too much.
			//System.out.println(caldays);

		}catch (DateOutOfRangeException D){

		}
 // 	}

  			  elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
  				 if((iteration%100)==0 && iteration!=0 )
  					   System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);


			//Delete datahandler files
			File f = new File("./testName1");
			f.delete();
  		 }
  	 }catch(NullPointerException e){

  	 }
  	  System.out.println("Done testing...");
	 }
}
