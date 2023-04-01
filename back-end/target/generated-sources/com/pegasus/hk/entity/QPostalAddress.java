package com.pegasus.hk.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostalAddress is a Querydsl query type for PostalAddress
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPostalAddress extends EntityPathBase<PostalAddress> {

    private static final long serialVersionUID = 1088781776L;

    public static final QPostalAddress postalAddress = new QPostalAddress("postalAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath municipality = createString("municipality");

    public final StringPath region = createString("region");

    public final StringPath registrationNumber = createString("registrationNumber");

    public final StringPath state = createString("state");

    public final StringPath streetName = createString("streetName");

    public final StringPath streetNumber = createString("streetNumber");

    public final StringPath zip = createString("zip");

    public QPostalAddress(String variable) {
        super(PostalAddress.class, forVariable(variable));
    }

    public QPostalAddress(Path<? extends PostalAddress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostalAddress(PathMetadata metadata) {
        super(PostalAddress.class, metadata);
    }

}

