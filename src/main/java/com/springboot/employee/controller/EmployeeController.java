package com.springboot.employee.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.Specification.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.employee.service.EmployeeService;
import com.springboot.employee.service.PdfGenerator;
import com.springboot.employee.specification.EmployeeSpecification;

@RestController
@RequestMapping("/emp")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	private PdfGenerator pdfGenerator;

	@GetMapping("/")
	public List<Employee> get() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/{id}")
	public Employee getOneEmployee(@PathVariable("id") Integer id) {
		return employeeService.getEmployee(id);
	}

	@PostMapping("/add")
	public Employee addEmp(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmp(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
	}

	@PutMapping("/update")
	public Employee updateEmp(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	// @Transactional
	@DeleteMapping("/deleteByPosition/{position}")
	public ResponseEntity<String> deleteEmployeesBy(@PathVariable("position") String position) {
		employeeService.deleteEmployeesByPosition(position);
		return ResponseEntity.ok("Employees with position " + position + " deleted successfully.");

	}

	@GetMapping("/get/{firstname}")
	public List<Employee> getEmpByFirstName(@PathVariable("firstname") String firstname) {
		List<Employee> employee = employeeService.getEmployeeByFirstName(firstname);
		return employee.stream().map(e -> dozerBeanMapper.map(e, Employee.class)).collect(Collectors.toList());
	}

	@GetMapping("/page/{PageNo}/{PageSize}")
	public List<Employee> findAllByPage(@PathVariable("PageNo") int PageNo, @PathVariable("PageSize") int PageSize) {
		// model.addAttribute(model);
		return employeeService.FindAllByPage(PageNo, PageSize);
	}

	@GetMapping("/getting/{firstname}")
	public List<Employee> findByFirstname(@PathVariable("firstname") String firstname) {
		// Specification<Employee> specification =
		// Specification.where(EmployeeSpecification.hasFirstname(firstname));
		List<Employee> list = employeeRepository.findAll(where(EmployeeSpecification.hasFirstname(firstname)));
		// System.out.println("this is byfirstname");
		System.out.println("hasFirstname---------------------------------------------------------------");
		return list.stream().map(e -> dozerBeanMapper.map(e, Employee.class)).collect(Collectors.toList());
	}

	@GetMapping("/getPosition/{position}")
	public List<Employee> findByPosition(@PathVariable("position") String position) {
		List<Employee> list = employeeRepository.findAll(where(EmployeeSpecification.hasPosition(position)));

		return list.stream().map(e -> dozerBeanMapper.map(e, Employee.class)).collect(Collectors.toList());
	}

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> displayPdf() {
		byte[] pdf = pdfGenerator.createPDF();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
//	 return ResponseEntity
//             .ok()
//             .headers(headers)
//             .contentType(MediaType.APPLICATION_PDF)
//             .body(new InputStreamResource(b));
		String fileName = "example.pdf";
		headers.setContentDispositionFormData(fileName, fileName);
		headers.setContentType(MediaType.APPLICATION_PDF);
		return ResponseEntity.ok().headers(headers).body(pdf);

	}

	@PatchMapping("/change/{id}")
	public Employee patch(@PathVariable("id") String id, @RequestBody Map<String, Object> employee) {
		return employeeService.patch(id, employee);

	}

}
