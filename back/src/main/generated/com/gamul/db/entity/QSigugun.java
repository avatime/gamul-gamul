package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSigugun is a Querydsl query type for Sigugun
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSigugun extends EntityPathBase<Sigugun> {

    private static final long serialVersionUID = 599728016L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSigugun sigugun = new QSigugun("sigugun");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath sigugunName = createString("sigugunName");

    public final QStore store;

    public QSigugun(String variable) {
        this(Sigugun.class, forVariable(variable), INITS);
    }

    public QSigugun(Path<? extends Sigugun> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSigugun(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSigugun(PathMetadata metadata, PathInits inits) {
        this(Sigugun.class, metadata, inits);
    }

    public QSigugun(Class<? extends Sigugun> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

