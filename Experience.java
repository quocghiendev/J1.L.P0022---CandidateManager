/*
 * Experience.java
 *
 * Phiên bản: 1.0
 * Ngày cập nhật: 28/10/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Model của chương trình Quản lý ứng viên.
 * Lớp con biểu diễn ứng viên có kinh nghiệm làm việc.
 */
package model;

/**
 * Ứng viên có kinh nghiệm. Ngoài các thuộc tính chung còn có số năm kinh
 * nghiệm và kỹ năng chuyên môn.
 *
 * @version 1.0 28 Oct 2026
 * @author [Họ tên sinh viên]
 */
public class Experience extends Candidate {

    /**
     * Số năm kinh nghiệm (từ 0 đến 100).
     */
    private final int expInYear;

    /**
     * Kỹ năng chuyên môn.
     */
    private final String proSkill;

    /**
     * Tạo ứng viên có kinh nghiệm.
     *
     * @param id        mã ứng viên
     * @param firstName tên
     * @param lastName  họ
     * @param birthDate năm sinh
     * @param address   địa chỉ
     * @param phone     số điện thoại
     * @param email     email
     * @param expInYear số năm kinh nghiệm
     * @param proSkill  kỹ năng chuyên môn
     */
    public Experience(String id, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            int expInYear, String proSkill) {
        super(id, firstName, lastName, birthDate, address, phone, email,
                EXPERIENCE);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    /**
     * @return số năm kinh nghiệm
     */
    public int getExpInYear() {
        return expInYear;
    }

    /**
     * @return kỹ năng chuyên môn
     */
    public String getProSkill() {
        return proSkill;
    }
}
