package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Item {

    private Long id;

    @NotBlank(message = "공백X") // 빈값 또는 공백만 있는 경우 허용 불가
    private String itemName;

    @NotNull // null 허용 불가
    @Range(min = 1000, max = 1000000) // hibernate validator 구현체를 사용할 때만 제공. 실무에서 대부분 사용
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
