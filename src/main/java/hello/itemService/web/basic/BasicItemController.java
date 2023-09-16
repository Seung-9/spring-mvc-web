package hello.itemService.web.basic;

import hello.itemService.domain.Item;
import hello.itemService.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "/basic/items";
    }

    // 상품 상세 조회
    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "/basic/item";
    }

    // 상품 등록 폼 추가
    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    @PostMapping("/add")
    // 상품 등록 처리 - ModelAttribute 이용
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "/basic/item";
    }

    // 테스트용 데이터 추가. 초기화 콜백
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemA", 20000, 20));
    }
}
