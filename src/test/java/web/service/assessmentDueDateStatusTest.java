package web.service;
import org.junit.*;
import java.time.LocalDate;

public class assessmentDueDateStatusTest {
	@Test 
	public void feedbackExpectedWhenAssessmentOnTime(Assessment assessment) {
		LocalDate dueDate = assessment.DateDue(); 
		boolean pastDue;
		if(dueDate.isAfter(LocalDate.now())) {
			pastDue = true;
		}
	}
}