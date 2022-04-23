package br.com.maralto.webappbiblioteca.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ConvertDate {

	
	 public static Date formataData(String dataForm) {

		Date data = null;

		try {
			
			data = new SimpleDateFormat("yyyy-MM-dd").parse(dataForm);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static Date stringToDateJava(String data) {

		try {

			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String DateToString(Date data) {
		if(data != null) {
			Format formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(data);
		}
		
		return "";
		
	}
	
	public static String DateToStringTimeStamp(Date data) {
		
		if(data != null) {
			Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatter.format(data);
		}
		

		return "";
	}
	
	public static Date addMesAData(Date dateToConvert) {
		
		LocalDate localDate = dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		localDate.plusMonths(1);
		
		return Date.from(localDate.plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
	}
	
	
}
