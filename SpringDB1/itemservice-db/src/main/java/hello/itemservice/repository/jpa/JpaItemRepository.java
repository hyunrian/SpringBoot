package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional // JPA에서 데이터를 변경할 때는 항상 Transaction 내에서 이루어져야 함(일반적으로 Repository가 아니라 Service 계층에서 트랜잭션을 걸어야 함)
public class JpaItemRepository implements ItemRepository {

    //스프링 부트는 JPA의 다양한 설정을 자동으로 해줌

    private final EntityManager em;
    //EntityManager가 있어야 JPA의 기능을 사용할 수 있음
    //JPA의 모든 동작은 EntityManager를 통해 이루어짐
    //내부에 DataSource를 가지고 있고, DB에 접근할 수 있음

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item) {
        em.persist(item); //테이블에 객체 저장
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        //entity의 변경을 기반으로 transaction이 커밋되는 시점에 db에 반영됨(update)
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id); //em.find(type, pk); 단건 조회
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        //jpql: sql과 거의 비슷함. 객체 지향 쿼리 언어(Java Persistence Query Language)
        //여러 데이터를 복잡한 조건으로 조회할 때 사용
        //from 다음에 Item Entity 객체 이름이 들어감(대소문자 구분 필요)
        String jpql = "select i from Item i"; // i : Item Entity

        Integer maxPrice = cond.getMaxPrice();
        String itemName = cond.getItemName();

        if (StringUtils.hasText(itemName) || maxPrice != null) {
            jpql += " where";
        }

        boolean andFlag = false;

        if (StringUtils.hasText(itemName)) {
            jpql += " i.itemName like concat('%',:itemName,'%')";
            andFlag = true;
        }

        if (maxPrice != null) {
            if (andFlag) {
                jpql += " and";
            }
            jpql += " i.price <= :maxPrice";
        }

        log.info("jpql={}", jpql);

        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        if (StringUtils.hasText(itemName)) {
            query.setParameter("itemName", itemName);
        }
        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }
        return query.getResultList();
    }
}
