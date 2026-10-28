/*
 * Main.java
 *
 * Phiên bản: 1.0
 * Ngày cập nhật: 28/10/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Điểm khởi chạy của chương trình Quản lý ứng viên.
 */
package main;

import controller.CandidateController;

/**
 * Lớp chứa hàm main, chỉ khởi tạo Controller và chạy chương trình.
 *
 * @version 1.0 28 Oct 2026
 * @author [Họ tên sinh viên]
 */
public class Main {

    /**
     * Hàm main của chương trình.
     *
     * @param args tham số dòng lệnh (không sử dụng)
     */
    public static void main(String[] args) {
        new CandidateController().run();
    }
}
