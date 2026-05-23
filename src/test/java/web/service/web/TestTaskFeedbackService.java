package web.service;
import org.junit.*;
import java.time.LocalDate;

public class TestTaskFeedbackService {
	@Test 
	public void testSubmittedBeforeDueSuccess() {
		LocalDate dueDate; 
		LocalDate dateSubmitted;
		String Result = TaskFeedbackService.Verification(dateSubmitted, dueDate);
	}
}