package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //다대다 --> 실무에서 사용X
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();
}
