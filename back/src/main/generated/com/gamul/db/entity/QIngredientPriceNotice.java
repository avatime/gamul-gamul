package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredientPriceNotice is a Querydsl query type for IngredientPriceNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientPriceNotice extends EntityPathBase<IngredientPriceNotice> {

    private static final long serialVersionUID = 1876316804L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredientPriceNotice ingredientPriceNotice = new QIngredientPriceNotice("ingredientPriceNotice");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath activeFlag = createBoolean("activeFlag");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QIngredient ingredient;

    public final NumberPath<Integer> lowerLimitPrice = createNumber("lowerLimitPrice", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Integer> upperLimitPrice = createNumber("upperLimitPrice", Integer.class);

    public final QUser user;

    public QIngredientPriceNotice(String variable) {
        this(IngredientPriceNotice.class, forVariable(variable), INITS);
    }

    public QIngredientPriceNotice(Path<? extends IngredientPriceNotice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredientPriceNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredientPriceNotice(PathMetadata metadata, PathInits inits) {
        this(IngredientPriceNotice.class, metadata, inits);
    }

    public QIngredientPriceNotice(Class<? extends IngredientPriceNotice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

