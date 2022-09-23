package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredientSelected is a Querydsl query type for IngredientSelected
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientSelected extends EntityPathBase<IngredientSelected> {

    private static final long serialVersionUID = 2091208824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredientSelected ingredientSelected = new QIngredientSelected("ingredientSelected");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath activeFlag = createBoolean("activeFlag");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QIngredient ingredient;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final QUser user;

    public QIngredientSelected(String variable) {
        this(IngredientSelected.class, forVariable(variable), INITS);
    }

    public QIngredientSelected(Path<? extends IngredientSelected> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredientSelected(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredientSelected(PathMetadata metadata, PathInits inits) {
        this(IngredientSelected.class, metadata, inits);
    }

    public QIngredientSelected(Class<? extends IngredientSelected> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

