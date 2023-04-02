package com.masai.team6.Controller;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.masai.team6.Entities.test;
import com.masai.team6.Exception.ApiException;
import com.masai.team6.Repository.testRepo;
import com.masai.team6.Services.Impl.testServices;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private testServices testservices;

	@Autowired
	private testRepo tRepo;

//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFileViaCSV(@RequestParam("file") MultipartFile file) throws Exception {
		InputStream inputStream = file.getInputStream();

		// CSV settings and Parsing:
		CsvParserSettings settings = new CsvParserSettings();
		settings.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(settings);

		List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
		List<test> testList = new ArrayList<>();
		parseAllRecords.forEach((record) -> {
			test tes = new test();
			tes.setTestName(record.getString("TestName"));
			String date = record.getString("TestDate");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date, formatter);
			tes.setDate(localDate);
			tes.setAttendence(record.getString("attendence"));
			tes.setUserEmail(record.getString("userEmail"));
			tes.setMaxMarks(record.getInt("maxMarks"));
			tes.setGetMarks(record.getInt("marksObtained"));

			tRepo.save(tes);

		});

		return ResponseEntity.ok("created ");
	}

	@GetMapping("/{useremail}")
	public ResponseEntity<List<test>> getListOffTestBYUserEmail(@PathVariable String useremail) {
		List<test> alltest = tRepo.getListOfTest(useremail);
		return new ResponseEntity<List<test>>(alltest, HttpStatus.OK);

	}
	
	
	
	

}
