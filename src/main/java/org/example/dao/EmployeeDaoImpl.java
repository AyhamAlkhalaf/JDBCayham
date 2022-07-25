package org.example.dao;

import org.example.model.Employee;
import org.example.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

// الاوفر رايد للكلاس الاصلي و وضع وظائف الميثود الاساسية
// هذا لكلاس يقوم بتعامل مع قاعدة البيانات خاصة بل موظف فقط
//للتحككم في الجدول الخاص في الموظف في قاعدة البيانات من هنا
//اذا عنا جدول اخر  خاص بمنتج مثلا يجب عمل نفس هذا الكلاسات مودل و انترفيس و امبلمنت

public class EmployeeDaoImpl implements EmployeeDao {


    @Override
    public List<Employee> findAll() {
        Connection con = DBConnection.getConnection();
        if(con == null) {
            return null;
        }

        List<Employee> employees = new LinkedList<>();

        //جلب كل الموظفين في داتا بيس
        String query = "SELECT * FROM employee;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .gender(resultSet.getBoolean("gender"))
                        .birthDate(resultSet.getDate("birth_date"))
                        .salary(resultSet.getDouble("salary"))
                        .build();

                employees.add(employee);
            }

        } catch(SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Connection con = DBConnection.getConnection();
        if(con == null) {
            return null;
        }

        String query = "SELECT * FROM employee WHERE id=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .gender(resultSet.getBoolean("gender"))
                        .birthDate(resultSet.getDate("birth_date"))
                        .salary(resultSet.getDouble("salary"))
                        .build();
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void save(Employee employee) {

        Connection  con = DBConnection.getConnection(); //منعمل اتصال بقاعدة البيانات
        if(con == null){
            System.out.println("cant be connected");
        }
        // اذا الايدي اكبر من صفر معناتا موجود معلومات فا رح نعدلها واذا صفر رح نكريت جديد
        if (employee.getId()> 0 ){// updateتحديث للبيانات الموجودة


            String query = "UPDATE employee SET name=? , gender= ? , birth_date =? , salary = ?  WHERE id=? ;";
            // لكي يقوم باغلاق الاتصال عند الانتهاء من الاضافة الى الجدول
            try(PreparedStatement preparedStatement = con.prepareStatement(query)){
                //يعني قم بتعويض الاستفهام الاول ب هذه القيمة من نوع سترينغ
                preparedStatement.setString(1,employee.getName());
                // قم بتعويض الاستفهام الثاني بيقمة الثانية من نوع بوليين
                preparedStatement.setBoolean(2,employee.isGender());
                // قم بتعويض ال استفهام الثالث ب قيمة الثالثة عن طريق ميثود صنعناها لتحويل التاريخ الى صيغة sql
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate()));
                //ثم بتعويض القيمة الثالثة من صيغة دابل
                preparedStatement.setDouble(4,employee.getSalary());
                preparedStatement.setDouble(5,employee.getId());


                //يقوم بانشاء بيانات للجدول امبلوي
                preparedStatement.executeUpdate();


            }catch (SQLException se){
                se.printStackTrace();

            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    // قطع الاتصال
                    con.close();
                }catch (SQLException throwables){
                    throwables.printStackTrace();

                }
            }




        }else { // create اضافة بيانات جديدة
            // يعني رح نضيف بيانات الى ال امبلوي
            String query = "INSERT INTO employee  (name , gender , birth_date, salary ) VALUES ( ? , ? , ? , ? );";
            // لكي يقوم باغلاق الاتصال عند الانتهاء من الاضافة الى الجدول
            try(PreparedStatement preparedStatement = con.prepareStatement(query)){
                //يعني قم بتعويض الاستفهام الاول ب هذه القيمة من نوع سترينغ
                preparedStatement.setString(1,employee.getName());
                // قم بتعويض الاستفهام الثاني بيقمة الثانية من نوع بوليين
                preparedStatement.setBoolean(2,employee.isGender());
                // قم بتعويض ال استفهام الثالث ب قيمة الثالثة عن طريق ميثود صنعناها لتحويل التاريخ الى صيغة sql
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate()));
                //ثم بتعويض القيمة الثالثة من صيغة دابل
                preparedStatement.setDouble(4,employee.getSalary());


                //يقوم بانشاء بيانات للجدول امبلوي
                preparedStatement.executeUpdate();


            }catch (SQLException se){
                se.printStackTrace();

            }catch (Exception e){
              e.printStackTrace();
            } finally {
                try {
                    // قطع الاتصال 
                    con.close();
                }catch (SQLException throwables){
                    throwables.printStackTrace();

                }
            }



        }

    }

    @Override
    public void deleteById(int id) {
        Connection con = DBConnection.getConnection();
        if(con == null) {
            return;
        }

        String query = "DELETE FROM employee WHERE id=?;";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
