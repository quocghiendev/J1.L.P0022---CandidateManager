/*
 * Internship.java
 *
 * Phiên bản: 1.0
 * Ngày cập nhật: 28/10/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Model của chương trình Quản lý ứng viên.
 * Lớp con biểu diễn ứng viên thực tập sinh.
 */
package model;

/**
 * Ứng viên thực tập sinh. Ngoài các thuộc tính chung còn có chuyên ngành, học
 * kỳ và tên trường đại học.
 *
 * @version 1.0 28 Oct 2026
 * @author [Họ tên sinh viên]
 */
public class Internship extends Candidate {

    /**
     * Chuyên ngành đang học.
     */
    private final String major;

    /**
     * Học kỳ hiện tại.
     */
    private final String semester;

    /**
     * Tên trường đại học.
     */
    private final String university;

    /**
     * Tạo ứng viên thực tập sinh.
     *
     * @param id         mã ứng viên
     * @param firstName  tên
     * @param lastName   họ
     * @param birthDate  năm sinh
     * @param address    địa chỉ
     * @param phone      số điện thoại
     * @param email      email
     * @param major      chuyên ngành
     * @param semester   học kỳ
     * @param university tên trường đại học
     */
    public Internship(String id, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            String major, String semester, String university) {
        super(id, firstName, lastName, birthDate, address, phone, email,
                INTERN);
        this.major = major;
        this.semester = semester;
        this.university = university;
    }

    /**
     * @return chuyên ngành
     */
    public String getMajor() {
        return major;
    }

    /**
     * @return học kỳ
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @return tên trường đại học
     */
    public String getUniversity() {
        return university;
    }
}
