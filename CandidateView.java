/*
 * CandidateView.java
 *
 * Phiên bản: 1.1
 * Ngày cập nhật: 06/11/2026
 * Tác giả: [Họ tên sinh viên] - [Mã sinh viên]
 *
 * Copyright (c) 2026 [Họ tên sinh viên]. All rights reserved.
 *
 * Mô tả: Thành phần View của chương trình Quản lý ứng viên.
 * Lớp nhập dữ liệu từ người dùng và hiển thị kết quả ra màn hình.
 */
package view;

import java.util.Calendar;
import java.util.List;

import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Internship;
import util.Validation;

/**
 * Giao diện dòng lệnh của chương trình Quản lý ứng viên. Đây là thành phần
 * View trong kiến trúc MVC: chỉ lo việc nhập, xuất dữ liệu và không xử lý
 * nghiệp vụ.
 *
 * @version 1.1 06 Nov 2026
 * @author [Họ tên sinh viên]
 */
public class CandidateView {

    /**
     * Tiêu đề hiển thị cho từng loại ứng viên, theo thứ tự 0, 1, 2.
     */
    private static final String[] TYPE_TITLES = {
        "EXPERIENCE CANDIDATE", "FRESHER CANDIDATE", "INTERN CANDIDATE"};

    /**
     * Định dạng dòng tiêu đề cột của ứng viên Experience (toàn bộ là chuỗi).
     */
    private static final String EXPERIENCE_HEADER_FORMAT
            = "%-8s %-15s %-15s %-6s %-20s %-12s %-25s %-8s %-20s%n";

    /**
     * Định dạng một dòng dữ liệu của ứng viên Experience: ID, First name,
     * Last name, Birth year, Address, Phone, Email, Years of experience,
     * Professional skill.
     */
    private static final String EXPERIENCE_ROW_FORMAT
            = "%-8s %-15s %-15s %-6d %-20s %-12s %-25s %-8d %-20s%n";

    /**
     * Định dạng dòng tiêu đề cột của ứng viên Fresher (toàn bộ là chuỗi).
     */
    private static final String FRESHER_HEADER_FORMAT
            = "%-8s %-15s %-15s %-6s %-20s %-12s %-25s %-12s %-10s %-20s%n";

    /**
     * Định dạng một dòng dữ liệu của ứng viên Fresher: ID, First name,
     * Last name, Birth year, Address, Phone, Email, Graduation date,
     * Graduation rank, University.
     */
    private static final String FRESHER_ROW_FORMAT
            = "%-8s %-15s %-15s %-6d %-20s %-12s %-25s %-12s %-10s %-20s%n";

    /**
     * Định dạng dòng tiêu đề cột của ứng viên Internship (toàn bộ là chuỗi).
     */
    private static final String INTERN_HEADER_FORMAT
            = "%-8s %-15s %-15s %-6s %-20s %-12s %-25s %-15s %-10s %-20s%n";

    /**
     * Định dạng một dòng dữ liệu của ứng viên Internship: ID, First name,
     * Last name, Birth year, Address, Phone, Email, Major, Semester,
     * University.
     */
    private static final String INTERN_ROW_FORMAT
            = "%-8s %-15s %-15s %-6d %-20s %-12s %-25s %-15s %-10s %-20s%n";

    /**
     * Năm hiện tại, dùng làm cận trên cho năm sinh.
     */
    private static final int CURRENT_YEAR
            = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * Đối tượng kiểm tra dữ liệu nhập.
     */
    private final Validation validation = new Validation();

    /**
     * Hiển thị menu chính.
     */
    public void showMenu() {
        System.out.println("\nCANDIDATE MANAGEMENT SYSTEM");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Internship");
        System.out.println("4. Searching");
        System.out.println("5. Exit");
        System.out.println("(Please choose 1 to Create Experience Candidate, "
                + "2 to Create Fresher Candidate, 3 to Create Internship "
                + "Candidate, 4 to Searching and 5 to Exit program)");
    }

    /**
     * Hiển thị một thông báo.
     *
     * @param message nội dung thông báo
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Nhập lựa chọn trong menu.
     *
     * @param min lựa chọn nhỏ nhất
     * @param max lựa chọn lớn nhất
     * @return lựa chọn hợp lệ
     */
    public int inputChoice(int min, int max) {
        return validation.inputInt("Enter your choice: ", min, max);
    }

    /**
     * Nhập mã ứng viên (chỉ gồm chữ và số, không chứa khoảng trắng).
     *
     * @return mã ứng viên đã nhập
     */
    public String inputCandidateId() {
        return validation.inputString("Enter candidate ID: ",
                "[A-Za-z0-9]+");
    }

