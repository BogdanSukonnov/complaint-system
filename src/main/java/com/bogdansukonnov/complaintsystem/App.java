package com.bogdansukonnov.complaintsystem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bogdansukonnov.complaintsystem.dao.ComplaintDao;
import com.bogdansukonnov.complaintsystem.entities.Complaint;

@Controller
public class App {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@RequestMapping("/fileComplaint")
	public String fileComplaint() {
		Complaint complaint = new Complaint(1 , "Hello", "bob", "bob@bob.com");
		ComplaintDao complaintDao = new ComplaintDao(sessionFactory);
		complaintDao.insertComplaint(complaint);
		for (Complaint currentComplaint : complaintDao.getAllComplaints()) {
			System.out.println(currentComplaint.getMessage());
		}
		return "fileComplaint";
	}
	
	@RequestMapping("/submitComplaint")
	public String submitComplaint() {
		return "submitComplaint";
	}
	
	@RequestMapping(name = "/showComplaints", method = RequestMethod.GET)
	public String showComplaintsGet() {
		return "showEnterPassword";
	}
	
	@RequestMapping(name = "/showComplaints", method = RequestMethod.POST)
	public String showComplaintsPost() {
		return "showComplaints";
	}

}
