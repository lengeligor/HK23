package com.pegasus.hk.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = 688136645L;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final StringPath amount = createString("amount");

    public final NumberPath<Long> balanceAfter = createNumber("balanceAfter", Long.class);

    public final NumberPath<Long> balanceBefore = createNumber("balanceBefore", Long.class);

    public final StringPath category = createString("category");

    public final StringPath date = createString("date");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath label = createString("label");

    public final NumberPath<Long> personId = createNumber("personId", Long.class);

    public final StringPath subcategory = createString("subcategory");

    public final StringPath type = createString("type");

    public QTransaction(String variable) {
        super(Transaction.class, forVariable(variable));
    }

    public QTransaction(Path<? extends Transaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransaction(PathMetadata metadata) {
        super(Transaction.class, metadata);
    }

}

