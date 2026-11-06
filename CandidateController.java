/*
 * CandidateController.java
 *
 * Phiên bản: 1.1
 * Ngày cập nhật: 06/11/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần Controller của chương trình Quản lý ứng viên.
 * Lớp điều phối luồng chương trình và xử lý nghiệp vụ trên danh sách
 * ứng viên.
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import model.Candidate;
import view.CandidateView;

/**
 * Điều phối giữa View và Model của chương trình Quản lý ứng viên. Đây là thành
 * phần Controller trong kiến trúc MVC: nhận lựa chọn từ View, thực hiện nghiệp
 * vụ (thêm, kiểm tra trùng mã, kiểm tra trùng ứng viên, tìm kiếm) trên danh
 * sách Candidate, rồi yêu cầu View hiển thị kết quả.
 *
 * @version 1.1 06 Nov 2026
 * @author [Họ tên sinh viên]
 */
public class CandidateController {

    /**
     * Danh sách ứng viên đang được quản lý (lưu trong ArrayList).
     */
    private final List<Candidate> candidates;

    /**
     * View dùng để nhập và xuất dữ liệu.
     */
    private final CandidateView view;

    /**
     * Tạo Controller với danh sách rỗng và View mặc định.
     */
    public CandidateController() {
        candidates = new ArrayList<>();
        view = new CandidateView();
    }

    /**
     * Chạy chương trình cho tới khi người dùng chọn thoát hoặc luồng nhập bị
     * đóng.
     */
    public void run() {
        try {
            runMenu();
        } catch (IllegalStateException e) {

            /*
             * Luồng nhập đã đóng (Ctrl+D / Ctrl+Z hoặc file input hết dữ
             * liệu): không thể đọc tiếp nên thông báo và kết thúc.
             */
            view.showMessage("\n" + e.getMessage()
                    + " Program terminated.");
        }
    }

    /**
     * Lặp hiển thị menu, đọc lựa chọn và gọi chức năng tương ứng cho tới khi
     * người dùng chọn thoát.
     */
    private void runMenu() {
        while (true) {
            view.showMenu();

            /*
             * Menu có 5 chức năng đánh số từ 1 đến 5; View đọc và kiểm tra
             * để lựa chọn luôn nằm trong khoảng này.
             */
            int choice = view.inputChoice(1, 5);

            switch (choice) {
                case 1:
                case 2:
                case 3:

                    /* Lựa chọn 1, 2, 3 tương ứng loại ứng viên 0, 1, 2. */
                    createCandidates(choice - 1);
                    break;
                case 4:
                    searchCandidate();
                    break;
                case 5:
                    view.showMessage("Program terminated.");
                    return;
                default:

                    /* Không xảy ra vì View đã kiểm tra lựa chọn hợp lệ. */
                    view.showMessage("Invalid choice.");
                    break;
            }
        }
    }

    /**
     * Tạo ứng viên thuộc loại cho trước. Ứng viên trùng họ tên và trùng số
     * điện thoại hoặc email với một ứng viên đã có sẽ không được thêm. Sau mỗi
     * lần nhập, hỏi người dùng có tiếp tục hay không. Khi dừng, hiển thị toàn
     * bộ ứng viên đã tạo.
     *
     * @param type loại ứng viên (0 - Experience, 1 - Fresher, 2 - Intern)
     */
    private void createCandidates(int type) {
        do {
            String id = inputUniqueId();
            Candidate candidate = inputCandidate(type, id);

            if (isCandidateExisted(candidate)) {
                view.showMessage("Candidate already exists (same full name "
                        + "and same phone or email). Not created.");
            } else {
                candidates.add(candidate);
                view.showMessage("Candidate added successfully.");
            }
        } while (view.inputContinue().equalsIgnoreCase("Y"));

        view.showMessage("\nAll candidates created:");
        view.displayCandidates(candidates);
    }

    /**
     * Nhờ View nhập thông tin ứng viên theo đúng loại.
     *
     * @param type loại ứng viên (0 - Experience, 1 - Fresher, 2 - Intern)
     * @param id   mã ứng viên đã được kiểm tra không trùng
     * @return ứng viên vừa nhập
     */
    private Candidate inputCandidate(int type, String id) {
        switch (type) {
            case Candidate.EXPERIENCE:
                return view.inputExperience(id);
            case Candidate.FRESHER:
                return view.inputFresher(id);
            default:
                return view.inputInternship(id);
        }
    }

    /**
     * Yêu cầu nhập mã ứng viên cho tới khi mã chưa tồn tại trong danh sách.
     *
     * @return mã ứng viên chưa được sử dụng
     */
    private String inputUniqueId() {
        while (true) {
            String id = view.inputCandidateId();

            if (!isIdExisted(id)) {
                return id;
            }
            view.showMessage("ID " + id + " already exists. Try again.");
        }
    }

    /**
     * Kiểm tra mã ứng viên đã có trong danh sách hay chưa.
     * <p>
     * Phép so sánh không phân biệt chữ hoa, chữ thường.
     *
     * @param id mã cần kiểm tra
     * @return true nếu mã đã tồn tại, ngược lại false
     */
    private boolean isIdExisted(String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra ứng viên đã tồn tại trong danh sách hay chưa. Ứng viên được coi
     * là đã tồn tại khi trùng họ tên đầy đủ VÀ (trùng số điện thoại HOẶC trùng
     * email) với một ứng viên có sẵn, bất kể loại ứng viên.
     *
     * @param newCandidate ứng viên mới nhập
     * @return true nếu đã tồn tại ứng viên trùng, ngược lại false
     */
    private boolean isCandidateExisted(Candidate newCandidate) {
        String newName = normalizeName(newCandidate.getFullName());

        for (Candidate candidate : candidates) {
            boolean sameName
                    = normalizeName(candidate.getFullName()).equals(newName);
            boolean samePhone
                    = candidate.getPhone().equals(newCandidate.getPhone());
            boolean sameEmail
                    = candidate.getEmail().equalsIgnoreCase(
                            newCandidate.getEmail());

            if (sameName && (samePhone || sameEmail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chuẩn hóa họ tên để so sánh: bỏ khoảng trắng thừa, gom nhiều khoảng
     * trắng liên tiếp thành một và chuyển về chữ thường.
     *
     * @param name họ tên cần chuẩn hóa
     * @return họ tên đã chuẩn hóa
     */
    private String normalizeName(String name) {
        return name.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    /**
     * Hiển thị danh sách tên ứng viên, nhận từ khóa và loại ứng viên rồi tìm
     * kiếm và hiển thị kết quả.
     */
    private void searchCandidate() {
        if (candidates.isEmpty()) {
            view.showMessage("Candidate list is empty.");
            return;
        }
        view.displayCandidateNames(candidates);

        String keyword = view.inputSearchName().toLowerCase();
        int type = view.inputSearchType();
        List<Candidate> result = new ArrayList<>();

        for (Candidate candidate : candidates) {

            /*
             * Ứng viên phải đúng loại VÀ có họ hoặc tên chứa từ khóa
             * (không phân biệt hoa thường).
             */
            boolean matchName
                    = candidate.getFirstName().toLowerCase().contains(keyword)
                    || candidate.getLastName().toLowerCase().contains(keyword);

            if (candidate.getCandidateType() == type && matchName) {
                result.add(candidate);
            }
        }

        if (result.isEmpty()) {
            view.showMessage("No candidate found.");
        } else {
            view.displaySearchResult(result);
        }
    }
}