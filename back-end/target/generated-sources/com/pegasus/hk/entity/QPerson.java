package com.pegasus.hk.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = -75081714L;

    public static final QPerson person = new QPerson("person");

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final NumberPath<Long> age = createNumber("age", Long.class);

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final StringPath dateOfBirth = createString("dateOfBirth");

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastname = createString("lastname");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath type = createString("type");

    public QPerson(String variable) {
        super(Person.class, forVariable(variable));
    }

    public QPerson(Path<? extends Person> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerson(PathMetadata metadata) {
        super(Person.class, metadata);
    }

}

