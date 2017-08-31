package com.github.pwalan.mysql.specs;

import static com.google.common.collect.Iterables.toArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class CustomerSpecs {

    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {

        final Class<T> type = (Class<T>) example.getClass();

        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//存储构造的查询条件
                List<Predicate> predicates = new ArrayList<>();
                //获取实体类的EntityType，以获得实体类属性
                EntityType<T> entity = entityManager.getMetamodel().entity(type);

                for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
                    //获取实体类对象的某一属性值
                    Object attrValue = getValue(example, attr);
                    if (attrValue != null) {
                        if (attr.getJavaType() == String.class) {
                            if (!StringUtils.isEmpty(attrValue)) {
                                //构造当前属性like属性值查询条件
                                predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
                                        pattern((String) attrValue)));
                            }
                        } else {
                            //其余情况构造属性和属性值equal查询条件
                            predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
                                    attrValue));
                        }
                    }

                }
                //将条件列表装换成Predictate
                return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));
            }

            /**
             * 通过反射获得实体类对象的对应属性的属性值
             */
            private <T> Object getValue(T example, Attribute<T, ?> attr) {
                return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
            }

            /**
             * 获得实体类当前属性的SingularAttribute（包含实体类某个单独属性）
             */
            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
                                                             Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }

        };

    }

    /**
     * 构造like的查询模式
     */
    static private String pattern(String str) {
        return "%" + str + "%";
    }

}
