package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "금액과 수량의 곱이 10,000원 이상으로 입력하세요.")
public class ItemOldVersion {

    @NotNull(groups = UpdateCheck.class) // 수정 요구사항 추가
    private Long id;

    @NotBlank(message = "공백X", groups = {SaveCheck.class, UpdateCheck.class}) // 빈값 또는 공백만 있는 경우 허용 불가
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class}) // null 허용 불가
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class}) // hibernate validator 구현체를 사용할 때만 제공. 실무에서 대부분 사용
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public ItemOldVersion() {
    }

    public ItemOldVersion(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
