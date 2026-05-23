package web.service;
import org.junit.*;
import java.time.LocalDate;

public class TestTaskFeedbackService {
	@Test 
	public void testSubmittedBeforeDueSuccess() {
		LocalDate dueDate = LocalDate.parse("2010-10-10"); 
		LocalDate dateSubmitted = LocalDate.parse("2010-01-01");
		String output = TaskFeedbackService.Verification(dateSubmitted, dueDate);
	}
}