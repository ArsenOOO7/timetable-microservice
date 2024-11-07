package com.pnu.system.common.utils;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class QueryDslFactory {

    private static JPAQueryFactory queryFactory;

    public QueryDslFactory(EntityManager em) {
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
    }

    public static JPAQueryFactory getQueryFactory() {
        if (queryFactory == null) {
            throw new NullPointerException("Query Factory is not initialized yet");
        }
        return queryFactory;
    }
}