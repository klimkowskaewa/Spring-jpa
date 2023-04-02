package pl.edu.wszib.springjpa.zwierze;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class ZwierzeSpecifications {

    public static Specification<Zwierze> findByGatunekAndName(String gatunek, String name) {
        return new Specification<Zwierze>() {
            @Override
            public Predicate toPredicate(Root<Zwierze> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> predicates = new ArrayList<>();

                if (gatunek != null) {
                    predicates.add(
                            criteriaBuilder.equal(root.get("gatunek"), gatunek));
                }

                return criteriaBuilder.and(
                        predicates.toArray(new Predicate[0]));
            }
        };
    }
}