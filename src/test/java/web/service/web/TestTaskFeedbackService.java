package web.service;
import org.junit.*;
import java.time.LocalDate;

public class TestTaskFeedbackService {
	// if the assessment was submitted before the due date, the student will get feedback
	@Test 
	public void testSubmittedBeforeDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-07-10"); 
		LocalDate dateSubmitted = LocalDate.parse("2026-03-01");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will be given on your assessment.");
	}
	// first test passed and finished, can reuse structure etc
	
	// if the assessmeent was submitted after the due date, the student will not receive feedback
	@Test 
	public void testSubmittedAfterDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-03-01"); 
		LocalDate dateSubmitted = LocalDate.parse("2026-03-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will not be given on your assessment.");
	}
	
	// if the assessment was submitted on the due date, the student will receive feedback
	@Test 
	public void testSubmittedEqualsDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-06-01"); 
		LocalDate dateSubmitted = LocalDate.parse("2026-06-01");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will be given on your assessment.");
	}
	
	// if the assessment has not been submitted (no date) and the due date has passed, the student will not receive feedback
	@Test 
	public void testNotSubmittedPassedDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2010-06-01"); 
		LocalDate dateSubmitted = null;
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will not be given on your assessment.");
	}
	
	// if the assessment has not been submitted (no date) and the due date has not passed, 
	// the student will receive if they subit by the date
	@Test 
	public void testNotSubmittedFutureDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-06-01"); 
		LocalDate dateSubmitted = null;
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment if submitted by the due date.");
	}
	
	// if the assessment has not been submitted (no date) and the due date is today, 
	// the student will receive if they submit today
	@Test 
	public void testNotSubmittedDueTodaySuccess() {
		LocalDate dueDate = LocalDate.now(); 
		LocalDate dateSubmitted = null;
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment if submitted today.");
	}
	
	// if due date is empty then it is an error and student directed to contact tutor
	@Test
	public void testDueDateEmptySuccess() {
		LocalDate dueDate = null;
		LocalDate dateSubmitted = LocalDate.parse("2026-03-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"No due date found, please contact your tutor for assistance.");
	}
	
	// scrapping this test, the parameter expects localdate data type so any invalid inputs are blocked by default
	// throw error message if submitted date is invalid
	/*
	 * @Test public void testSubmittedDateInvalidFail() { LocalDate dueDate =
	 * LocalDate.parse("2026-06-10"); //invalid date entered directly as param
	 * Assert.assertTrue(TaskFeedbackService.Verification("9999-13-40", dueDate) ==
	 * "Invalid date. Try again or contact your tutor."); }
	 */
	
	// narrow down scope of acceptable dates - dates outside of the trimester should not be accepted
	// trimester 1 2026 O week start:23-02-2026
	// trimester 1 2026 end (supplementary exam period plus one week in case
	// of special circumstances: 07-08-2026
	@Test
	public void testDateSubmittedBeforeStartSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-03-30");
		LocalDate dateSubmitted = LocalDate.parse("2025-02-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	
	// error to be recived when submission date is after trimester end
	@Test
	public void testDateSubmittedAfterEndSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-03-30");
		LocalDate dateSubmitted = LocalDate.parse("2026-10-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	
	// test to check correct result if due date is  before semester start
	@Test
	public void testDueDateBeforeStartSuccess() {
		LocalDate dueDate = LocalDate.parse("2025-03-30");
		LocalDate dateSubmitted = LocalDate.parse("2026-03-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	
	// test to check correct result if due date is after semester end
	@Test
	public void testDueDateAfterEndSuccess() {
		LocalDate dueDate = LocalDate.parse("2027-03-30");
		LocalDate dateSubmitted = LocalDate.parse("2026-03-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	// test to check correct result if both due date and submit date are out of range
	@Test
	public void testDueDateAndSubmitDateInvalidSuccess() {
		LocalDate dueDate = LocalDate.parse("2027-03-30");
		LocalDate dateSubmitted = LocalDate.parse("2025-03-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	
	// check if both inputs null correct result
	@Test
	public void testNullSubmitAndNullDueSuccess() {
		LocalDate dueDate = null;
		LocalDate dateSubmitted = null;
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"No due date found, please contact your tutor for assistance.");
	}
	
	//	test boundaries - (min--, min, min++, middle, max--, max, max++)
	// min: 2026-02-23
	// max: 	2026-08-07
	@Test
	public void testBelowMinDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-02-22");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	@Test
	public void testMinDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-02-23");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will not be given on your assessment.");
	}
	@Test
	public void testAboveMinDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-02-24");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will not be given on your assessment.");
	}
	@Test
	public void testMiddleDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment.");
	}
	@Test
	public void testBelowMaxDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-08-06");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment.");
	}
	@Test
	public void testMaxDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-08-07");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment.");
	}
	@Test
	public void testAboveMaxDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2026-08-08");
		LocalDate dateSubmitted = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	@Test
	public void testBelowMinSubmitSuccess() {
		LocalDate dateSubmitted = LocalDate.parse("2026-02-22");
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}
	@Test
	public void testMinSubmitSuccess() {
		LocalDate dateSubmitted = LocalDate.parse("2026-02-23");
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment.");
	}
	@Test
	public void testAboveMinSubmitSuccess() {
		LocalDate dateSubmitted = LocalDate.parse("2026-02-24");
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will be given on your assessment.");
	}
	public void testBelowMaxSubmitSuccess() {
		LocalDate dateSubmitted = LocalDate.parse("2026-08-06");
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will not be given on your assessment.");
	}
	@Test
	public void testMaxSubmitSuccess() {
		LocalDate dateSubmitted = LocalDate.parse("2026-08-07");
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Feedback will not be given on your assessment.");
	}	
	@Test
	public void testAboveMaxSubmitSuccess() {
		LocalDate dateSubmitted = LocalDate.parse("2026-08-08");
		LocalDate dueDate = LocalDate.parse("2026-05-07");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == 
				"Entered dates do not fall within the trimester. "
				+ "Try again or speak with your tutor if special circumstances apply.");
	}

}