    /**
     * Nhập thông tin ứng viên có kinh nghiệm.
     *
     * @param id mã ứng viên đã được Controller kiểm tra không trùng
     * @return ứng viên có kinh nghiệm vừa nhập
     */
    public Experience inputExperience(String id) {
        String firstName = inputFirstName();
        String lastName = inputLastName();
        int birthDate = inputBirthDate();
        String address = inputAddress();
        String phone = inputPhone();
        String email = inputEmail();
        int expInYear = validation.inputInt(
                "Enter year of experience (0-100): ", 0, 100);
        String proSkill = validation.inputString(
                "Enter professional skill: ", ".+");

        return new Experience(id, firstName, lastName, birthDate, address,
                phone, email, expInYear, proSkill);
    }

    /**
     * Nhập thông tin ứng viên mới tốt nghiệp. Năm tốt nghiệp phải sau năm
     * sinh.
     *
     * @param id mã ứng viên đã được Controller kiểm tra không trùng
     * @return ứng viên mới tốt nghiệp vừa nhập
     */
    public Fresher inputFresher(String id) {
        String firstName = inputFirstName();
        String lastName = inputLastName();
        int birthDate = inputBirthDate();
        String address = inputAddress();
        String phone = inputPhone();
        String email = inputEmail();
        String graduationDate = validation.inputGraduationDate(
                "Enter graduation date (dd/MM/yyyy): ", birthDate);
        String graduationRank = validation.inputString(
                "Enter graduation rank (Excellence/Good/Fair/Poor): ",
                Validation.GRADUATION_RANK_REGEX);
        String education = validation.inputString(
                "Enter university where graduated: ", ".+");

        return new Fresher(id, firstName, lastName, birthDate, address, phone,
                email, graduationDate, graduationRank, education);
    }

    /**
     * Nhập thông tin ứng viên thực tập sinh.
     *
     * @param id mã ứng viên đã được Controller kiểm tra không trùng
     * @return thực tập sinh vừa nhập
     */
    public Internship inputInternship(String id) {
        String firstName = inputFirstName();
        String lastName = inputLastName();
        int birthDate = inputBirthDate();
        String address = inputAddress();
        String phone = inputPhone();
        String email = inputEmail();
        String major = validation.inputString("Enter major: ", ".+");
        String semester = validation.inputString("Enter semester: ", ".+");
        String university = validation.inputString(
                "Enter university name: ", ".+");

        return new Internship(id, firstName, lastName, birthDate, address,
                phone, email, major, semester, university);
    }

    /**
     * Hỏi người dùng có muốn tiếp tục nhập ứng viên hay không.
     *
     * @return "Y" hoặc "N" (không phân biệt hoa thường)
     */
    public String inputContinue() {
        return validation.inputString("Do you want to continue (Y/N)? ",
                "^(?i:Y|N)$");
    }

    /**
     * Nhập từ khóa tên cần tìm (họ hoặc tên).
     *
     * @return từ khóa tìm kiếm
     */
    public String inputSearchName() {
        return validation.inputString(
                "Input Candidate name (First name or Last name): ", ".+");
    }

    /**
     * Nhập loại ứng viên cần tìm.
     *
     * @return 0 - Experience, 1 - Fresher, 2 - Intern
     */
    public int inputSearchType() {
        return validation.inputInt(
                "Input type of candidate (0: Experience, 1: Fresher, "
                + "2: Intern): ", 0, 2);
    }

    /**
     * Hiển thị tên các ứng viên, gom nhóm theo từng loại.
     *
     * @param candidates danh sách ứng viên cần hiển thị
     */
    public void displayCandidateNames(List<Candidate> candidates) {
        System.out.println("\nList of candidate:");

        for (int type = 0; type < TYPE_TITLES.length; type++) {
            System.out.println("===========" + TYPE_TITLES[type]
                    + "============");

            for (Candidate candidate : candidates) {
                if (candidate.getCandidateType() == type) {
                    System.out.println(candidate.getFullName());
                }
            }
        }
    }

    /**
     * Hiển thị thông tin chi tiết của các ứng viên, gom nhóm theo từng loại.
     *
     * @param candidates danh sách ứng viên cần hiển thị
     */
    public void displayCandidates(List<Candidate> candidates) {
        for (int type = 0; type < TYPE_TITLES.length; type++) {
            System.out.println("===========" + TYPE_TITLES[type]
                    + "============");
            printHeader(type);

            for (Candidate candidate : candidates) {
                if (candidate.getCandidateType() == type) {
                    printCandidate(candidate);
                }
            }
        }
    }

