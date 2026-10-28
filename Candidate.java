/*
 * Candidate.java
 *
 * Phiên bản: 1.0
 * Ngày cập nhật: 28/10/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Model của chương trình Quản lý ứng viên.
 * Lớp cha chứa các thuộc tính chung của mọi loại ứng viên.
 */
package model;

/**
 * Lớp cha của các loại ứng viên (Experience, Fresher, Internship). Chứa các
 * thuộc tính chung: mã, họ tên, năm sinh, địa chỉ, số điện thoại, email và loại
 * ứng viên.
 *
 * @version 1.0 28 Oct 2026
 * @author [Họ tên sinh viên]
 */
public abstract class Candidate {

    /**
     * Loại ứng viên có kinh nghiệm.
     */
    public static final int EXPERIENCE = 0;

    /**
     * Loại ứng viên mới tốt nghiệp.
     */
    public static final int FRESHER = 1;

    /**
     * Loại ứng viên thực tập sinh.
     */
    public static final int INTERN = 2;

    /**
     * Mã ứng viên.
     */
    private final String id;

    /**
     * Tên của ứng viên.
     */
    private final String firstName;

    /**
     * Họ của ứng viên.
     */
    private final String lastName;

    /**
     * Năm sinh của ứng viên (gồm 4 chữ số).
     */
    private final int birthDate;

    /**
     * Địa chỉ của ứng viên.
     */
    private final String address;

    /**
     * Số điện thoại của ứng viên.
     */
    private final String phone;

    /**
     * Email của ứng viên.
     */
    private final String email;

    /**
     * Loại ứng viên: 0 - Experience, 1 - Fresher, 2 - Intern.
     */
    private final int candidateType;

    /**
     * Tạo ứng viên với đầy đủ thông tin chung.
     *
     * @param id            mã ứng viên
     * @param firstName     tên
     * @param lastName      họ
     * @param birthDate     năm sinh
     * @param address       địa chỉ
     * @param phone         số điện thoại
     * @param email         email
     * @param candidateType loại ứng viên (0, 1 hoặc 2)
     */
    protected Candidate(String id, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            int candidateType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.candidateType = candidateType;
    }

    /**
     * @return mã ứng viên
     */
    public String getId() {
        return id;
    }

    /**
     * @return tên của ứng viên
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return họ của ứng viên
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return năm sinh của ứng viên
     */
    public int getBirthDate() {
        return birthDate;
    }

    /**
     * @return địa chỉ của ứng viên
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return số điện thoại của ứng viên
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return email của ứng viên
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return loại ứng viên (0, 1 hoặc 2)
     */
    public int getCandidateType() {
        return candidateType;
    }

    /**
     * Lấy họ tên đầy đủ theo dạng "First name + Last name".
     *
     * @return họ tên đầy đủ
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Chuỗi mô tả ứng viên theo định dạng của đề bài: tên | năm sinh | địa chỉ
     * | số điện thoại | email | loại ứng viên.
     *
     * @return chuỗi mô tả ứng viên
     */
    @Override
    public String toString() {
        return getFullName() + " | " + birthDate + " | " + address + " | "
                + phone + " | " + email + " | " + candidateType;
    }
}
