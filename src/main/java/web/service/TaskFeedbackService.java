package web.service;
import java.time.LocalDate;

public class TaskFeedbackService{	
	public static String Verification(LocalDate DateSubmitted, LocalDate DueDate){
		if(DateSubmitted.isBefore(DueDate) || DateSubmitted.isEqual(DueDate)) {
			return "Feedback will be given on your assessment.";
		}
		else if (DateSubmitted.isAfter(DueDate) || 
				(DateSubmitted == null && LocalDate.now().isAfter(DueDate))) {
			return "Feedback will not be given on your assessment.";
		}
		else return "Please contact your tutor.";
	}
}  