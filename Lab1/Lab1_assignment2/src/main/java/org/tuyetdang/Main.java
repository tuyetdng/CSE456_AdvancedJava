package org.tuyetdang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        //Sử dụng các class cung cấp sẵn có trong thư viên JDBC (có trong JAVA SDK)
        //JDBC sẽ tự động kết nối với MySQL JDBC Server (MySQL connector/java)
        try {
            String url = "jdbc:mysql://localhost:3306/lab1_cse456";

            //Đối với Java mới thì Driver sẽ được tự động dò tìm trong URL mà ko cần lệnh này.
            //Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url,"root","123123123");
            System.out.println("Connected to database successfully");

            //Sau khi kết nối thành công thì tiếp tục thực hiện truy vấn bằng SQL
            //Tạo Class PreparedStatement để thực hiện câu truy vấn.
            PreparedStatement pstmt = conn.prepareStatement("select * from student");

            // Insert data to the database
            String sql = "INSERT INTO student VALUES (?,?,?,?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql);
            pstmt1.setString(1, "001");
            pstmt1.setString(2, "Dang");
            pstmt1.setString(3, "Tuyet");
            pstmt1.setInt(4, 2003);
            pstmt1.setDouble(5, 3.52);
            pstmt1.executeUpdate();

            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                System.out.print(rs.getString(1));
//                System.out.print(rs.getString(2));
//                System.out.print(rs.getString(3));
//                System.out.print(rs.getString(4));
//                System.out.println(rs.getString(5));
//            }
            while (rs.next()) {
                String id = rs.getString("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int yearOfBirth = rs.getInt("yearOfBirth");
                double gpa = rs.getInt("gpa");
//                System.out.println(id + "|" + firstName + "|" + lastName + "|" + yearOfBirth + "|" + gpa + "|" );
                System.out.printf("|%10s|%-10s|%-10s|%2d|%4f|\n",id,firstName,lastName,yearOfBirth,gpa);

            }
            conn.close();
            System.out.println("Disconnected from database successfully");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}