package com.dayz.shop.repository;

import com.dayz.shop.jpa.entities.Store;
import com.dayz.shop.jpa.entities.StoreConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreConfigRepository  extends JpaRepository<StoreConfig, Long> {
	StoreConfig findByKeyAndStore(String key, Store store);
	StoreConfig findByKeyAndStoreId(String key, Long storeId);
	List<StoreConfig> findAllByStore(Store store);
}
