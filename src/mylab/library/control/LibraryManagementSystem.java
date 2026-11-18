
package mylab.library.control;

import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        // STEP 4-1. 도서관 객체 생성
        Library library = new Library("중앙 도서관");

        // STEP 4-2. 샘플 도서 6권 등록
        addSampleBooks(library);

        // 도서관 기본 정보
        System.out.println("===== " + library.getName() + " =====");

        System.out.println("전체 도서 수: "
                + library.getTotalBooks());

        System.out.println("대출 가능 도서 수: "
                + library.getAvailableBooksCount());

        System.out.println("대출 중인 도서 수: "
                + library.getBorrowedBooksCount());

        // STEP 4-3. 도서 검색
        testFindBook(library);

        // STEP 4-4. 도서 대출
        testCheckOut(library);
        
        // STEP 4-5. 도서 반납
        testReturn(library);
        
        // STEP 4-6. 대출 가능한 도서 목록 출력
        displayAvailableBooks(library);
        
        testExtraCases(library);
    }

    // STEP 4-2. 샘플 도서 등록
    private static void addSampleBooks(Library library) {

        library.addBook(new Book(
                "자바 프로그래밍",
                "김자바",
                "978-89-01-12345-6",
                2022
        ));

        library.addBook(new Book(
                "객체지향의 사실과 오해",
                "조영호",
                "978-89-01-67890-1",
                2015
        ));

        library.addBook(new Book(
                "Clean Code",
                "Robert C. Martin",
                "978-0-13-235088-4",
                2008
        ));

        library.addBook(new Book(
                "Effective Java",
                "Joshua Bloch",
                "978-0-13-468599-1",
                2018
        ));

        library.addBook(new Book(
                "Head First Java",
                "Kathy Sierra",
                "978-0-596-00920-5",
                2005
        ));

        library.addBook(new Book(
                "자바의 정석",
                "남궁성",
                "978-89-01-14077-4",
                2019
        ));
    }

    // STEP 4-3. 도서 검색
    private static void testFindBook(Library library) {

        System.out.println("===== 도서 검색 테스트 =====");

        // 제목 검색
        System.out.println("제목으로 검색 결과:");

        Book book = library.findBookByTitle("자바의 정석");

        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println(
                    "해당 제목의 도서를 찾을 수 없습니다."
            );
        }

        // 저자 검색
        System.out.println("저자로 검색 결과:");

        List<Book> books =
                library.findBooksByAuthor("Robert C. Martin");

        if (!books.isEmpty()) {

            for (Book foundBook : books) {
                System.out.println(foundBook);
            }

        } else {
            System.out.println(
                    "해당 저자의 도서를 찾을 수 없습니다."
            );
        }
    }

    // STEP 4-4. 도서 대출
    private static void testCheckOut(Library library) {

        System.out.println("===== 도서 대출 테스트 =====");

        String isbn = "978-89-01-14077-4";

        // 대출 실행
        boolean result = library.checkOutBook(isbn);

        if (result) {
            System.out.println("도서 대출 성공!");
        } else {
            System.out.println("도서 대출 실패!");
        }

        // 대출된 도서 확인
        Book book = library.findBookByISBN(isbn);

        if (book != null) {
            System.out.println("대출된 도서 정보:");
            System.out.println(book);
        }

        // 대출 후 도서관 상태
        System.out.println("도서관 현재 상태:");

        System.out.println("전체 도서 수: "
                + library.getTotalBooks());

        System.out.println("대출 가능 도서 수: "
                + library.getAvailableBooksCount());

        System.out.println("대출 중인 도서 수: "
                + library.getBorrowedBooksCount());
    }
    

    // STEP 4-5. 도서 반납 테스트
    private static void testReturn(Library library) {

        System.out.println("===== 도서 반납 테스트 =====");

        // 1. 반납할 도서의 ISBN
        String isbn = "978-89-01-14077-4";

        // 2. 도서 반납 실행
        boolean result = library.returnBook(isbn);

        // 3. 반납 결과 확인
        if (result) {
            System.out.println("도서 반납 성공!");
        } else {
            System.out.println("도서 반납 실패!");
        }

        // 4. 반납된 도서 정보 출력
        Book book = library.findBookByISBN(isbn);

        if (book != null) {
            System.out.println("반납된 도서 정보:");
            System.out.println(book);
        }

        // 5. 반납 후 도서관 상태 출력
        System.out.println("도서관 현재 상태:");

        System.out.println("전체 도서 수: "
                + library.getTotalBooks());

        System.out.println("대출 가능 도서 수: "
                + library.getAvailableBooksCount());

        System.out.println("대출 중인 도서 수: "
                + library.getBorrowedBooksCount());
    }

    // STEP 4-6. 대출 가능한 도서 목록 출력
    private static void displayAvailableBooks(Library library) {

        System.out.println("===== 대출 가능한 도서 목록 =====");

        // 1. 대출 가능한 도서 목록 가져오기
        List<Book> availableBooks = library.getAvailableBooks();

        // 2. 대출 가능한 도서가 없는 경우
        if (availableBooks.isEmpty()) {
            System.out.println("대출 가능한 도서가 없습니다.");
            return;
        }

        // 3. 대출 가능한 도서 출력
        for (Book book : availableBooks) {
            System.out.println(book);
            System.out.println("------------------------");
        }
    }

	private static void testExtraCases(Library library) {
	
	    System.out.println("===== 추가 테스트 =====");
	
	    String isbn = "978-89-01-14077-4";
	
	    // 첫 번째 대출
	    System.out.println("첫 번째 대출: "
	            + library.checkOutBook(isbn));
	
	    // 같은 책 다시 대출
	    System.out.println("중복 대출: "
	            + library.checkOutBook(isbn));
	
	    // 존재하지 않는 책 대출
	    System.out.println("없는 책 대출: "
	            + library.checkOutBook("999-999"));
	
	    // 책 반납
	    System.out.println("정상 반납: "
	            + library.returnBook(isbn));
	}

}
