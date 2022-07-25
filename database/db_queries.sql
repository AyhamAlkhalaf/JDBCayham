DROP DATABASE IF EXISTS jdbcayham;
//هنا نقوم بحذف الداتا بيس اذا كانت موجودة ب نفس الاسم

CREATE DATABASE jdbcayham;

// انشاء داا جديدة

USE jdbcayham;
// استخدامها


CREATE TABLE employee (
    id INT(15) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    gender BOOLEAN,
    birth_date DATE,
    salary REAL
);

// انشاء جدول به العناصر
// اي دي نوعه رقم وعدده 15 وهو مفتاح الدخول و يتم انشائه تلقائيا
