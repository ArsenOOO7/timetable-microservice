package com.pnu.system.common.search.helper;

import com.pnu.system.common.exception.InvalidParameterException;
import com.pnu.system.common.search.constant.ConditionOperation;
import com.pnu.system.common.search.dto.Condition;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchHelper {

    public Predicate buildWhereCondition(Condition condition, Path<?> path) {
        switch (condition.getDataType()) {
            case STRING -> {
                return buildPredicate((StringPath) path, (String) condition.getValue(), condition.getOperation());
            }
            case INTEGER -> {
                return buildPredicate((NumberPath<Integer>) path, (Integer) condition.getValue(), condition.getOperation());
            }
            case LIST_STRING -> {
                return buildPredicateForList((StringPath) path, (List<String>) condition.getValue(), condition.getOperation());
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
            case IN -> {
                return expression.in(value);
            }
            default -> throw new InvalidParameterException("Invalid Operation: " + operation);
        }
    }

    private <E> Predicate buildPredicateForList(SimpleExpression<E> expression, List<E> values, ConditionOperation operation) {
        switch (operation) {
            case IN -> {
                return expression.in(values);
            }
            default -> throw new InvalidParameterException("Invalid Operation: " + operation);
        }
    }

}
