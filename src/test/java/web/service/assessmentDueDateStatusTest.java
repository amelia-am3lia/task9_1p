package web.service;
import org.junit.*;
import java LocalDateTime;

public class assessmentDueDateStatusTest {
	@Test 
	public void feedbackExpectedWhenAssessmentOnTime(Assessment assessment) {
		LocalDate dueDate = assessment.DateDue; 
		Bool pastDue;
		if(dueDate > LocalDate.now()) {
			pastDue = true;
		}
	}
}