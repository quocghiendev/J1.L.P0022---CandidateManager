/*
 * Validation.java
 *
 * Phiên bản: 1.0
 * Ngày cập nhật: 28/10/2026
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
 * số nguyên trong khoảng, ngày, xếp loại tốt nghiệp, Y/N). Mỗi hàm lặp lại cho
 * tới khi người dùng nhập đúng.
 *
 * @version 1.0 28 Oct 2026
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
     * Định dạng ngày tốt nghiệp.
     */
    private static final String DATE_FORMAT = "dd/MM/yyyy";

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

            try {
                format.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println("Invalid date. Format must be "
                        + DATE_FORMAT + ".");
            }
        }
    }

    /**
     * Nhập xếp loại tốt nghiệp, chỉ chấp nhận Excellence, Good, Fair hoặc
     * Poor (không phân biệt hoa thường).
     *
     * @param message nội dung nhắc nhập
     * @return xếp loại đã chuẩn hóa chữ hoa đầu
     */
    public String inputGraduationRank(String message) {
        while (true) {
            System.out.print(message);
            String input = readLine();

            for (String rank : GRADUATION_RANKS) {
                if (rank.equalsIgnoreCase(input)) {
                    return rank;
                }
            }
            System.out.println("Rank must be one of: Excellence, Good, "
                    + "Fair, Poor.");
        }
    }

    /**
     * Nhập lựa chọn Y/N.
     *
     * @param message nội dung nhắc nhập
     * @return true nếu người dùng chọn Y, false nếu chọn N
     */
    public boolean inputYesNo(String message) {
        while (true) {
            System.out.print(message);
            String input = readLine();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N.");
        }
    }
}
