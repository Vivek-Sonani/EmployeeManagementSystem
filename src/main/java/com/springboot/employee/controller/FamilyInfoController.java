package com.springboot.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.FamilyInfo;
import com.springboot.employee.service.FamilyInfoService;

@RestController
@RequestMapping("/family")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FamilyInfoController {

  @Autowired
  private FamilyInfoService familyInfoService;

  @GetMapping("/id/{employeeId}")
  public List<FamilyInfo> getAll(@PathVariable("employeeId") Integer employeeId) {
    return familyInfoService.getByEmployeeId(employeeId);
  }

  @PostMapping("/add")
  public FamilyInfo addFamily(@RequestBody FamilyInfo familyInfo) {
    System.out.println("Controller starting...");
    return familyInfoService.addFamilyInfo(familyInfo);
  }

  @PutMapping("/update")
  public FamilyInfo updateFamily(@RequestBody FamilyInfo familyInfo) {
    return familyInfoService.updateFamilyInfo(familyInfo);
  }

  @GetMapping("/{id}")
  public FamilyInfo get(@PathVariable("id") Integer id) {
    return familyInfoService.getFamilyInfo(id);
  }

  @DeleteMapping("/remove/{id}")
  public void remove(@PathVariable("id") Integer id) {
    familyInfoService.removeFamilyInfo(id);
  }

}
