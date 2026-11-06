/*
 * Validation.java
 *
 * Phiên bản: 1.1
 * Ngày cập nhật: 06/11/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Lớp tiện ích đọc và kiểm tra dữ liệu nhập từ bàn phím cho chương
 * trình Quản lý ứng viên.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Cung cấp các hàm nhập dữ liệu có kiểm tra tính hợp lệ (chuỗi theo mẫu regex,
 * số nguyên trong khoảng, ngày, ngày tốt nghiệp sau năm sinh). Mỗi hàm lặp lại
 * cho tới khi người dùng nhập đúng.
 *
 * @version 1.1 06 Nov 2026
 * @author [Họ tên sinh viên]
 */
public class Validation {

    /**
     * Mẫu số điện thoại: chỉ gồm chữ số, tối thiểu 10 ký tự.
     */
    public static final String PHONE_REGEX = "\\d{10,}";

    /**
     * Mẫu email: dạng account@domain (ví dụ annguyen@fpt.edu.vn).
     */
    public static final String EMAIL_REGEX
            = "[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+";

    /**
     * Các xếp loại tốt nghiệp hợp lệ.
     */
    private static final String[] GRADUATION_RANKS
            = {"Excellence", "Good", "Fair", "Poor"};

    /**
     * Mẫu xếp loại tốt nghiệp: một trong các xếp loại hợp lệ, không phân biệt
     * chữ hoa, chữ thường.
     */
    public static final String GRADUATION_RANK_REGEX
            = "(?i)" + String.join("|", GRADUATION_RANKS);

    /**
     * Định dạng ngày tốt nghiệp.
     */
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Mẫu chuỗi ngày: 2 chữ số ngày, 2 chữ số tháng, 4 chữ số năm. Dùng để
     * chặn năm viết thiếu chữ số (ví dụ 01/01/20) mà SimpleDateFormat vẫn
     * chấp nhận.
     */
    private static final String DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    /**
     * Đối tượng đọc dữ liệu từ bàn phím.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Đọc một dòng từ bàn phím và bỏ khoảng trắng hai đầu.
     *
     * @return dòng đã đọc
     * @throws IllegalStateException nếu luồng nhập đã đóng
     */
    private String readLine() {
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("Input stream closed.");
        }
        return scanner.nextLine().trim();
    }

    /**
     * Nhập chuỗi khớp với mẫu regex cho trước.
     *
     * @param message nội dung nhắc nhập
     * @param regex   mẫu mà chuỗi phải khớp
     * @return chuỗi hợp lệ
     */
    public String inputString(String message, String regex) {
        while (true) {
            System.out.print(message);
            String input = readLine();

            if (input.matches(regex)) {
                return input;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    /**
     * Nhập số nguyên trong khoảng [min, max].
     *
     * @param message nội dung nhắc nhập
     * @param min     giá trị nhỏ nhất
     * @param max     giá trị lớn nhất
     * @return số nguyên hợp lệ
     */
    public int inputInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = readLine();

            try {
                int number = Integer.parseInt(input);

                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number from " + min
                        + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer number.");
            }
        }
    }

    /**
     * Nhập ngày theo định dạng dd/MM/yyyy (không chấp nhận ngày không tồn
     * tại, ví dụ 31/02/2020).
     *
     * @param message nội dung nhắc nhập
     * @return ngày hợp lệ dưới dạng chuỗi dd/MM/yyyy
     */
    public String inputDate(String message) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setLenient(false);

        while (true) {
            System.out.print(message);
            String input = readLine();

            if (input.matches(DATE_REGEX)) {
                try {
                    format.parse(input);
                    return input;
                } catch (ParseException e) {

                    /* Rơi xuống thông báo lỗi bên dưới. */
                }
            }
            System.out.println("Invalid date. Format must be "
                    + DATE_FORMAT + ".");
        }
    }

    /**
     * Nhập ngày tốt nghiệp (dd/MM/yyyy) và bắt buộc năm tốt nghiệp phải sau
     * năm sinh.
     *
     * @param message   nội dung nhắc nhập
     * @param birthYear năm sinh của ứng viên
     * @return ngày tốt nghiệp hợp lệ dưới dạng chuỗi dd/MM/yyyy
     */
    public String inputGraduationDate(String message, int birthYear) {
        while (true) {
            String date = inputDate(message);

            if (isAfterBirthYear(date, birthYear)) {
                return date;
            }
            System.out.println("Graduation year must be after birth year ("
                    + birthYear + ").");
        }
    }

    /**
     * Kiểm tra năm của ngày (dd/MM/yyyy) có lớn hơn năm sinh hay không.
     *
     * @param date      ngày hợp lệ dạng dd/MM/yyyy
     * @param birthYear năm sinh
     * @return true nếu năm của ngày lớn hơn năm sinh
     */
    private boolean isAfterBirthYear(String date, int birthYear) {
        int year = Integer.parseInt(date.substring(6));
        return year > birthYear;
    }
}