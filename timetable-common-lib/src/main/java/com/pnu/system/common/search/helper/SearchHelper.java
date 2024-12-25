package com.pnu.system.common.search.helper;

import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.dto.SearchCondition;
import com.pnu.system.common.search.dto.SearchOrderByField;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SearchHelper {

    public Predicate buildWhereCondition(SearchCondition condition, Path<?> path) {
        switch (condition.getDataType()) {
            case STRING -> {
                return buildPredicate((StringPath) path, (String) condition.getValue(), condition.getOperation());
            }
            case INTEGER -> {
                return buildPredicate((NumberPath<Integer>) path, (Integer) condition.getValue(), condition.getOperation());
            }
            case LIST_STRING -> {
                return buildPredicateForCollection((StringPath) path, (List<String>) condition.getValue(), condition.getOperation());
            }
            default -> throw new InvalidParameterException("Invalid data type: " + condition.getDataType());
        }
    }

    private <E> Predicate buildPredicate(SimpleExpression<E> expression, E value, ConditionOperation operation) {
        switch (operation) {
            case EQUAL -> {
                return expression.eq(value);
            }
            case NOT_EQUAL -> {
                return expression.ne(value);
            }
            case CONTAIN -> {
                return Expressions.booleanOperation(Ops.STRING_CONTAINS, expression, ConstantImpl.create(value));
            }
            default -> throw new InvalidParameterException("Invalid Operation: " + operation);
        }
    }

    private <E> Predicate buildPredicateForCollection(SimpleExpression<E> expression, Collection<E> values, ConditionOperation operation) {
        switch (operation) {
            case IN -> {
                return expression.in(values);
            }
            case NOT_IN -> {
                return expression.notIn(values);
            }
            default -> throw new InvalidParameterException("Invalid Operation: " + operation);
        }
    }

    public OrderSpecifier<?> buildOrderSpecifier(SearchOrderByField orderByField, ComparableExpressionBase<?> path) {
        switch (orderByField.getOrderBy()) {
            case ASC -> {
                return path.asc();
            }
            case DESC -> {
                return path.desc();
            }
            default -> throw new InvalidParameterException("Invalid order by: " + orderByField.getOrderBy());
        }
    }

}
