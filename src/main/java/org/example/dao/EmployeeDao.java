package org.example.dao;

import org.example.model.Employee;

import java.util.List;


// كلاس الانتر فيس هو الكلاس الاساي الذي يحتوي على الميثود التي نحتاجها لكي نتبادل المعلومات مع ال داتا بيس
public interface EmployeeDao {

   List<Employee> findAll();
   // ميثود ل ايجاد كل عناصر الموظف
   Employee findById(int id);
   // لايجاد الموظف بنسبة لل اي دي
   void  save(Employee  employee);
   //بحسب ال id لاضافة موظف جديد او تحديث قديم
   void  deleteById(int id);
   // لحذف موظف تبعا لل اي دي

}
