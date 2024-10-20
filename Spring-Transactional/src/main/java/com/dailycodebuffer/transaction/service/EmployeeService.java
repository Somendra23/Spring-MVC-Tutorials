package com.dailycodebuffer.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dailycodebuffer.transaction.entity.Department;
import com.dailycodebuffer.transaction.entity.Employee;
import com.dailycodebuffer.transaction.repository.DepartmentRepository;
import com.dailycodebuffer.transaction.repository.EmployeeRepository;
import com.dailycodebuffer.transaction.vo.EmployeeRequestVO;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(propagation = Propagation.REQUIRED )
    public String saveEmployee(EmployeeRequestVO employeeRequestVO) {

        Department department  = new Department();
        department.setDepartmentName(employeeRequestVO.getDepartmentName());
        department .setDepartmentCode(employeeRequestVO.getDepartmentName());

        Long departmentId = departmentRepository.save(department)
                            .getDepartmentId();
        
        //if(true) throw new RuntimeException();

        Employee  employee = new Employee();

        employee.setEmpName(employeeRequestVO.getEmpName());
        employee.setEmail(employeeRequestVO.getEmail());
        employee.setDepartmentId(departmentId);

        employeeRepository.save(employee);

        return "Employee is saved with Employee ID :" + employee.getEmployeeId();
    }
}
