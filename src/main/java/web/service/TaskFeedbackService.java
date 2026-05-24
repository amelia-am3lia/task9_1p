package web.service;
import java.time.LocalDate;

public class TaskFeedbackService{	
	public static String Verification(LocalDate DateSubmitted, LocalDate DueDate){
		LocalDate TrimesterStart = LocalDate.parse("2026-02-23");
		LocalDate TrimesterEnd = LocalDate.parse("2026-08-07");
		if(DueDate == null) {
			return "No due date found, please contact your tutor for assistance.";
		}
		if (DateSubmitted == null) {
			if (LocalDate.now().isAfter(DueDate)) {
			return "Feedback will not be given on your assessment.";
			}
			if (LocalDate.now().isBefore(DueDate)) {
				return "Feedback will be given on your assessment if submitted by the due date.";
			}
			if(LocalDate.now().isEqual(DueDate)) {
				return "Feedback will be given on your assessment if submitted today.";
			}
		}
		if(DateSubmitted.isBefore(TrimesterStart) || DateSubmitted.isAfter(TrimesterEnd)
				|| DueDate.isBefore(TrimesterStart) || DueDate.isAfter(TrimesterEnd)) {
			return "Entered dates do not fall within the trimester. "
					+ "Try again or speak with your tutor if special circumstances apply.";
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