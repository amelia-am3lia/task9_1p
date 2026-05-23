package web.service;
import org.junit.*;
import java.time.LocalDate;

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