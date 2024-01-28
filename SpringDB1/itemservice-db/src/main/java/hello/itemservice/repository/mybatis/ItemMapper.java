package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {
    //MyBatis 매핑 xml을 호출해주는 매퍼 인터페이스
    //xml 파일은 resources 하위에 interface와 동일한 디렉토리 구조 내에 위치해야 함
    //원하는 위치에 두고 싶으면 application.properties에
    // "mybatis.mapper-locations=classpath:mapper/**/*.xml" 등으로 작성 -> resources/mapper 포함 하위 폴더의 xml 인식 가능

    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);
    // parameter가 두개 이상일 경우에는 @Param을 작성해야 함

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond itemSearch);
}
