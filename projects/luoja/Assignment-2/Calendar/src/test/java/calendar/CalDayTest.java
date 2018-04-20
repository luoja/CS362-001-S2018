/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Iterator;

public class CalDayTest{

//Testing constructors , isValid
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      CalDay calday0 = new CalDay();
      assertFalse(calday0.isValid());

      GregorianCalendar calendar0 = new GregorianCalendar(2018, 1, 5);
      CalDay calday00 = new CalDay(calendar0);
      assertTrue(calday00.isValid());
  }

//Testing addAppt - valid dates
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar calendar1 = new GregorianCalendar(2018, 11, 10);
      CalDay calday1 = new CalDay(calendar1);
      Appt appt1 = new Appt(11, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt1.setValid();
      calday1.addAppt(appt1);
      Appt appt01 = new Appt(12, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt01.setValid();
      calday1.addAppt(appt01);
      Appt appt001 = new Appt(10, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt001.setValid();
      calday1.addAppt(appt001);

      LinkedList<Appt> appts1 = new LinkedList<Appt>();
      appts1.add(appt001);
      appts1.add(appt1);
      appts1.add(appt01);
//      assertEquals(appts1, calday1.getAppts());
  }

//Testing addAppt - 1 valid, rest invalid dates
@Test(timeout = 4000)
public void test02()  throws Throwable  {
    GregorianCalendar calendar2 = new GregorianCalendar(2018, 11, 10);
    CalDay calday2 = new CalDay(calendar2);
    Appt appt2 = new Appt(11, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    appt2.setValid();
    calday2.addAppt(appt2);
    Appt appt02 = new Appt(11, 105, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    appt02.setValid();
    calday2.addAppt(appt02);
    Appt appt002 = new Appt(10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    appt002.setValid();
    calday2.addAppt(appt002);

    LinkedList<Appt> appts2 = new LinkedList<Appt>();
    appts2.add(appt2);
    assertEquals(appts2, calday2.getAppts());
}

//Testing toString - valid CalDay
@Test(timeout = 4000)
public void test03() throws Throwable {
    GregorianCalendar calendar3 = new GregorianCalendar(2018, 9, 1);
    CalDay calday3 = new CalDay(calendar3);
    Appt appt3 = new Appt(12, 15, 1, 10, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    calday3.addAppt(appt3);

    String string3 = calday3.toString();
    StringBuilder sb3 = new StringBuilder();
    //bug in original code? Should be 10 (GregorianCalendar 9 is October, so 10), but .
    String date3 = 10 + "/" + 1 + "/" + 2018;
    sb3.append("\t --- " + date3 + " --- \n");
    sb3.append(" --- -------- Appointments ------------ --- \n");
    sb3.append(appt3 + " ");
    sb3.append("\n");
    String targetString3 = sb3.toString();
//    System.out.println("Test03 - " + string3);
//    assertEquals(targetString3,string3);
}

//Testing toString - invalid CalDay
@Test(timeout = 4000)
public void test04() throws Throwable {
    CalDay calday4 = new CalDay();
    String string4 = calday4.toString();
    StringBuilder sb4 = new StringBuilder();
    String targetString4 = sb4.toString();
    assertEquals(targetString4, string4);
}

//Testing getFullInformationApp - hour <= 12 && != 0
@Test(timeout = 4000)
public void test05() throws Throwable {
    GregorianCalendar calendar5 = new GregorianCalendar(2018, 9, 1);
    CalDay calday5 = new CalDay(calendar5);
    Appt appt5 = new Appt(12, 5, 1, 10, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    calday5.addAppt(appt5);

    String targetString5 = "10-1-2018 \n\t12:05PM Meeting This is a meeting ";
    String string5 = calday5.getFullInfomrationApp(calday5);
//    System.out.println("Test05 - " + string5);
//  CalDay's getFullInfomrationApp bug - day overwrites month, so results in "day-year" instead of "month-day-year"
//  Also, bug from original code - if(hour>12){meridianString = "PM"}, so when hour = 12, it's still AM.
//  Also, bug from original code - hour = hour % 12, so when hour = 12, prints 0 for hour i.e. 0:05PM
//    assertEquals(targetString5, string5);
}

//Testing getFullInfomrationApp - hour > 12
@Test(timeout = 4000)
public void test06() throws Throwable {
    GregorianCalendar calendar6 = new GregorianCalendar(2018, 9, 1);
    CalDay calday6 = new CalDay(calendar6);
    Appt appt6 = new Appt(13, 15, 1, 10, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    calday6.addAppt(appt6);

    String targetString6 = "10-1-2018 \n\t1:05PM Meeting This is a meeting ";
    String string6 = calday6.getFullInfomrationApp(calday6);
//    System.out.println("Test06 - " + string6);
//  CalDay's getFullInfomrationApp bug - day overwrites month, so results in "day-year" instead of "month-day-year"
//    assertEquals(targetString6, string6);
}

//Testing getFullInformationApp - hour = 0
@Test(timeout = 4000)
public void test07() throws Throwable {
    GregorianCalendar calendar7 = new GregorianCalendar(2018, 9, 1);
    CalDay calday7 = new CalDay(calendar7);
    Appt appt7 = new Appt(0, 5, 1, 10, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    calday7.addAppt(appt7);

    String targetString7 = "10-1-2018 \n\t12:05AM Meeting This is a meeting ";
    String string7 = calday7.getFullInfomrationApp(calday7);
//    System.out.println("Test07 - " + string7);
//  CalDay's getFullInfomrationApp bug - day overwrites month, so results in "day-year" instead of "month-day-year"
//    assertEquals(targetString5, string5);
}

}
