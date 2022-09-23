package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAllergy is a Querydsl query type for Allergy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllergy extends EntityPathBase<Allergy> {

    private static final long serialVersionUID = 1894569492L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAllergy allergy = new QAllergy("allergy");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath activeFlag = createBoolean("activeFlag");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QIngredient ingredient;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final QUser user;

    public QAllergy(String variable) {
        this(Allergy.class, forVariable(variable), INITS);
    }

    public QAllergy(Path<? extends Allergy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAllergy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAllergy(PathMetadata metadata, PathInits inits) {
        this(Allergy.class, metadata, inits);
    }

    public QAllergy(Class<? extends Allergy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

