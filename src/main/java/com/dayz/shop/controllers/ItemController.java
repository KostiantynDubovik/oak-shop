package com.dayz.shop.controllers;

import com.dayz.shop.jpa.entities.*;
import com.dayz.shop.repository.CategoryRepository;
import com.dayz.shop.repository.ItemRepository;
import com.dayz.shop.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;
	private final LanguageRepository languageRepository;
	private final ItemDescriptionRepository itemDescriptionRepository;

	@Autowired
	public ItemController(ItemRepository itemRepository, CategoryRepository categoryRepository,
	                      LanguageRepository languageRepository, ItemDescriptionRepository itemDescriptionRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.languageRepository = languageRepository;
		this.itemDescriptionRepository = itemDescriptionRepository;
	}

	@GetMapping("{itemId}")
	public Item getItem(@PathVariable Long itemId, @RequestAttribute Store store) {
		return itemRepository.findByIdAndStore(itemId, store);
	}

	@GetMapping("{categoryName}/{page}")
	public Page<Item> getByCategory(@PathVariable("categoryName") String categoryName,
	                                @PathVariable int page,
	                                @RequestParam(defaultValue = "sequence") String sortBy,
	                                @RequestParam(defaultValue = "100") int pageSize,
	                                @RequestAttribute Store store) {
		Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize < 3 ? 3 : pageSize, Sort.by(sortBy));
		return itemRepository.findAllByStoreAndBuyableAndCategory(store, categoryRepository.findByCategoryName(categoryName), pageable);
	}

	@PostMapping()
	@Consumes("*/*")
	@PreAuthorize("hasAuthority('STORE_WRITE')")
	public Item createItem(@RequestBody Item item, @RequestParam BigDecimal listPrice, @RequestAttribute Store store) {
		item.setStore(store);
		ItemDescription itemDescription = item.getItemDescription();
		if (itemDescription != null) {
			itemDescription.setStore(store);
			itemDescription.setItem(item);
			itemDescription.setLanguage(languageRepository.getById(-3L));
			itemDescriptionRepository.save(itemDescription);
		}
		ListPrice price = new ListPrice();
		price.setPrice(listPrice);
		price.setStore(store);
		price.setItem(item);
		price.setCurrency("RUB");
		item.setListPrice(price);
		if (item.getImageUrl() == null) {
			item.setImageUrl(Utils.getStoreConfig("default.image", store));
		}
		return itemRepository.save(item);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('STORE_WRITE')")
	public Item updateItem(@RequestBody Item item) {
		return itemRepository.save(item);
	}

	@DeleteMapping("{item}")
	@PreAuthorize("hasAuthority('STORE_WRITE')")
	public void deleteItem(@PathVariable Item item) {
		itemRepository.delete(item);
	}
}
