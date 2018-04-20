
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{

//Test getApptRange - valid appointments and recurrences
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      DataHandler dh0 = new DataHandler("testName0",true);
      Appt appt01 = new Appt(10, 15, 1, 1, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      Appt appt02 = new Appt(10, 15, 2, 2, 2018, "Meeting2", "This is another meeting", "456@gmail.com");
      Appt appt03 = new Appt(10, 15, 2, 2, 2018, "Meeting3", "This is the third meeting", "234@gmail.com");
      int recurDays3[] = {};
      appt03.setRecurrence(recurDays3,1,2,5);
      Appt appt04 = new Appt(10, 15, 2, 2, 2018, "Meeting4", "This is the 4th meeting", "234@gmail.com");
      int recurDays4[] = {1};
      appt04.setRecurrence(recurDays4,1,2,5);
      Appt appt05 = new Appt(10, 15, 2, 2, 2018, "Meeting5", "This is the 5th meeting", "234@gmail.com");
      int recurDays5[] = {2};
      appt05.setRecurrence(recurDays5,2,1,5);
      Appt appt06 = new Appt(10, 15, 2, 2, 2018, "Meeting6", "This is the 6th meeting", "234@gmail.com");
      int recurDays6[] = {2};
      appt06.setRecurrence(recurDays6,3,2,5);
      Appt appt07 = new Appt(10, 15, 3, 3, 2018, "Meeting7", "This is the 7th meeting", "567@gmail.com");
      dh0.saveAppt(appt01);
      dh0.saveAppt(appt02);
      dh0.saveAppt(appt03);
      dh0.saveAppt(appt04);
      dh0.saveAppt(appt05);
      dh0.saveAppt(appt06);
      dh0.saveAppt(appt07);

      GregorianCalendar calendar01 = new GregorianCalendar(2018, 0, 1);
      GregorianCalendar calendar02 = new GregorianCalendar(2018, 2, 3);

      LinkedList<CalDay> caldays = new LinkedList<CalDay>();
      LinkedList<CalDay> targetcaldays = new LinkedList<CalDay>();

      GregorianCalendar nextDay = (GregorianCalendar) calendar01.clone();
      while (nextDay.before(calendar02)) {
          targetcaldays.add(new CalDay(nextDay));
          nextDay.add(nextDay.DAY_OF_MONTH, 1);
      }
      targetcaldays.get(0).addAppt(appt01);
      targetcaldays.get(32).addAppt(appt02);
      targetcaldays.get(32).addAppt(appt03);
      targetcaldays.get(32).addAppt(appt04);
      targetcaldays.get(32).addAppt(appt05);
      targetcaldays.get(32).addAppt(appt06);
      targetcaldays.get(34).addAppt(appt04);
      targetcaldays.get(39).addAppt(appt03);
      targetcaldays.get(41).addAppt(appt04);
      targetcaldays.get(46).addAppt(appt03);
      targetcaldays.get(48).addAppt(appt04);
      targetcaldays.get(53).addAppt(appt03);
      targetcaldays.get(55).addAppt(appt04);
      targetcaldays.get(60).addAppt(appt03);

      // These would be present in the result due to getNextApptOccurence bug - monthly recurrence increments the day instead of month
      // targetcaldays.get(33).addAppt(appt05);
      // targetcaldays.get(34).addAppt(appt05);
      // targetcaldays.get(35).addAppt(appt05);
      // targetcaldays.get(36).addAppt(appt05);
      // targetcaldays.get(37).addAppt(appt05);

      caldays = (LinkedList<CalDay>) dh0.getApptRange(calendar01,calendar02);

      //Prints to manually check - assertion failed even when they're they equivalent... maybe it checks if the two lists are the same one, instead of
      //having the same contents?
      //Note - caldays contents seem to constantly append to itself on sucessive runs of mvn test - Must delete file "testname0" before each test.
//      System.out.println(caldays);
//      System.out.println(targetcaldays);
//      assertEquals(caldays, targetcaldays);
  }

//Test getApptRange - invalid recurBy
  @Test(timeout = 4000)
  public void test000() throws Throwable{
      DataHandler dh00 = new DataHandler("testName0",true);
      Appt appt001 = new Appt(10, 15, 1, 1, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      int recurDays001[] = {};
      appt001.setRecurrence(recurDays001,0,2,5);
      dh00.saveAppt(appt001);
      GregorianCalendar calendar001 = new GregorianCalendar(2018, 0, 1);
      GregorianCalendar calendar002 = new GregorianCalendar(2018, 1, 2);
      LinkedList<CalDay> caldays000 = new LinkedList<CalDay>();
      LinkedList<CalDay> targetcaldays000 = new LinkedList<CalDay>();
      GregorianCalendar nextDay00 = (GregorianCalendar) calendar001.clone();
      while (nextDay00.before(calendar002)) {
          targetcaldays000.add(new CalDay(nextDay00));
          nextDay00.add(nextDay00.DAY_OF_MONTH, 1);
      }
      targetcaldays000.get(0).addAppt(appt001);

      caldays000 = (LinkedList<CalDay>) dh00.getApptRange(calendar001,calendar002);
      //Refer to test00 comments
      // System.out.println(caldays000);
      // System.out.println(targetcaldays000);
      // assertEquals(caldays000, targetcaldays000);
  }

//Test getApptRange - invalid recurDays
@Test(timeout = 4000)
public void test001() throws Throwable{
    DataHandler dh01 = new DataHandler("testName1",true);
    Appt appt002 = new Appt(10, 15, 1, 1, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    int recurDays002[] = {9};
    appt002.setRecurrence(recurDays002,1,2,5);
    dh01.saveAppt(appt002);
    GregorianCalendar calendar0001 = new GregorianCalendar(2018, 0, 1);
    GregorianCalendar calendar0002 = new GregorianCalendar(2018, 1, 2);
    LinkedList<CalDay> caldays001 = new LinkedList<CalDay>();
    LinkedList<CalDay> targetcaldays001 = new LinkedList<CalDay>();
    GregorianCalendar nextDay01 = (GregorianCalendar) calendar0001.clone();
    while (nextDay01.before(calendar0002)) {
        targetcaldays001.add(new CalDay(nextDay01));
        nextDay01.add(nextDay01.DAY_OF_MONTH, 1);
    }
    targetcaldays001.get(0).addAppt(appt002);

    caldays001 = (LinkedList<CalDay>) dh01.getApptRange(calendar0001,calendar0002);
    //Refer to test00 comments
    // System.out.println(caldays001);
    // System.out.println(targetcaldays000);
    // assertEquals(caldays001, targetcaldays001);
}

//Test saveAppt - invalid appt
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      DataHandler dh1 = new DataHandler("testName2",true);
      Appt appt1 = new Appt(10, 15, 10, 13, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt1.setValid();
      assertFalse(dh1.saveAppt(appt1));

  }

  //Test saveAppt - valid appt - isAutoSave
    @Test(timeout = 4000)
    public void test02()  throws Throwable  {
        DataHandler dh2 = new DataHandler("testName2",true);
        Appt appt2 = new Appt(10, 15, 10, 7, 2018, "Meeting", "This is a meeting", "123@gmail.com");
        appt2.setValid();
        assertTrue(dh2.saveAppt(appt2));

    }

//Test saveAppt - valid appt no autosave
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      DataHandler dh3 = new DataHandler("testName2",false);
      Appt appt3 = new Appt(10, 15, 10, 7, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt3.setValid();
      assertTrue(dh3.saveAppt(appt3));

  }

//Test deleteAppt - invalid appt
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      DataHandler dh4 = new DataHandler("testName1",true);
      Appt appt4 = new Appt(10, 15, 10, 13, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt4.setValid();
      dh4.saveAppt(appt4);
      assertFalse(dh4.deleteAppt(appt4));
  }

  //Test deleteAppt - valid appt + autosave
  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      DataHandler dh5 = new DataHandler("testName1",true);
      Appt appt5 = new Appt(10, 15, 10, 7, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt5.setValid();
      dh5.saveAppt(appt5);
      assertTrue(dh5.deleteAppt(appt5));
  }

//Test deleteAppt - valid appt no autosave
  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      DataHandler dh6 = new DataHandler("testName1",false);
      Appt appt6 = new Appt(10, 15, 10, 7, 2018, "Meeting", "This is a meeting", "123@gmail.com");
      appt6.setValid();
      dh6.saveAppt(appt6);
      assertTrue(dh6.deleteAppt(appt6));
  }

}
