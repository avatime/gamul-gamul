package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIngredient is a Querydsl query type for Ingredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredient extends EntityPathBase<Ingredient> {

    private static final long serialVersionUID = -1784346947L;

    public static final QIngredient ingredient = new QIngredient("ingredient");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> highClass = createNumber("highClass", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath lowClass = createString("lowClass");

    public final StringPath midClass = createString("midClass");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QIngredient(String variable) {
        super(Ingredient.class, forVariable(variable));
    }

    public QIngredient(Path<? extends Ingredient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngredient(PathMetadata metadata) {
        super(Ingredient.class, metadata);
    }

}

