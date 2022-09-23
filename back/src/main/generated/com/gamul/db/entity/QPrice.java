package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrice is a Querydsl query type for Price
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrice extends EntityPathBase<Price> {

    private static final long serialVersionUID = 1262926045L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrice price1 = new QPrice("price1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> dateTime = createDateTime("dateTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QIngredient ingredient;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QStore store;

    public final StringPath unit = createString("unit");

    public QPrice(String variable) {
        this(Price.class, forVariable(variable), INITS);
    }

    public QPrice(Path<? extends Price> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrice(PathMetadata metadata, PathInits inits) {
        this(Price.class, metadata, inits);
    }

    public QPrice(Class<? extends Price> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

