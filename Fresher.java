/*
 * Fresher.java
 *
 * Phiên bản: 1.0
 * Ngày cập nhật: 28/10/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Model của chương trình Quản lý ứng viên.
 * Lớp con biểu diễn ứng viên mới tốt nghiệp.
 */
package model;

/**
 * Ứng viên mới tốt nghiệp. Ngoài các thuộc tính chung còn có thời gian tốt
 * nghiệp, xếp loại tốt nghiệp và trường đã theo học.
 *
 * @version 1.0 28 Oct 2026
 * @author [Họ tên sinh viên]
 */
public class Fresher extends Candidate {

    /**
     * Thời gian tốt nghiệp (dạng dd/MM/yyyy).
     */
    private final String graduationDate;

    /**
     * Xếp loại tốt nghiệp: Excellence, Good, Fair hoặc Poor.
     */
    private final String graduationRank;

    /**
     * Trường đã tốt nghiệp.
     */
    private final String education;

    /**
     * Tạo ứng viên mới tốt nghiệp.
     *
     * @param id             mã ứng viên
     * @param firstName      tên
     * @param lastName       họ
     * @param birthDate      năm sinh
     * @param address        địa chỉ
     * @param phone          số điện thoại
     * @param email          email
     * @param graduationDate thời gian tốt nghiệp
     * @param graduationRank xếp loại tốt nghiệp
     * @param education      trường đã tốt nghiệp
     */
    public Fresher(String id, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            String graduationDate, String graduationRank, String education) {
        super(id, firstName, lastName, birthDate, address, phone, email,
                FRESHER);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    /**
     * @return thời gian tốt nghiệp
     */
    public String getGraduationDate() {
        return graduationDate;
    }

    /**
     * @return xếp loại tốt nghiệp
     */
    public String getGraduationRank() {
        return graduationRank;
    }

    /**
     * @return trường đã tốt nghiệp
     */
    public String getEducation() {
        return education;
    }
}
