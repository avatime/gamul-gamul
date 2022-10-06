package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecipe is a Querydsl query type for Recipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipe extends EntityPathBase<Recipe> {

    private static final long serialVersionUID = 541081722L;

    public static final QRecipe recipe = new QRecipe("recipe");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath information = createString("information");

    public final StringPath name = createString("name");

    public final StringPath thumbnail = createString("thumbnail");

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QRecipe(String variable) {
        super(Recipe.class, forVariable(variable));
    }

    public QRecipe(Path<? extends Recipe> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecipe(PathMetadata metadata) {
        super(Recipe.class, metadata);
    }

}

