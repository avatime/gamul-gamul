package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDo is a Querydsl query type for Do
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDo extends EntityPathBase<Do> {

    private static final long serialVersionUID = -1289990793L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDo do$ = new QDo("do$");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath doName = createString("doName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QStore store;

    public QDo(String variable) {
        this(Do.class, forVariable(variable), INITS);
    }

    public QDo(Path<? extends Do> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDo(PathMetadata metadata, PathInits inits) {
        this(Do.class, metadata, inits);
    }

    public QDo(Class<? extends Do> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

