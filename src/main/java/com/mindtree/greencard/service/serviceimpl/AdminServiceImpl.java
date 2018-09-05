package com.mindtree.greencard.service.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.greencard.jprepository.adminrepository.GreenCardHistoryRepository;
import com.mindtree.greencard.jprepository.adminrepository.InProgressGreenCardRepository;
import com.mindtree.greencard.jprepository.greencardrepository.NewGreenCardRepository;
import com.mindtree.greencard.model.GreenCardHistory;
import com.mindtree.greencard.model.InProgressGreenCard;
import com.mindtree.greencard.model.NewGreenCard;
import com.mindtree.greencard.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	NewGreenCardRepository newgreencard;

	@Autowired
	InProgressGreenCardRepository inprogresscard;

	@Autowired
	GreenCardHistoryRepository history;

	@Override
	public List<NewGreenCard> newComplaints() {
	

		return this.newgreencard.findAll();
	}

	@Override
	public Optional<NewGreenCard> getCard(int gid) {

		return this.newgreencard.findById(gid);
	}

	@Override
	public String assigncard(InProgressGreenCard card) {
		
		this.inprogresscard.save(card);
		return "Assigned";
	}

	@Override
	public List<InProgressGreenCard> viewprogress() {
	
		return this.inprogresscard.findAll();
	}

	@Override
	public List<GreenCardHistory> getAllFromHistory() {
		
		return this.history.findAll();

	}

	@Override
	public void generateXl() {
		List<GreenCardHistory> list = this.history.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("GreenCradObservations");
		XSSFRow row = spreadsheet.createRow(1);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("GreenCardId");
		cell = row.createCell(1);
		cell.setCellValue("UserId");
		cell = row.createCell(2);
		cell.setCellValue("OpenedDateWithTime");
		cell = row.createCell(3);
		cell.setCellValue("ClosedDateWithTime");
		cell = row.createCell(4);
		cell.setCellValue("AssignedPersonId");
		cell = row.createCell(5);
		cell.setCellValue("status");
		cell = row.createCell(6);
		cell.setCellValue("correctiveaction");
		cell = row.createCell(7);
		cell.setCellValue("root cause");
		cell = row.createCell(8);
		cell.setCellValue("what happened");
		cell = row.createCell(9);
		cell.setCellValue("landmark");
		cell = row.createCell(10);
		cell.setCellValue("category");
		int i = 2;
		for (GreenCardHistory e : list) {

			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(e.getgId());
			cell = row.createCell(1);
			cell.setCellValue(e.getUserId());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String openedtime = e.getSubmittedDateTime().format(formatter);
			cell = row.createCell(2);
			cell.setCellValue(openedtime);
			if (e.getClosedDateTime() != null) {
				String closedtime = e.getClosedDateTime().format(formatter);
				cell = row.createCell(3);
				cell.setCellValue(closedtime);
			} else {
				cell = row.createCell(3);
				cell.setCellValue("-");
			}
			cell = row.createCell(4);
			cell.setCellValue(e.getAssignedPersonId());
			cell = row.createCell(5);
			cell.setCellValue(e.getStatus());
			cell = row.createCell(6);
			cell.setCellValue(e.getCorrectiveAction());
			cell = row.createCell(7);
			cell.setCellValue(e.getRootCause());
			cell = row.createCell(8);
			cell.setCellValue(e.getWhatHappened());
			cell = row.createCell(9);
			cell.setCellValue(e.getLandmark());
			cell = row.createCell(10);
			cell.setCellValue(e.getCategory());
			i++;
		}

		try {
		
			FileOutputStream out = new FileOutputStream(
					new File("D://greencardhistoryexcelsheet.xlsx"));
			workbook.write(out);
			out.close();

		} catch (Exception e) {
		}
		finally{
		}

	}

}
