package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 1265762421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QDo dou;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath name = createString("name");

    public final QSigugun sigugun;

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dou = inits.isInitialized("dou") ? new QDo(forProperty("dou"), inits.get("dou")) : null;
        this.sigugun = inits.isInitialized("sigugun") ? new QSigugun(forProperty("sigugun"), inits.get("sigugun")) : null;
    }

}