    /**
     * Hiển thị kết quả tìm kiếm. Các ứng viên trong danh sách cùng một loại
     * (Controller đã lọc theo loại) nên dùng chung một dòng tiêu đề.
     *
     * @param candidates danh sách ứng viên tìm được (không rỗng)
     */
    public void displaySearchResult(List<Candidate> candidates) {
        System.out.println("\nThe candidates found:");
        printHeader(candidates.get(0).getCandidateType());

        for (Candidate candidate : candidates) {
            printCandidate(candidate);
        }
    }

    /**
     * In dòng tiêu đề cột tương ứng với loại ứng viên.
     *
     * @param type 0 - Experience, 1 - Fresher, 2 - Intern
     */
    private void printHeader(int type) {
        switch (type) {
            case Candidate.EXPERIENCE:
                System.out.print(String.format(EXPERIENCE_HEADER_FORMAT,
                        "ID", "First name", "Last name", "Birth", "Address",
                        "Phone", "Email", "Exp(yr)", "Professional skill"));
                break;
            case Candidate.FRESHER:
                System.out.print(String.format(FRESHER_HEADER_FORMAT,
                        "ID", "First name", "Last name", "Birth", "Address",
                        "Phone", "Email", "Graduation", "Rank",
                        "University"));
                break;
            default:
                System.out.print(String.format(INTERN_HEADER_FORMAT,
                        "ID", "First name", "Last name", "Birth", "Address",
                        "Phone", "Email", "Major", "Semester",
                        "University"));
                break;
        }
    }

    /**
     * In đầy đủ thông tin một ứng viên theo định dạng của loại tương ứng.
     *
     * @param candidate ứng viên cần in
     */
    private void printCandidate(Candidate candidate) {
        if (candidate instanceof Experience) {
            Experience e = (Experience) candidate;
            System.out.print(String.format(EXPERIENCE_ROW_FORMAT,
                    e.getId(), e.getFirstName(), e.getLastName(),
                    e.getBirthDate(), e.getAddress(), e.getPhone(),
                    e.getEmail(), e.getExpInYear(), e.getProSkill()));
        } else if (candidate instanceof Fresher) {
            Fresher f = (Fresher) candidate;
            System.out.print(String.format(FRESHER_ROW_FORMAT,
                    f.getId(), f.getFirstName(), f.getLastName(),
                    f.getBirthDate(), f.getAddress(), f.getPhone(),
                    f.getEmail(), f.getGraduationDate(),
                    f.getGraduationRank(), f.getEducation()));
        } else if (candidate instanceof Internship) {
            Internship i = (Internship) candidate;
            System.out.print(String.format(INTERN_ROW_FORMAT,
                    i.getId(), i.getFirstName(), i.getLastName(),
                    i.getBirthDate(), i.getAddress(), i.getPhone(),
                    i.getEmail(), i.getMajor(), i.getSemester(),
                    i.getUniversity()));
        }
    }

    /**
     * Nhập tên.
     *
     * @return tên hợp lệ (chữ cái và khoảng trắng)
     */
    private String inputFirstName() {
        return validation.inputString("Enter first name: ",
                "[\\p{L}\\s]+");
    }

    /**
     * Nhập họ.
     *
     * @return họ hợp lệ (chữ cái và khoảng trắng)
     */
    private String inputLastName() {
        return validation.inputString("Enter last name: ",
                "[\\p{L}\\s]+");
    }

    /**
     * Nhập năm sinh trong khoảng 1900 đến năm hiện tại.
     *
     * @return năm sinh hợp lệ
     */
    private int inputBirthDate() {
        return validation.inputInt("Enter birth date (1900-" + CURRENT_YEAR
                + "): ", 1900, CURRENT_YEAR);
    }

    /**
     * Nhập địa chỉ.
     *
     * @return địa chỉ không rỗng
     */
    private String inputAddress() {
        return validation.inputString("Enter address: ", ".+");
    }

    /**
     * Nhập số điện thoại (số, tối thiểu 10 ký tự).
     *
     * @return số điện thoại hợp lệ
     */
    private String inputPhone() {
        return validation.inputString("Enter phone (at least 10 digits): ",
                Validation.PHONE_REGEX);
    }

    /**
     * Nhập email theo dạng account@domain.
     *
     * @return email hợp lệ
     */
    private String inputEmail() {
        return validation.inputString("Enter email: ",
                Validation.EMAIL_REGEX);
    }
}