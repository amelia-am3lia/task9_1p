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
	public void testSubmittedAfterDueFail() {
		LocalDate dueDate = LocalDate.parse("2010-01-01"); 
		LocalDate dateSubmitted = LocalDate.parse("2010-01-10");
		Assert.assertTrue(TaskFeedbackService.Verification(dateSubmitted, dueDate) == "Feedback will not be given on your assessment.");
	}
}