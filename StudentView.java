/*
 * StudentView.java
 *
 * Phiên bản: 1.2
 * Ngày cập nhật: 30/09/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần View của chương trình Quản lý sinh viên. Lớp chỉ
 * phụ trách nhập dữ liệu từ bàn phím và xuất kết quả ra màn hình.
 */
package view;

import java.util.List;

import model.Student;
import util.Validation;


/**
 * Giao diện dòng lệnh của chương trình Quản lý sinh viên.
 * 
 * Đây là thành phần View trong kiến trúc MVC: hiển thị menu, đọc dữ liệu
 * người dùng nhập và in danh sách sinh viên. Lớp không lưu trữ danh sách
 * và không chứa nghiệp vụ; mọi quyết định do Controller đưa ra.
 *
 * @version 1.2 30 Sep 2026
 * @author [Họ tên sinh viên]
 */
public class StudentView {

    /*
     * Mọi nội dung hiển thị của View (menu, lời nhắc nhập, bảng dữ liệu,
     * thông báo) đều đi qua showMessage. Validation chỉ đọc và kiểm tra
     * dữ liệu, không in lời nhắc.
     */

    /** Số sinh viên tối đa được thêm trong một lần nhập. */
    private static final int MAX_STUDENTS_PER_INPUT = 100;

    /** Mẫu định dạng một dòng của bảng sinh viên. */
    private static final String ROW_FORMAT = "%-10s %-25s %-5s %-5s";

    /** Đường kẻ ngăn cách tiêu đề và nội dung bảng. */
    private static final String SEPARATOR =
            "------------------------------------------------";

    /** Đối tượng hỗ trợ đọc dữ liệu có kiểm tra hợp lệ. */
    private final Validation validation;

    /**
     * Tạo View cùng đối tượng Validation dùng để đọc dữ liệu.
     */
    public StudentView() {
        validation = new Validation();
    }

    /**
     * Hiển thị menu chính gồm các chức năng đánh số từ 1 đến 5.
     */
    public void showMenu() {
        showMessage("\n========== STUDENT MANAGEMENT ==========");
        showMessage("1. Add students");
        showMessage("2. Display students");
        showMessage("3. Search student by name");
        showMessage("4. Sort students by GPA");
        showMessage("5. Exit");
        showMessage("========================================");
    }

    /**
     * Đọc số chức năng người dùng chọn từ bàn phím.
     * Giá trị không phải số nguyên hoặc nằm ngoài khoảng [min, max] sẽ
     * bị từ chối và người dùng phải nhập lại.
     *
     * @param min số chức năng nhỏ nhất trên menu
     * @param max số chức năng lớn nhất trên menu
     * @return số chức năng hợp lệ trong khoảng [min, max]
     */
    public int inputChoice(int min, int max) {
        showMessage("Choose a function (" + min + "-" + max + "):");
        return validation.inputInt(min, max);
    }

    /**
     * Đọc số lượng sinh viên cần thêm.
     *
     * @return số lượng trong khoảng [1, MAX_STUDENTS_PER_INPUT]
     */
    public int inputNumberOfStudents() {
        showMessage("Enter number of students (1-"
                + MAX_STUDENTS_PER_INPUT + "):");
        return validation.inputInt(1, MAX_STUDENTS_PER_INPUT);
    }

    /**
     * Đọc mã sinh viên.
     * Tách riêng khỏi {@link #inputStudentDetails(String)} để Controller
     * kiểm tra trùng mã ngay sau khi nhập, trước khi hỏi các thông tin khác.
     *
     * @return mã sinh viên khác rỗng
     */
    public String inputStudentId() {
        showMessage("Enter ID:");
        return validation.inputString();
    }

    /**
     * Đọc họ tên, tuổi, điểm trung bình và tạo đối tượng Student.
     *
     * @param id mã sinh viên đã được Controller kiểm tra hợp lệ
     * @return sinh viên mới với dữ liệu người dùng nhập
     */
    public Student inputStudentDetails(String id) {
        showMessage("Enter name:");
        String name = validation.inputString();

        showMessage("Enter age (" + Student.MIN_AGE + "-"
                + Student.MAX_AGE + "):");
        int age = validation.inputInt(Student.MIN_AGE, Student.MAX_AGE);

        showMessage("Enter GPA (" + Student.MIN_GPA + "-"
                + Student.MAX_GPA + "):");
        double gpa = validation.inputDouble(Student.MIN_GPA,
                Student.MAX_GPA);

        return new Student(id, name, age, gpa);
    }

    /**
     * Đọc từ khóa tìm kiếm theo tên.
     *
     * @return từ khóa khác rỗng
     */
    public String inputSearchName() {
        showMessage("Enter name to search:");
        return validation.inputString();
    }

    /**
     * Hiển thị danh sách sinh viên dưới dạng bảng.
     * 
     * Nếu danh sách rỗng, hiển thị thông báo thay vì bảng.
     *
     * @param students danh sách cần hiển thị
     */
    public void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            showMessage("Student list is empty.");
            return;
        }

        showMessage(String.format(ROW_FORMAT, "ID", "Name", "Age", "GPA"));
        showMessage(SEPARATOR);

        for (Student student : students) {
            showMessage(String.format(ROW_FORMAT,
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    String.format("%.2f", student.getGpa())));
        }
    }

    /**
     * Hiển thị một dòng thông báo ra màn hình. Đây là điểm xuất duy nhất
     * của View.
     *
     * @param message nội dung thông báo
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
