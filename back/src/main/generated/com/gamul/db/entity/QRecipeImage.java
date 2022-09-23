package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeImage is a Querydsl query type for RecipeImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeImage extends EntityPathBase<RecipeImage> {

    private static final long serialVersionUID = 307148033L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeImage recipeImage = new QRecipeImage("recipeImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> imageOrder = createNumber("imageOrder", Integer.class);

    public final StringPath imagePath = createString("imagePath");

    public final QMyRecipe myRecipe;

    public QRecipeImage(String variable) {
        this(RecipeImage.class, forVariable(variable), INITS);
    }

    public QRecipeImage(Path<? extends RecipeImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeImage(PathMetadata metadata, PathInits inits) {
        this(RecipeImage.class, metadata, inits);
    }

    public QRecipeImage(Class<? extends RecipeImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.myRecipe = inits.isInitialized("myRecipe") ? new QMyRecipe(forProperty("myRecipe"), inits.get("myRecipe")) : null;
    }

}

