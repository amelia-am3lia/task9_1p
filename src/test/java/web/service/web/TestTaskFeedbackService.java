package web.service;
import org.junit.*;
import java.time.LocalDate;

public class TestTaskFeedbackService {
	// if the assessment was submitted before the due date, the student will get feedback
	@Test 
	public void testSubmittedBeforeDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2010-10-10"); 
		LocalDate dateSubmitted = LocalDate.parse("2010-01-01");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will be given on your assessment.");
	}
	// first test passed and finished, can reuse structure etc
	
	// if the assessmeent was submitted after the due date, the student will not receive feedback
	@Test 
	public void testSubmittedAfterDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2010-01-01"); 
		LocalDate dateSubmitted = LocalDate.parse("2010-01-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will not be given on your assessment.");
	}
	
	// if the assessment was submitted on the due date, the student will receive feedback
	@Test 
	public void testSubmittedEqualsDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2010-06-01"); 
		LocalDate dateSubmitted = LocalDate.parse("2010-06-01");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will be given on your assessment.");
	}
	
	// if the assessment has not been submitted (no date) and the due date has passed, the student will not receive feedback
	@Test 
	public void testNotSubmittedPassedDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2010-06-01"); 
		LocalDate dateSubmitted = null;
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will not be given on your assessment.");
	}
}