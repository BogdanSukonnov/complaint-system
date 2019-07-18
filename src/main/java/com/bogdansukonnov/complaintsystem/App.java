package com.bogdansukonnov.complaintsystem;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bogdansukonnov.complaintsystem.dao.ComplaintDao;
import com.bogdansukonnov.complaintsystem.entities.Complaint;

@Controller
@PropertySource({"classpath:admin-properties.properties"})
public class App {
	
	@Autowired
	private Environment env;
	
	@Autowired
	SessionFactory sessionFactory;
		
	
	@RequestMapping("/fileComplaint")
	public String fileComplaint() {
		return "fileComplaint";
	}
	
	@RequestMapping("/submitComplaint")
	public String submitComplaint(@RequestParam String complaint
			, @RequestParam String name, @RequestParam String email) {
		ComplaintDao dao = new ComplaintDao(sessionFactory);
		Complaint complaintEntity = new Complaint(complaint, name, email);
		dao.insertComplaint(complaintEntity);
		System.out.println("Inserted complaint!");
		return "submitComplaint";
	}
	
	@RequestMapping(name = "/showComplaints", method = RequestMethod.GET)
	public String showComplaintsGet() {
		return "showEnterPassword";
	}
	
	@RequestMapping(name = "/showComplaints", method = RequestMethod.POST)
	public ModelAndView showComplaintsPost(@RequestParam(name = "pass") String pass, ModelAndView modelAndView) {
		if (pass.equals(env.getProperty("admin.password"))) {
			ComplaintDao dao = new ComplaintDao(sessionFactory);
			List<Complaint> complaints = dao.getAllComplaints();
			modelAndView.addObject(complaints);
			modelAndView.setViewName("showComplaints");			
		} else {
			modelAndView.setViewName("showEnterPassword");
		}
		return modelAndView;
	}

}
