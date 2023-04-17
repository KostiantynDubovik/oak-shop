package com.dayz.shop.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "categories")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "CATEGORY_ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "CATEGORY_NAME", nullable = false, unique = true)
	private String categoryName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "category_relations",
			joinColumns = @JoinColumn(name = "PARENT_CATEGORY_ID", referencedColumnName = "CATEGORY_ID"),
			inverseJoinColumns = @JoinColumn(name = "CHILD_CATEGORY_ID", referencedColumnName = "CATEGORY_ID"))
	@ToString.Exclude
	private List<Category> childCategories;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DESCRIPTION_ID")
	private Description description;

	@Column(name = "visible")
	private boolean visible;

	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	private Store store;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Category category = (Category) o;
		return id != null && Objects.equals(id, category.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
