package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyRecipe is a Querydsl query type for MyRecipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyRecipe extends EntityPathBase<MyRecipe> {

    private static final long serialVersionUID = 243809862L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyRecipe myRecipe = new QMyRecipe("myRecipe");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final QUser user;

    public QMyRecipe(String variable) {
        this(MyRecipe.class, forVariable(variable), INITS);
    }

    public QMyRecipe(Path<? extends MyRecipe> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyRecipe(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyRecipe(PathMetadata metadata, PathInits inits) {
        this(MyRecipe.class, metadata, inits);
    }

    public QMyRecipe(Class<? extends MyRecipe> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

