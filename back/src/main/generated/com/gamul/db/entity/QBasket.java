package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasket is a Querydsl query type for Basket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasket extends EntityPathBase<Basket> {

    private static final long serialVersionUID = 79799474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasket basket = new QBasket("basket");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath activeFlag = createBoolean("activeFlag");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QIngredient ingredient;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final QUser user;

    public QBasket(String variable) {
        this(Basket.class, forVariable(variable), INITS);
    }

    public QBasket(Path<? extends Basket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasket(PathMetadata metadata, PathInits inits) {
        this(Basket.class, metadata, inits);
    }

    public QBasket(Class<? extends Basket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

