package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyRecipeIngredient is a Querydsl query type for MyRecipeIngredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyRecipeIngredient extends EntityPathBase<MyRecipeIngredient> {

    private static final long serialVersionUID = 148892855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyRecipeIngredient myRecipeIngredient = new QMyRecipeIngredient("myRecipeIngredient");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QIngredient ingredient;

    public final QMyRecipe myRecipe;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QMyRecipeIngredient(String variable) {
        this(MyRecipeIngredient.class, forVariable(variable), INITS);
    }

    public QMyRecipeIngredient(Path<? extends MyRecipeIngredient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyRecipeIngredient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyRecipeIngredient(PathMetadata metadata, PathInits inits) {
        this(MyRecipeIngredient.class, metadata, inits);
    }

    public QMyRecipeIngredient(Class<? extends MyRecipeIngredient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.myRecipe = inits.isInitialized("myRecipe") ? new QMyRecipe(forProperty("myRecipe"), inits.get("myRecipe")) : null;
    }

}

