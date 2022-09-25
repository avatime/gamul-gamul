package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeOrder is a Querydsl query type for RecipeOrder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeOrder extends EntityPathBase<RecipeOrder> {

    private static final long serialVersionUID = 312840948L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeOrder recipeOrder = new QRecipeOrder("recipeOrder");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> descOrder = createNumber("descOrder", Integer.class);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QRecipe recipe;

    public QRecipeOrder(String variable) {
        this(RecipeOrder.class, forVariable(variable), INITS);
    }

    public QRecipeOrder(Path<? extends RecipeOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeOrder(PathMetadata metadata, PathInits inits) {
        this(RecipeOrder.class, metadata, inits);
    }

    public QRecipeOrder(Class<? extends RecipeOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new QRecipe(forProperty("recipe")) : null;
    }

}

