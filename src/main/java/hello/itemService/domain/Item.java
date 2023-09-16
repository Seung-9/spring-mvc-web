package hello.itemService.domain;


import lombok.Data;

@Data
public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    public Item(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
