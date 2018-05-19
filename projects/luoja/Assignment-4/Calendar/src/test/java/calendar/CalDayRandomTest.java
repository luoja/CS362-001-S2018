package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
//	private static final int NUM_TESTS=100;



    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void randomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
 		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

 		 System.out.println("Start testing...");

 		try{
		  for (int iteration = 0; elapsed < TestTimeout; iteration++) {
 			 long randomseed =System.currentTimeMillis(); //10
  //		System.out.println(" Seed:"+randomseed );
 			 Random random = new Random(randomseed);
			 int year = ValuesGenerator.getRandomIntBetween(random, 1, 2018);
			 int month = ValuesGenerator.getRandomIntBetween(random, 0, 11);
			 int day = ValuesGenerator.getRandomIntBetween(random, 1, 31);

			 GregorianCalendar calendar1 = new GregorianCalendar(year, month, day);
			 CalDay calday1 = new CalDay(calendar1);

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
			 calday1.addAppt(appt);
			 calday1.addAppt(appt2);
			 calday1.addAppt(appt3);
			 LinkedList<Appt> appts = new LinkedList<Appt>();
			 //Add appts to the "expected" appt list.
			 if (appt.getValid()) {
				 boolean flag = false;	//True if appt added, false otherwise
				for (int i = 0; i < appts.size(); i++) {
					//Put the appointment in the correct order - finish this
					if ((appts.get(i)).getStartHour() > appt.getStartHour()) {
						appts.add(i, appt);
						flag = true;
						break;
					}
				}
				if(flag == false){
					appts.add(appt);
				}
			}
			if (appt2.getValid()) {
				boolean flag = false;	//True if appt added, false otherwise
			   for (int i = 0; i < appts.size(); i++) {
				   //Put the appointment in the correct order - finish this
				   if ((appts.get(i)).getStartHour() > appt2.getStartHour()) {
					   appts.add(i, appt2);
					   flag = true;
					   break;
				   }
			   }
			   if(flag == false){
				appts.add(appt2);
				}
		   }
		   if (appt3.getValid()) {
			   boolean flag = false;	//True if appt added, false otherwise
			  for (int i = 0; i < appts.size(); i++) {
				  //Put the appointment in the correct order - finish this
				  if ((appts.get(i)).getStartHour() > appt3.getStartHour()) {
					  appts.add(i, appt3);
					  flag = true;
					  break;
				  }
			  }
			  if(flag == false){
				appts.add(appt3);
			}
		  }
			assertEquals(appts, calday1.getAppts());

// 			 }

 			  elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
 				 if((iteration%10000)==0 && iteration!=0 ){
 					   System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
				  }
 		 }
 	 }catch(NullPointerException e){

 	 }

 	  System.out.println("Done testing...");
   	}

}
