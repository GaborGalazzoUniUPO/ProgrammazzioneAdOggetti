import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class Test
{
	@org.junit.Test
	public void t(){
		String dateFormat = "MM/dd/uuuu";
		String dateString = "02/28/2015";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter
				.ofPattern(dateFormat, Locale.ITALIAN)
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate date = LocalDate.parse(dateString, dateTimeFormatter);
			System.out.println(date);
		} catch (DateTimeParseException e) {
			// Throw invalid date message
			System.out.println("Exception was thrown");
		}
	}
}
