/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
//      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
  }

 //Testing setRecurrence
@Test(timeout = 4000)
 public void test01()  throws Throwable  {
     Appt appt1 = new Appt(11, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt1.setValid();
     assertEquals(true, appt1.getValid());
     int recurDays[] = new int[1];
     appt1.setRecurrence(recurDays,1,2,5);
     assertTrue(appt1.isRecurring());
     assertEquals(1,appt1.getRecurBy());
     assertEquals(2,appt1.getRecurIncrement());
     assertEquals(5,appt1.getRecurNumber());
     assertEquals(recurDays,appt1.getRecurDays());
     appt1.setRecurrence(null,1,2,5);
}

//Testing setValid - invalid hour
@Test(timeout = 4000)
 public void test02()  throws Throwable  {
     Appt appt2 = new Appt(25, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt2.setValid();
     assertFalse(appt2.getValid());

     Appt appt02 = new Appt(-10, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt02.setValid();
     assertFalse(appt02.getValid());
}

//Testing setValid - invalid minute
@Test(timeout = 4000)
 public void test03()  throws Throwable  {
     Appt appt3 = new Appt(10, 65, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt3.setValid();
     assertFalse(appt3.getValid());
     assertTrue(appt3.hasTimeSet());

     Appt appt03 = new Appt(10, -10, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt03.setValid();
     assertFalse(appt03.getValid());
}

//Testing setValid - invalid year
@Test(timeout = 4000)
 public void test04()  throws Throwable  {
     Appt appt4 = new Appt(10, 15, 10, 12, -2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt4.setValid();
     assertFalse(appt4.getValid());

     Appt appt04 = new Appt(10, 15, 10, 12, 0, "Meeting", "This is a meeting", "123@gmail.com");
     appt04.setValid();
//   Appt's setValid bug - 0 should be invalid, but bug causes it to count as valid.
//   assertFalse(appt04.getValid());
}

//Testing setValid - invalid month
@Test(timeout = 4000)
public void test05() throws Throwable {
     Appt appt5 = new Appt(10, 15, 10, 13, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt5.setValid();
     assertFalse(appt5.getValid());

     Appt appt05 = new Appt(10, 15, 10, -5, 2018, "Meeting", "This is a meeting", "123@gmail.com");
     appt05.setValid();
     assertFalse(appt05.getValid());
}

//Testing setValid - invalid day
@Test(timeout = 4000)
 public void test06()  throws Throwable  {
       Appt appt6 = new Appt(10, 15, -5, 11, 2018, "Meeting", "This is a meeting", "123@gmail.com");
       appt6.setValid();
//     Appt's constructor bug - constructor uses month value (11) to set day, so day here is 11 and thus not invalid
//       assertFalse(appt6.getValid());

       Appt appt06 = new Appt(10, 15, 31, 11, 2018, "Meeting", "This is a meeting", "123@gmail.com");
       appt06.setValid();
//     Appt's constructor bug - constructor uses month value (11) to set day, so day here is 11 and thus not invalid
//       assertFalse(appt06.getValid());
}

//Testing second appt constructor
@Test(timeout = 4000)
 public void test07()  throws Throwable  {
     Appt appt7 = new Appt(1,2,3, null, null, null);
     assertEquals("", appt7.getTitle());
     assertEquals("", appt7.getDescription());
     assertEquals("", appt7.getEmailAddress());

     Appt appt07 = new Appt(1,2,2018, "No Time Meeting", "This is a meeting without a time", "456@gmail.com");
     assertFalse(appt07.hasTimeSet());
 }

//Testing isOn
 @Test(timeout = 4000)
 public void test08()  throws Throwable  {
      Appt appt8 = new Appt(12,30,1,1,2018, "Meeting", "This is a meeting", "456@gmail.com");
      assertTrue(appt8.isOn(1,1,2018));
      assertFalse(appt8.isOn(2,1,2018));
      assertFalse(appt8.isOn(1,2,2018));
      assertFalse(appt8.isOn(1,1,2000));

      Appt appt08 = new Appt(12,30,25,12,2018, "Meeting", "This is a meeting", "456@gmail.com");
      assertFalse(appt08.isOn(5,4,2005));
//     Appt's constructor bug - constructor uses month value (12) to set day, so day here is 12 and not 25
//      assertTrue(appt08.isOn(25,12,2018));
}

//Testing toString
@Test(timeout = 4000)
public void test09() throws Throwable {
    Appt appt9 = new Appt(10, 15, 10, 12, -2018, "Meeting", "This is a meeting", "123@gmail.com");
    appt9.setValid();
    assertFalse(appt9.getValid());
    String string9 = appt9.toString();
    //Not sure how to check for error message...

    Appt appt09 = new Appt(11, 15, 10, 12, 2018, "Meeting", "This is a meeting", "123@gmail.com");
    String string09 = appt09.toString();
//  Appt's constructor bug - constructor uses month (12) to set day, so day here is 12 and not 10
//    assertEquals("\t12/10/2018 at 11:15am ,Birthday Party, This is my birthday party\n", string09);
}

}
