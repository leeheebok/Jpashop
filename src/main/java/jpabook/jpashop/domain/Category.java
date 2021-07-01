package jpabook.jpashop.domain;

import jpabook.jpashop.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    //GenerateValue 는 Sequence 를 자동적으로 생성해줍니다.
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CategoryItem> categoryItems  = new ArrayList<>();

    //주어절에는 Many가 먼저 들어간다!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    //받는 쪽은 One이 먼저
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //== 연관관계 메서드==//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
