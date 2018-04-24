package example.com.mapping_m2m.uni.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import example.com.mapping_m2m.uni.domain.Member;
import example.com.mapping_m2m.uni.domain.Product;

public class App {
    public static void main( String[] args ) {
		// 1. 엔티티 매니저 팩토리 생성.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabookmall");

		//2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		//3. 트랜잭션 받아오기
		EntityTransaction tx = em.getTransaction();
		
		//4. 트랜잭션 시작 
		tx.begin();
		
		//5. 비지니스 로직
		try {
			// testSave( em );
			testFind( em );
			
		} catch( Exception ex ) {
			ex.printStackTrace();
			tx.rollback();
		}
		
		//6. 트랜잭션 커밋
		tx.commit();
		
		//7. 엔티티 매니저 닫기
		em.close();
		
		//8. 엔티티 매니저 팩토리 닫기
		emf.close();
    }
    
    public static void testSave( EntityManager em ) {
    	Product productA = new Product();
    	productA.setName("상품 A");
    	em.persist( productA );
    	
    	Member member1 = new Member();
    	member1.setName( "회원 1" );
    	//연관관계 설정
    	member1.getProducts().add( productA );
    	em.persist( member1 );
    }
    
    public static void testFind( EntityManager em ) {
    	Member member = em.find( Member.class, 1L );
    	//객체 그래프 탐색
    	List<Product> products = member.getProducts();
    	for(Product product : products) {
    		System.out.println( product );
    	}
    	
    }
}
