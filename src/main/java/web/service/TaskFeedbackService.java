package web.service;
import java.time.LocalDate;

public class TaskFeedbackService{	
	public static String Verification(LocalDate DateSubmitted, LocalDate DueDate){
		if (DateSubmitted == null) {
			if (LocalDate.now().isAfter(DueDate)) {
			return "Feedback will not be given on your assessment.";
			}
			if (LocalDate.now().isBefore(DueDate)) {
				return "Feedback will be given on your assessment if submitted by the due date.";
			}
		}
		
		if(DateSubmitted.isBefore(DueDate) || DateSubmitted.isEqual(DueDate)) {
			return "Feedback will be given on your assessment.";
		}
		if (DateSubmitted.isAfter(DueDate)) {
			return "Feedback will not be given on your assessment.";
		}
		else return "Please contact your tutor.";
	}
}  