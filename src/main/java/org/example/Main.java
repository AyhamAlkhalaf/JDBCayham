package org.example;
import org.example.model.Employee;
import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeDaoImpl;

import java.util.Date;//


public class Main {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        Employee employee = Employee.builder()
                .name("Asmaa")
                .gender(false)
                .birthDate(new Date())
                .salary(2324d)
                .build();


  //      employeeDao.save(employee);
        employeeDao.findAll().forEach(System.out::println);
//        Employee emp = employeeDao.findById(2);
//        System.out.println(emp);

        employeeDao.deleteById(1);

        System.out.println("Done!");
    }
}