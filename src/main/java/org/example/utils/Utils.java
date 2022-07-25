package org.example.utils;


// تحويل من الجافا يوتل اللى جافا اسكيو ال
public class Utils {
    public static java.sql.Date getSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}