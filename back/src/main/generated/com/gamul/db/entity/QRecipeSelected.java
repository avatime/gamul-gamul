package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeSelected is a Querydsl query type for RecipeSelected
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeSelected extends EntityPathBase<RecipeSelected> {

    private static final long serialVersionUID = -276988619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeSelected recipeSelected = new QRecipeSelected("recipeSelected");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath activeFlag = createBoolean("activeFlag");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QRecipe recipe;

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final QUser user;

    public QRecipeSelected(String variable) {
        this(RecipeSelected.class, forVariable(variable), INITS);
    }

    public QRecipeSelected(Path<? extends RecipeSelected> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeSelected(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeSelected(PathMetadata metadata, PathInits inits) {
        this(RecipeSelected.class, metadata, inits);
    }

    public QRecipeSelected(Class<? extends RecipeSelected> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new QRecipe(forProperty("recipe")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

