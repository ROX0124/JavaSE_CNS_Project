
package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;
import java.util.List;

public class LibraryTest {

    public static void main(String[] args) {

        Library library = new Library("중앙 도서관");

        Book book1 = new Book(
                "자바의 정석",
                "남궁성",
                "978-89-01-14077-4",
                2019
        );

        Book book2 = new Book(
                "Clean Code",
                "Robert C. Martin",
                "978-0-13-235088-4",
                2008
        );

        library.addBook(book1);
        library.addBook(book2);
        

		System.out.println("===== 도서 검색 테스트 =====");
		
		Book foundBook = library.findBookByTitle("자바의 정석");
		
		if (foundBook != null) {
		    System.out.println("검색 성공!");
		    System.out.println(foundBook);
		} else {
		    System.out.println("도서를 찾을 수 없습니다.");
		}
		

		System.out.println("===== 저자 검색 테스트 =====");
		
		List<Book> foundBooks =
		        library.findBooksByAuthor("Robert C. Martin");
		
		for (Book book : foundBooks) {
		    System.out.println(book);
		}
		
		System.out.println("검색된 도서 수: " + foundBooks.size());
		

		System.out.println("===== ISBN 검색 테스트 =====");
		
		Book isbnBook = library.findBookByISBN("978-89-01-14077-4");
		
		if (isbnBook != null) {
		    System.out.println("ISBN 검색 성공!");
		    System.out.println(isbnBook);
		} else {
		    System.out.println("해당 ISBN의 도서를 찾을 수 없습니다.");
		}

		System.out.println("===== 도서 대출 테스트 =====");
		
		String isbn = "978-89-01-14077-4";
		
		// 첫 번째 대출
		boolean firstResult = library.checkOutBook(isbn);
		System.out.println("첫 번째 대출 결과: " + firstResult);
		
		// 두 번째 대출
		boolean secondResult = library.checkOutBook(isbn);
		System.out.println("두 번째 대출 결과: " + secondResult);
		
		// 대출 후 도서 정보
		Book borrowedBook = library.findBookByISBN(isbn);
		
		if (borrowedBook != null) {
		    System.out.println("대출된 도서 정보:");
		    System.out.println(borrowedBook);
		}
		
		// 존재하지 않는 ISBN
		boolean thirdResult = library.checkOutBook("999-999");
		System.out.println("없는 도서 대출 결과: " + thirdResult);

		System.out.println("===== 도서 반납 테스트 =====");
		
		String returnIsbn = "978-89-01-14077-4";
		
		// 도서 반납
		boolean returnResult = library.returnBook(returnIsbn);
		
		System.out.println("도서 반납 결과: " + returnResult);
		
		// 반납 후 도서 상태 확인
		Book returnedBook = library.findBookByISBN(returnIsbn);
		
		if (returnedBook != null) {
		    System.out.println("반납된 도서 정보:");
		    System.out.println(returnedBook);
		}
		
		// 존재하지 않는 ISBN 반납
		boolean invalidResult = library.returnBook("999-999");
		
		System.out.println("없는 도서 반납 결과: " + invalidResult);


		System.out.println("===== 도서 목록 조회 테스트 =====");
		
		// Clean Code 대출
		library.checkOutBook("978-0-13-235088-4");
		
		// 전체 도서 목록
		System.out.println("===== 전체 도서 목록 =====");
		
		List<Book> allBooks = library.getAllBooks();
		
		for (Book book : allBooks) {
		    System.out.println(book);
		}
		
		System.out.println("전체 도서 수: " + allBooks.size());
		
		// 대출 가능한 도서 목록
		System.out.println("===== 대출 가능한 도서 목록 =====");
		
		List<Book> availableBooks = library.getAvailableBooks();
		
		for (Book book : availableBooks) {
		    System.out.println(book);
		}
		
		System.out.println("대출 가능 도서 수: " + availableBooks.size());

		System.out.println("===== 도서 통계 테스트 =====");
		
		// 테스트 상태 초기화
		library.returnBook("978-89-01-14077-4");
		library.returnBook("978-0-13-235088-4");
		
		// Clean Code 대출
		library.checkOutBook("978-0-13-235088-4");
		
		// 도서 수량 통계
		System.out.println("전체 도서 수: "
		        + library.getTotalBooks());
		
		System.out.println("대출 가능 도서 수: "
		        + library.getAvailableBooksCount());
		
		System.out.println("대출 중인 도서 수: "
		        + library.getBorrowedBooksCount());



    }
}
