package com.gamul.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHighClass is a Querydsl query type for HighClass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHighClass extends EntityPathBase<HighClass> {

    private static final long serialVersionUID = -1185534326L;

    public static final QHighClass highClass = new QHighClass("highClass");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QHighClass(String variable) {
        super(HighClass.class, forVariable(variable));
    }

    public QHighClass(Path<? extends HighClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHighClass(PathMetadata metadata) {
        super(HighClass.class, metadata);
    }

}

